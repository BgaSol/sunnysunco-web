package com.sunnysunco.cloud.business.information.image;

import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.base.exception.BaseException;
import com.sunnysunco.cloud.business.information.file.FileEntity;
import com.sunnysunco.cloud.business.information.file.FileService;
import com.sunnysunco.cloud.business.information.image.dto.ImagePageDto;
import io.minio.GetObjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class ImageService extends BaseService<ImageEntity, ImagePageDto> {
    private final ImageMapper imageMapper;

    private final ImageRepository imageRepository;

    private final FileService fileService;

    @Override
    public ImageMapper commonBaseMapper() {
        return imageMapper;
    }

    @Override
    public ImageRepository commonBaseRepository() {
        return imageRepository;
    }

    @Override
    @Transactional
    public ImageEntity save(ImageEntity entity) {
        FileEntity file = fileService.findById(entity.getFileId());
        // 设置图片类型
        entity.setType(file.getType());
        // 获取图片文件流
        GetObjectResponse imageStream = fileService.getFileStream(file.getId());
        // 流转为图片
        BufferedImage read;
        try {
            read = ImageIO.read(imageStream);
        } catch (IOException e) {
            throw new BaseException("图片读取错误");
        }
        // 设置图片宽高
        entity.setWidth(read.getWidth());
        entity.setHeight(read.getHeight());
        // 设置图片类型
        return super.save(entity);
    }

    @Override
    @Transactional
    public ImageEntity update(ImageEntity entity) {
        FileEntity file = fileService.findById(entity.getFileId());
        // 设置图片类型
        entity.setType(file.getType());
        // 获取图片文件流
        GetObjectResponse imageStream = fileService.getFileStream(file.getId());
        // 流转为图片
        BufferedImage read;
        try {
            read = ImageIO.read(imageStream);
        } catch (IOException e) {
            throw new BaseException("图片读取错误");
        }
        // 设置图片宽高
        entity.setWidth(read.getWidth());
        entity.setHeight(read.getHeight());
        return super.update(entity);
    }

    /**
     * 获取图片文件流
     *
     * @param id 图片ID
     * @return 图片文件流
     */
    @Transactional(readOnly = true)
    public GetObjectResponse getImageStream(String id) {
        ImageEntity imageEntity = this.findById(id);
        // 获取图片文件流
        return fileService.getFileStream(imageEntity.getFile().getId());
    }
}
