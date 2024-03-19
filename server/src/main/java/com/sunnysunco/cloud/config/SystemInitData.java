package com.sunnysunco.cloud.config;

import com.sunnysunco.cloud.business.auth.department.DepartmentEntity;
import com.sunnysunco.cloud.business.auth.department.DepartmentMapper;
import com.sunnysunco.cloud.business.auth.menu.MenuEntity;
import com.sunnysunco.cloud.business.auth.menu.MenuMapper;
import com.sunnysunco.cloud.business.auth.menu.MenuType;
import com.sunnysunco.cloud.business.auth.user.UserEntity;
import com.sunnysunco.cloud.business.auth.user.UserMapper;
import lombok.AllArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@AllArgsConstructor
public class SystemInitData implements ApplicationRunner {

    private final UserMapper userMapper;

    private final DepartmentMapper departmentMapper;

    private final MenuMapper menuMapper;

    @Override
    public void run(ApplicationArguments args) {
        // 创建菜单
        initMenus();
        // 创建部门
        initDepartment();
        // 创建用户
        initUsers();
    }

    public void initMenus() {
        // 后台系统路由前缀 admin
        // 后台系统菜单组 admin-master
        {
            MenuEntity systemAuth = new MenuEntity();
            systemAuth.setId("systemAuth");
            systemAuth.setName("系统身份验证");
            systemAuth.setType(MenuType.MENU);
            systemAuth.setIcon("Lock");
            systemAuth.setMenuGroup("admin-master");
            if (menuMapper.selectById(systemAuth.getId()) == null) {
                menuMapper.insert(systemAuth);
            }
            {
                MenuEntity role = new MenuEntity();
                role.setId("role");
                role.setName("角色管理");
                role.setParentId(systemAuth.getId());
                role.setType(MenuType.PAGE);
                role.setPath("/admin/role");
                role.setRouteName("admin_role");
                role.setIcon("Crop");
                role.setMenuGroup("admin-master");
                if (menuMapper.selectById(role.getId()) == null) {
                    menuMapper.insert(role);
                }

                MenuEntity user = new MenuEntity();
                user.setId("user");
                user.setName("用户管理");
                user.setParentId(systemAuth.getId());
                user.setType(MenuType.PAGE);
                user.setPath("/admin/user");
                user.setRouteName("admin_user");
                user.setIcon("User");
                user.setMenuGroup("admin-master");
                if (menuMapper.selectById(user.getId()) == null) {
                    menuMapper.insert(user);
                }

                MenuEntity permission = new MenuEntity();
                permission.setId("permission");
                permission.setName("权限管理");
                permission.setParentId(systemAuth.getId());
                permission.setType(MenuType.PAGE);
                permission.setPath("/admin/permission");
                permission.setRouteName("admin_permission");
                permission.setIcon("Operation");
                permission.setMenuGroup("admin-master");
                if (menuMapper.selectById(permission.getId()) == null) {
                    menuMapper.insert(permission);
                }

                MenuEntity menu = new MenuEntity();
                menu.setId("menu");
                menu.setName("菜单管理");
                menu.setParentId(systemAuth.getId());
                menu.setType(MenuType.PAGE);
                menu.setPath("/admin/menu");
                menu.setRouteName("admin_menu");
                menu.setIcon("Menu");
                menu.setMenuGroup("admin-master");
                if (menuMapper.selectById(menu.getId()) == null) {
                    menuMapper.insert(menu);
                }

                MenuEntity clientPage = new MenuEntity();
                clientPage.setId("clientPage");
                clientPage.setName("自定义页面");
                clientPage.setParentId(systemAuth.getId());
                clientPage.setType(MenuType.PAGE);
                clientPage.setPath("/admin/client-page");
                clientPage.setRouteName("admin_client_page");
                clientPage.setIcon("Memo");
                clientPage.setMenuGroup("admin-master");
                if (menuMapper.selectById(clientPage.getId()) == null) {
                    menuMapper.insert(clientPage);
                }

                MenuEntity department = new MenuEntity();
                department.setId("department");
                department.setName("部门管理");
                department.setParentId(systemAuth.getId());
                department.setType(MenuType.PAGE);
                department.setPath("/admin/department");
                department.setRouteName("admin_department");
                department.setIcon("Connection");
                department.setMenuGroup("admin-master");
                if (menuMapper.selectById(department.getId()) == null) {
                    menuMapper.insert(department);
                }

                MenuEntity tag = new MenuEntity();
                tag.setId("tag");
                tag.setName("标签管理");
                tag.setParentId(systemAuth.getId());
                tag.setType(MenuType.PAGE);
                tag.setPath("/admin/tag");
                tag.setRouteName("admin_tag");
                tag.setIcon("Paperclip");
                tag.setMenuGroup("admin-master");
                if (menuMapper.selectById(tag.getId()) == null) {
                    menuMapper.insert(tag);
                }
            }
        }
        {
            MenuEntity systemInformation = new MenuEntity();
            systemInformation.setId("systemInformation");
            systemInformation.setName("系统信息");
            systemInformation.setType(MenuType.MENU);
            systemInformation.setIcon("View");
            systemInformation.setMenuGroup("admin-master");
            if (menuMapper.selectById(systemInformation.getId()) == null) {
                menuMapper.insert(systemInformation);
            }
            {
                MenuEntity log = new MenuEntity();
                log.setId("log");
                log.setName("日志管理");
                log.setParentId(systemInformation.getId());
                log.setType(MenuType.PAGE);
                log.setPath("/admin/log");
                log.setRouteName("admin_log");
                log.setIcon("Document");
                log.setMenuGroup("admin-master");
                if (menuMapper.selectById(log.getId()) == null) {
                    menuMapper.insert(log);
                }
            }
            {
                MenuEntity image = new MenuEntity();
                image.setId("image");
                image.setName("图片管理");
                image.setParentId(systemInformation.getId());
                image.setType(MenuType.PAGE);
                image.setPath("/admin/image");
                image.setRouteName("admin_image");
                image.setIcon("Picture");
                image.setMenuGroup("admin-master");
                if (menuMapper.selectById(image.getId()) == null) {
                    menuMapper.insert(image);
                }
            }
        }
    }

    private void initDepartment() {
        DepartmentEntity department = new DepartmentEntity();
        department.setName("默认部门");
        department.setDescription("系统必须要有一个部门，用于存放没有部门的用户");
        department.setId("default");
        if (this.departmentMapper.selectById(department.getId()) == null) {
            this.departmentMapper.insert(department);
        }
    }

    public void initUsers() {
        UserEntity admin = new UserEntity();
        admin.setId("admin");
        admin.setUsername("admin");
        admin.setPassword("admin");
        admin.setNickname("admin");
        admin.setLocked(false);
        admin.setDescription("超级管理员用户,无需配置权限,拥有系统最高权限");
        admin.setDepartmentId("default");
        // 账号时间100年
        // long now = System.currentTimeMillis();
        // admin.setActivatedAt(new Date(now));
        // admin.setDeactivatedAt(new Date(now + 100L * 365 * 24 * 60 * 60 * 1000));
        if (this.userMapper.selectById(admin.getId()) == null) {
            this.userMapper.insert(admin);
        }
    }
}
