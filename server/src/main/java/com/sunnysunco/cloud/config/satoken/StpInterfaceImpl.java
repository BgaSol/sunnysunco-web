package com.sunnysunco.cloud.config.satoken;

import cn.dev33.satoken.exception.NotLoginException;
import cn.dev33.satoken.stp.StpInterface;
import com.sunnysunco.cloud.business.auth.permission.PermissionEntity;
import com.sunnysunco.cloud.business.auth.role.RoleEntity;
import com.sunnysunco.cloud.business.auth.user.UserEntity;
import com.sunnysunco.cloud.business.auth.user.UserService;
import com.sunnysunco.cloud.business.base.exception.BaseException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 自定义权限加载接口实现类
 * todo 缓存未实现
 */
@Component
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StpInterfaceImpl implements StpInterface {

    private final UserService userService;

    /**
     * 返回一个账号所拥有的权限码集合
     */
    @Override
    @Transactional(readOnly = true)
    public List<String> getPermissionList(Object loginId, String loginType) {
        UserEntity user = this.getUser((String) loginId, loginType);
        Set<String> permissions = new HashSet<>();
        if (user.getId().equals("admin")) {
            permissions.add("*");
        } else {
            for (RoleEntity role : user.getRoles()) {
                for (PermissionEntity permission : role.getPermissions()) {
                    permissions.add(permission.getCode());
                }
            }
        }
        return new ArrayList<>(permissions);
    }

    /**
     * 返回一个账号所拥有的角色标识集合 (权限与角色可分开校验)
     */
    @Override
    @Transactional(readOnly = true)
    public List<String> getRoleList(Object loginId, String loginType) {
        UserEntity user = this.getUser((String) loginId, loginType);
        Set<String> roles = new HashSet<>();
        if (user.getId().equals("admin")) {
            roles.add("*");
        } else {
            for (RoleEntity role : user.getRoles()) {
                roles.add(role.getCode());
            }
        }
        return new ArrayList<>(roles);
    }

    public UserEntity getUser(String userId, String loginType) {
        try {
            return userService.getUserInfo(userId);
        } catch (BaseException e) {
            throw new NotLoginException(e.getMessage(), loginType, NotLoginException.INVALID_TOKEN);
        }
    }
}