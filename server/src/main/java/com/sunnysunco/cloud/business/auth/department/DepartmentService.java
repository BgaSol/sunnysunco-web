package com.sunnysunco.cloud.business.auth.department;

import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunnysunco.cloud.business.auth.user.UserService;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.base.dto.PageDto;
import com.sunnysunco.cloud.business.information.image.ImageService;
import io.minio.GetObjectResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class DepartmentService extends BaseService<DepartmentEntity, PageDto<DepartmentEntity>> {
    private final DepartmentMapper departmentMapper;

    private final DepartmentRepository departmentRepository;

    private final UserService userService;

    private final ImageService imageService;

    @Override
    public DepartmentMapper commonBaseMapper() {
        return departmentMapper;
    }

    @Override
    public DepartmentRepository commonBaseRepository() {
        return departmentRepository;
    }

    @Override
    public Class<DepartmentEntity> commonBaseEntityClass() {
        return DepartmentEntity.class;
    }

    /**
     * 获取当前登录用户的部门logo
     * 未登录则获取默认部门logo
     *
     * @return 图片流
     */
    @Transactional(readOnly = true)
    public GetObjectResponse getLogoStream(String domain) {
        DepartmentEntity department = this.getMyDepartment(domain);
        String imageId = department.getIcon().getId();
        return imageService.getImageStream(imageId);
    }

    @Transactional(readOnly = true)
    public DepartmentEntity getMyDepartment(String domain) {
        boolean login = StpUtil.isLogin();
        String departmentId = "default";
        if (login) {
            String userId = StpUtil.getLoginIdAsString();
            departmentId = userService.findById(userId).getDepartment().getId();
        } else if (ObjectUtils.isNotEmpty(domain)) {
            QueryWrapper<DepartmentEntity> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("domain", domain);
            DepartmentEntity departmentEntity = this.departmentMapper.selectOne(queryWrapper);
            if (ObjectUtils.isNotEmpty(departmentEntity)) {
                departmentId = departmentEntity.getId();
            }
        }
        return this.findById(departmentId);
    }
}
