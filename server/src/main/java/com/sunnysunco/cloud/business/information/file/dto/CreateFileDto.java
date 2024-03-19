package com.sunnysunco.cloud.business.information.file.dto;

import com.sunnysunco.cloud.business.base.dto.BaseCreateDto;
import com.sunnysunco.cloud.business.information.file.FileEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotNull;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "创建文件")
@Data
public class CreateFileDto extends BaseCreateDto<FileEntity> {
    @Schema(description = "要上传的文件块")
    @NotNull(message = "上传文件不能为空")
    MultipartFile uploadFile;

    @Override
    public FileEntity toEntity() {
        throw new UnsupportedOperationException("方法未实现");
    }
}
