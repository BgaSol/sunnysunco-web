package com.sunnysunco.cloud.business.auth.role;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sunnysunco.cloud.business.auth.role.dto.CreateRoleDto;
import com.sunnysunco.cloud.business.auth.role.dto.UpdateRoleDto;
import com.sunnysunco.cloud.business.base.BaseController;
import com.sunnysunco.cloud.business.base.dto.PageDto;
import com.sunnysunco.cloud.business.base.vo.BaseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "角色管理")
@RequestMapping("/role")
@Validated
public class RoleController extends BaseController<
        RoleEntity,
        PageDto<RoleEntity>,
        CreateRoleDto,
        UpdateRoleDto> {
    private final RoleService roleService;

    @Override
    public RoleService commonBaseService() {
        return roleService;
    }

    @Override
    @PostMapping()
    @Operation(summary = "新增角色", operationId = "saveRole")
    @SaCheckPermission("role:save")
    public BaseVo<RoleEntity> save(@RequestBody @Valid CreateRoleDto entity) {
        return super.save(entity);
    }

    @Override
    @PutMapping()
    @Operation(summary = "更新角色", operationId = "updateRole")
    @SaCheckPermission("role:update")
    public BaseVo<RoleEntity> update(@RequestBody @Valid UpdateRoleDto entity) {
        return super.update(entity);
    }

    @Override
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除角色", operationId = "deleteRole")
    @SaCheckPermission("role:delete")
    public BaseVo<Integer[]> delete(@PathVariable String ids) {
        return super.delete(ids);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "根据ID查询角色", operationId = "findRoleById")
    @SaCheckPermission("role:findById")
    public BaseVo<RoleEntity> findById(@PathVariable String id) {
        return super.findById(id);
    }

    @Override
    @GetMapping()
    @Operation(summary = "查询所有角色", operationId = "findAllRole")
    @SaCheckPermission("role:findAll")
    public BaseVo<List<RoleEntity>> findAll() {
        return super.findAll();
    }
}
