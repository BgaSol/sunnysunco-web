package com.sunnysunco.cloud.business.information.file;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.business.base.exception.BaseException;
import com.sunnysunco.cloud.business.information.file.dto.CreateFileDto;
import com.sunnysunco.cloud.config.minio.MinioConfig;
import io.minio.GetObjectArgs;
import io.minio.GetObjectResponse;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.errors.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.codec.digest.DigestUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class FileService extends BaseService<FileEntity, BasePageDto<FileEntity>> {
    private final FileMapper fileMapper;

    private final FileRepository fileRepository;

    private final MinioClient minioClient;

    private final MinioConfig minioConfig;

    @Override
    public FileMapper commonBaseMapper() {
        return fileMapper;
    }

    @Override
    public FileRepository commonBaseRepository() {
        return fileRepository;
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

        // 获取文件流
        InputStream inputStream;
        try {
            inputStream = uploadFile.getInputStream();
        } catch (IOException e) {
            log.error("获取文件流失败", e);
            throw new BaseException("获取文件流失败");
        }
        // 获取文件hash
        try {
            String hex = DigestUtils.sha256Hex(inputStream);
            fileEntity.setHash(hex);
        } catch (IOException e) {
            throw new BaseException("获取文件hash失败");
        }
        // 判断文件是否存在
        QueryWrapper<FileEntity> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("hash", fileEntity.getHash());
        FileEntity file = this.fileMapper.selectOne(queryWrapper);
        if (file != null) {
            return file;
        }
        // 保存文件信息到数据库
        FileEntity saveEntity = this.save(fileEntity);
        // 初始化inputStream
        try {
            inputStream = uploadFile.getInputStream();
        } catch (IOException e) {
            log.error("获取文件流失败", e);
            throw new BaseException("获取文件流失败");
        }
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
        try {
            // 上传文件到minio 文件名称相同会覆盖
            minioClient.putObject(objectArgs);
        } catch (ErrorResponseException | InsufficientDataException | InternalException | InvalidKeyException |
                 InvalidResponseException | IOException | NoSuchAlgorithmException | ServerException |
                 XmlParserException e) {
            log.error("上传文件失败", e);
            throw new BaseException("上传文件失败");
        }
        return saveEntity;
    }

    /**
     * 获取文件流
     *
     * @param id 文件id
     * @return 文件流
     */
    @Transactional(readOnly = true)
    public GetObjectResponse getFileStream(String id) {
        FileEntity byId = this.findById(id);
        if (byId == null) {
            throw new BaseException("文件不存在");
        }
        try {
            GetObjectArgs build = GetObjectArgs
                    .builder()
                    .bucket(byId.getBucket())
                    .object(byId.getId())
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
}
