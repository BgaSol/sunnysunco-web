package com.sunnysunco.cloud.business.auth.permission;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sunnysunco.cloud.business.auth.permission.dto.CreatePermissionDto;
import com.sunnysunco.cloud.business.auth.permission.dto.UpdatePermissionDto;
import com.sunnysunco.cloud.business.base.BaseController;
import com.sunnysunco.cloud.business.base.dto.PageDto;
import com.sunnysunco.cloud.business.base.vo.BaseVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "权限管理")
@RequestMapping("/permission")
public class PermissionController extends BaseController<
        PermissionEntity,
        PageDto<PermissionEntity>,
        CreatePermissionDto,
        UpdatePermissionDto> {
    private final PermissionService permissionService;

    @Override
    public PermissionService commonBaseService() {
        return permissionService;
    }

    @Override
    @GetMapping()
    @Operation(summary = "查询所有权限", operationId = "findAllPermission")
    @SaCheckPermission("permission:findAll")
    public BaseVo<List<PermissionEntity>> findAll() {
        return super.findAll();
    }

    @Override
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除权限", operationId = "deletePermission")
    @SaCheckPermission("permission:delete")
    public BaseVo<Integer[]> delete(@PathVariable String ids) {
        return super.delete(ids);
    }
}
