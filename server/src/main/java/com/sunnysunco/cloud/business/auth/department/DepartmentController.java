package com.sunnysunco.cloud.business.auth.department;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sunnysunco.cloud.business.auth.department.dto.CreateDepartmentDto;
import com.sunnysunco.cloud.business.auth.department.dto.UpdateDepartmentDto;
import com.sunnysunco.cloud.business.base.BaseController;
import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.business.base.vo.BaseVo;
import io.minio.GetObjectResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "部门管理")
@RequestMapping("/department")
@Validated
public class DepartmentController extends BaseController<
        DepartmentEntity,
        BasePageDto<DepartmentEntity>,
        CreateDepartmentDto,
        UpdateDepartmentDto> {
    private final DepartmentService departmentService;

    @Override
    public DepartmentService commonBaseService() {
        return departmentService;
    }

    @Override
    @PostMapping()
    @SaCheckPermission("department:save")
    @Operation(summary = "新增部门", operationId = "saveDepartment")
    public BaseVo<DepartmentEntity> save(@RequestBody @Valid CreateDepartmentDto entity) {
        return super.save(entity);
    }

    @Override
    @PutMapping()
    @SaCheckPermission("department:update")
    @Operation(summary = "更新部门", operationId = "updateDepartment")
    public BaseVo<DepartmentEntity> update(@RequestBody @Valid UpdateDepartmentDto entity) {
        return super.update(entity);
    }

    @Override
    @DeleteMapping("/{ids}")
    @SaCheckPermission("department:delete")
    @Operation(summary = "删除部门", operationId = "deleteDepartment")
    public BaseVo<Integer[]> delete(@PathVariable String ids) {
        return super.delete(ids);
    }

    @Override
    @GetMapping("/{id}")
    @SaCheckPermission("department:findById")
    @Operation(summary = "根据ID查询部门", operationId = "findDepartmentById")
    public BaseVo<DepartmentEntity> findById(@PathVariable String id) {
        return super.findById(id);
    }

    @Override
    @GetMapping()
    @SaCheckPermission("department:findAll")
    @Operation(summary = "查询所有部门", operationId = "findAllDepartment")
    public BaseVo<List<DepartmentEntity>> findAll() {
        return super.findAll();
    }

    @GetMapping("/findByIdIsDefault")
    @Operation(summary = "查询默认部门", operationId = "findDefaultDepartment")
    public BaseVo<DepartmentEntity> findById() {
        return super.findById("default");
    }

    @GetMapping("/downloadLogo")
    @Operation(summary = "获取部门Logo", operationId = "downloadImage")
    public ResponseEntity<InputStreamResource> download(HttpServletRequest request) {
        // 获取域名
        String domain = request.getServerName();
        GetObjectResponse fileStream = this.departmentService.getLogoStream(domain);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(fileStream));
    }

    @GetMapping("/getMyDepartment")
    @Operation(summary = "获取我的部门", operationId = "getMyDepartment")
    public BaseVo<DepartmentEntity> getMyDepartment(HttpServletRequest request) {
        // 获取域名
        String domain = request.getServerName();
        return BaseVo.success(departmentService.getMyDepartment(domain));
    }
}
