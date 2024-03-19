package com.sunnysunco.cloud.business.auth.menu;

import com.sunnysunco.cloud.business.auth.user.UserEntity;
import com.sunnysunco.cloud.business.auth.user.UserService;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.base.dto.PageDto;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Transactional
public class MenuService extends BaseService<MenuEntity, PageDto<MenuEntity>> {
    private final MenuMapper menuMapper;

    private final MenuRepository menuRepository;

    private final UserService userService;

    @Override
    public MenuMapper commonBaseMapper() {
        return menuMapper;
    }

    @Override
    public MenuRepository commonBaseRepository() {
        return menuRepository;
    }

    @Override
    public Class<MenuEntity> commonBaseEntityClass() {
        return MenuEntity.class;
    }

    @Override
    public List<MenuEntity> findAll() {
        Specification<MenuEntity> example = (root, query, criteriaBuilder) -> {
            //用列表装载断言对象
            List<Predicate> predicates = new ArrayList<>();
            // 查询 parent_id为空的所有项目
            predicates.add(criteriaBuilder.isNull(root.get("parent")));
            Predicate[] p = new Predicate[predicates.size()];
            return criteriaBuilder.and(predicates.toArray(p));
        };
        return menuRepository.findAll(example);
    }

    @Transactional(readOnly = true)
    public List<MenuEntity> findAdminMenuGroup() {
        Specification<MenuEntity> example = (root, query, criteriaBuilder) -> {
            //用列表装载断言对象
            List<Predicate> predicates = new ArrayList<>();
            // 查询 parent_id为空的所有项目
            predicates.add(criteriaBuilder.isNull(root.get("parent")));
            // 查询menu_group为admin-master的所有项目
            predicates.add(criteriaBuilder.equal(root.get("menuGroup"), "admin-master"));
            // 将断言对象转换为Predicate[]
            Predicate[] p = new Predicate[predicates.size()];
            // 返回所有断言
            return criteriaBuilder.and(predicates.toArray(p));
        };
        List<MenuEntity> menus = menuRepository.findAll(example);
        UserEntity user = userService.getUserInfo();
        Set<String> menuIds = new HashSet<>();
        user.getRoles().forEach(role -> role.getMenus().forEach(menu -> {
            menuIds.add(menu.getId());
        }));
        if (!user.getId().equals("admin")) {
            this.findChildMenu(menus, menuIds);
        }
        return menus;
    }

    /**
     * 递归查询子菜单id是否存在如果不存在则删除
     *
     * @param menus   完整菜单
     * @param menuIds 用户可访问菜单id列表
     */
    @Transactional(readOnly = true)
    public void findChildMenu(List<MenuEntity> menus, Set<String> menuIds) {
        for (int i = 0; i < menus.size(); i++) {
            MenuEntity menu = menus.get(i);
            if (menuIds.contains(menu.getId())) {
                // 递归查询子菜单
                List<MenuEntity> children = menu.getChildren();
                if (ObjectUtils.isNotEmpty(children)) {
                    this.findChildMenu(children, menuIds);
                }
            } else {
                menus.remove(i--);
            }
        }
    }
}
