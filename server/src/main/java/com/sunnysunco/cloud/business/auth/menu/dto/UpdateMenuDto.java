package com.sunnysunco.cloud.business.auth.menu.dto;

import com.sunnysunco.cloud.business.auth.menu.MenuEntity;
import com.sunnysunco.cloud.business.base.dto.BaseUpdateDto;

public class UpdateMenuDto extends BaseUpdateDto<MenuEntity> {
    @Override
    public MenuEntity toEntity() {
        throw new UnsupportedOperationException("Not implemented yet");
    }
}
