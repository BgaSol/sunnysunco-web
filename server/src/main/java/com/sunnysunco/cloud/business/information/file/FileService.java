package com.sunnysunco.cloud.business.information.file;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunnysunco.cloud.business.auth.role.RoleEntity;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.base.dto.BaseCreateDto;
import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.business.base.exception.BaseException;
import com.sunnysunco.cloud.business.information.file.dto.CreateFileDto;
import com.sunnysunco.cloud.config.minio.MinioConfig;
import io.minio.*;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.activation.MimetypesFileTypeMap;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileService extends BaseService<FileEntity, BasePageDto<FileEntity>> {
    private final FileMapper fileMapper;

    private final FileRepository fileRepository;

    private final MinioClient minioClient;

    private final MinioConfig minioConfig;

    private final MimetypesFileTypeMap fileTypeMap = new MimetypesFileTypeMap();

    @Override
    public FileMapper commonBaseMapper() {
        return fileMapper;
    }

    @Override
    public FileRepository commonBaseRepository() {
        return fileRepository;
    }

    /**
     * 从本地路径保存文件
     *
     * @param file 文件
     * @return 文件实体
     */
    @Transactional
    public FileEntity save(File file, List<RoleEntity> roles) {
        String hash;
        // 获取文件hash
        try {
            FileInputStream fileInputStream = FileUtils.openInputStream(file);
            hash = DigestUtils.sha256Hex(fileInputStream);
            fileInputStream.close();
        } catch (IOException e) {
            throw new BaseException("获取文件hash失败");
        }

        FileEntity newFile = new FileEntity();
        newFile.setHash(hash);
        newFile.setId(BaseCreateDto.staticInitId(newFile));
        newFile.setName(file.getName());
        newFile.setSize(String.valueOf(file.length()));

        newFile.setAccessRole(roles);

        String mimeType = fileTypeMap.getContentType(file.getName());

        newFile.setType(mimeType);
        newFile.setBucket(minioConfig.getBucket());

        newFile = this.save(newFile);
        // 初始化inputStream
        try {
            FileInputStream fileInputStream = FileUtils.openInputStream(file);
            // 创建上传文件参数
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    // 设置桶
                    .bucket(newFile.getBucket())
                    // 设置文件id
                    .object(newFile.getId())
                    // 设置文件流
                    .stream(fileInputStream, file.length(), -1)
                    // 设置文件类型
                    .contentType(newFile.getType())
                    .build();
            // 上传文件到minio 文件名称相同会覆盖
            minioClient.putObject(objectArgs);
            fileInputStream.close();
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            log.error("上传文件失败", e);
            throw new BaseException("上传文件失败");
        }
        return newFile;
    }

    private FileEntity fileIsExist(String hash) {
        QueryWrapper<FileEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hash", hash);
        return this.fileMapper.selectOne(queryWrapper);
    }

    /**
     * 保存文件
     *
     * @param createFileDto 创建文件dto
     * @return 文件实体
     */
    @Transactional
    public FileEntity save(CreateFileDto createFileDto) {
        MultipartFile uploadFile = createFileDto.getUploadFile();

        FileEntity fileEntity = new FileEntity();
        fileEntity.setName(uploadFile.getOriginalFilename());
        fileEntity.setSize(String.valueOf(uploadFile.getSize()));

        fileEntity.setType(uploadFile.getContentType());

        fileEntity.setBucket(minioConfig.getBucket());

        fileEntity.setId(createFileDto.initId(fileEntity));

        // 获取文件hash
        try {
            InputStream inputStream = uploadFile.getInputStream();
            String hex = DigestUtils.sha256Hex(inputStream);
            fileEntity.setHash(hex);
            inputStream.close();
        } catch (IOException e) {
            throw new BaseException("获取文件hash失败");
        }
        fileEntity = this.save(fileEntity);
        try {
            InputStream inputStream = uploadFile.getInputStream();
            // 创建上传文件参数
            PutObjectArgs objectArgs = PutObjectArgs.builder()
                    // 设置桶
                    .bucket(fileEntity.getBucket())
                    // 设置文件id
                    .object(fileEntity.getId())
                    // 设置文件流
                    .stream(inputStream, uploadFile.getSize(), -1)
                    // 设置文件类型
                    .contentType(fileEntity.getType())
                    .build();
            // 上传文件到minio 文件名称相同会覆盖
            minioClient.putObject(objectArgs);
            inputStream.close();
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            log.error("上传文件失败", e);
            throw new BaseException("上传文件失败");
        }
        return fileEntity;
    }

    /**
     * 获取文件流
     *
     * @param id 文件id
     * @return 文件流
     */
    @Transactional(readOnly = true)
    public GetObjectResponse getFileStream(String id) {
        FileEntity file = this.findById(id);
        if (file == null) {
            throw new BaseException("文件不存在");
        }
        try {
            GetObjectArgs build = GetObjectArgs
                    .builder()
                    .bucket(file.getBucket())
                    .object(file.getId())
                    .build();
            // 获取文件流
            return minioClient.getObject(build);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            log.error("获取文件流失败", e);
            throw new BaseException("获取文件流失败");
        }
    }

    /**
     * 删除文件
     *
     * @param ids 文件id
     * @return 删除数量
     */
    @Override
    @Transactional
    public Integer[] delete(String... ids) {
        for (String id : ids) {
            FileEntity fileEntity = this.findById(id);
            if (fileEntity == null) {
                throw new BaseException("文件不存在");
            }
            try {
                RemoveObjectArgs build = RemoveObjectArgs
                        .builder()
                        .bucket(fileEntity.getBucket())
                        .object(fileEntity.getId())
                        .build();
                // 删除文件
                minioClient.removeObject(build);
            } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                     InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                     XmlParserException e) {
                log.error("删除文件失败", e);
                throw new BaseException("删除文件失败");
            }
        }
        return super.delete(ids);
    }
}
