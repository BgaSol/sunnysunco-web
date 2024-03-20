package com.sunnysunco.cloud.business.auth.role;

import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class RoleService extends BaseService<RoleEntity, BasePageDto<RoleEntity>> {
    private final RoleMapper roleMapper;

    private final RoleRepository roleRepository;

    @Override
    public RoleMapper commonBaseMapper() {
        return roleMapper;
    }

    @Override
    public RoleRepository commonBaseRepository() {
        return roleRepository;
    }

    @Override
    public Class<RoleEntity> commonBaseEntityClass() {
        return RoleEntity.class;
    }
}
