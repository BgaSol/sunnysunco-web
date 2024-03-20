package com.sunnysunco.cloud.business.auth.menu;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sunnysunco.cloud.business.auth.menu.dto.CreateMenuDto;
import com.sunnysunco.cloud.business.auth.menu.dto.UpdateMenuDto;
import com.sunnysunco.cloud.business.base.BaseController;
import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.business.base.vo.BaseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "菜单管理")
@RequestMapping("/menu")
@Validated
public class MenuController extends BaseController<
        MenuEntity,
        BasePageDto<MenuEntity>,
        CreateMenuDto,
        UpdateMenuDto> {
    private final MenuService menuService;

    @Override
    public MenuService commonBaseService() {
        return menuService;
    }

//    @Override
//    @PostMapping
//    @Operation(summary = "保存菜单", operationId = "saveMenu")
//    @SaCheckPermission("menu:save")
//    public BaseVo<MenuEntity> save(@RequestBody CreateMenuDto entity) {
//        return super.save(entity);
//    }

//    @Override
//    @PutMapping()
//    @Operation(summary = "更新菜单", operationId = "updateMenu")
//    @SaCheckPermission("menu:update")
//    public BaseVo<MenuEntity> update(@RequestBody UpdateMenuDto entity) {
//        return super.update(entity);
//    }

    @Override
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除菜单", operationId = "deleteMenu")
    @SaCheckPermission("menu:delete")
    public BaseVo<Integer[]> delete(@PathVariable String ids) {
        return super.delete(ids);
    }

//    @Override
//    @GetMapping("/{id}")
//    @Operation(summary = "根据ID查询菜单", operationId = "findById")
//    @SaCheckPermission("menu:findById")
//    public BaseVo<MenuEntity> findById(@PathVariable String id) {
//        return super.findById(id);
//    }

    @Override
    @GetMapping()
    @Operation(summary = "查询所有菜单", operationId = "findAllMenu")
    @SaCheckPermission("menu:findAll")
    public BaseVo<List<MenuEntity>> findAll() {
        return super.findAll();
    }

    @GetMapping("/admin-menu-group")
    @Operation(summary = "查询管理员菜单组", operationId = "findAdminMenuGroup")
    @SaCheckPermission("menu:findAdminMenuGroup")
    public BaseVo<List<MenuEntity>> findAdminMenuGroup() {
        return BaseVo.success(this.menuService.findAdminMenuGroup());
    }
}
