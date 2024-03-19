package com.sunnysunco.cloud.business.auth.permission;

import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.base.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class PermissionService extends BaseService<PermissionEntity, PageDto<PermissionEntity>> {
    private final PermissionMapper permissionMapper;

    private final PermissionRepository permissionRepository;

    @Override
    public PermissionMapper commonBaseMapper() {
        return permissionMapper;
    }

    @Override
    public PermissionRepository commonBaseRepository() {
        return permissionRepository;
    }

    @Override
    public Class<PermissionEntity> commonBaseEntityClass() {
        return PermissionEntity.class;
    }
}
