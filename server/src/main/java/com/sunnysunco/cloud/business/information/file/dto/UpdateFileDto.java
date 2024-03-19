package com.sunnysunco.cloud.business.information.file.dto;

import com.sunnysunco.cloud.business.base.dto.BaseUpdateDto;
import com.sunnysunco.cloud.business.information.file.FileEntity;

public class UpdateFileDto extends BaseUpdateDto<FileEntity> {
    @Override
    public FileEntity toEntity() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
