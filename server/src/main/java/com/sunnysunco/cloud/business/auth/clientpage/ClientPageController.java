package com.sunnysunco.cloud.business.auth.clientpage;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sunnysunco.cloud.business.auth.clientpage.dto.ClientPagePageDto;
import com.sunnysunco.cloud.business.auth.clientpage.dto.CreateClientPageDto;
import com.sunnysunco.cloud.business.auth.clientpage.dto.UpdateClientPageDto;
import com.sunnysunco.cloud.business.base.BaseController;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.base.vo.BaseVo;
import com.sunnysunco.cloud.business.base.vo.PageVo;
import io.minio.GetObjectResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "客户端自定义页面")
@RequestMapping("/client-page")
public class ClientPageController extends BaseController<
        ClientPageEntity,
        ClientPagePageDto,
        CreateClientPageDto,
        UpdateClientPageDto> {

    private final ClientPageService clientPageService;

    @Override
    public BaseService<ClientPageEntity, ClientPagePageDto> commonBaseService() {
        return clientPageService;
    }

    @Override
    @PostMapping()
    @Operation(summary = "保存页面", operationId = "saveClientPage")
    @SaCheckPermission("clientPage:save")
    public BaseVo<ClientPageEntity> save(@RequestBody CreateClientPageDto entity) {
        return super.save(entity);
    }

    @Override
    @PostMapping("/page")
    @Operation(summary = "分页查询页面", operationId = "findByClientPagePage")
    @SaCheckPermission("clientPage:findByPage")
    public BaseVo<PageVo<ClientPageEntity>> findByPage(@RequestBody @Valid ClientPagePageDto pageDto) {
        return super.findByPage(pageDto);
    }

    @Override
    @PutMapping()
    @Operation(summary = "更新页面", operationId = "updateClientPage")
    @SaCheckPermission("clientPage:update")
    public BaseVo<ClientPageEntity> update(@RequestBody @Valid UpdateClientPageDto entity) {
        return super.update(entity);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "根据id查询页面", operationId = "findByIdClientPage")
    @SaCheckPermission("clientPage:findById")
    public BaseVo<ClientPageEntity> findById(@PathVariable String id) {
        return super.findById(id);
    }

    @Override
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除页面", operationId = "deleteClientPage")
    @SaCheckPermission("clientPage:delete")
    public BaseVo<Integer[]> delete(@PathVariable String ids) {
        return super.delete(ids);
    }

    @Override
    @GetMapping()
    @Operation(summary = "查询所有页面", operationId = "findAllClientPage")
    @SaCheckPermission("clientPage:findAll")
    public BaseVo<List<ClientPageEntity>> findAll() {
        return super.findAll();
    }

    @GetMapping("/get-my-client-pages")
    @Operation(summary = "查询我的页面", operationId = "getMyClientPages")
    public BaseVo<List<ClientPageEntity>> getMyPages(HttpServletRequest request) {
        // 获取域名
        String domain = request.getServerName();
        List<ClientPageEntity> myClientPages = clientPageService.getMyClientPages(domain);
        return BaseVo.success(myClientPages);
    }

    @GetMapping("/get-my-client-page-image/{imageId}")
    @Operation(summary = "查询我的页面图片", operationId = "getMyClientPageImage")
    public ResponseEntity<InputStreamResource> getMyClientPageImage(HttpServletRequest request, @PathVariable String imageId) {
        // 获取域名
        String domain = request.getServerName();
        GetObjectResponse fileStream = clientPageService.getMyClientPageImage(domain, imageId);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(fileStream));
    }
}
