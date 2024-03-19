package com.sunnysunco.cloud.business.auth.menu.dto;

import com.sunnysunco.cloud.business.auth.menu.MenuEntity;
import com.sunnysunco.cloud.business.base.dto.BaseCreateDto;

public class CreateMenuDto extends BaseCreateDto<MenuEntity> {
    @Override
    public MenuEntity toEntity() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
