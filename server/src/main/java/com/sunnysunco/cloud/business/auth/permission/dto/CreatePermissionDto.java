package com.sunnysunco.cloud.business.auth.permission.dto;

import com.sunnysunco.cloud.business.auth.permission.PermissionEntity;
import com.sunnysunco.cloud.business.base.dto.BaseCreateDto;

public class CreatePermissionDto extends BaseCreateDto<PermissionEntity> {
    @Override
    public PermissionEntity toEntity() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
