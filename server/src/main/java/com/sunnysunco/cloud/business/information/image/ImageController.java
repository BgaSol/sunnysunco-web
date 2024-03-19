package com.sunnysunco.cloud.business.information.image;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sunnysunco.cloud.business.base.BaseController;
import com.sunnysunco.cloud.business.base.vo.BaseVo;
import com.sunnysunco.cloud.business.base.vo.PageVo;
import com.sunnysunco.cloud.business.information.image.dto.CreateImageDto;
import com.sunnysunco.cloud.business.information.image.dto.ImagePageDto;
import com.sunnysunco.cloud.business.information.image.dto.UpdateImageDto;
import io.minio.GetObjectResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tag(name = "图片管理")
@RequestMapping("/image")
public class ImageController extends BaseController<
        ImageEntity,
        ImagePageDto,
        CreateImageDto,
        UpdateImageDto> {
    private final ImageService imageService;

    @Override
    public ImageService commonBaseService() {
        return imageService;
    }

    @Override
    @PostMapping()
    @Operation(summary = "新增图片", operationId = "saveImage")
    @SaCheckPermission("image:save")
    public BaseVo<ImageEntity> save(@RequestBody @Valid CreateImageDto entity) {
        return super.save(entity);
    }

    @Override
    @PutMapping()
    @Operation(summary = "更新图片", operationId = "updateImage")
    @SaCheckPermission("image:update")
    public BaseVo<ImageEntity> update(@RequestBody @Valid UpdateImageDto entity) {
        return super.update(entity);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "查询图片", operationId = "findImageById")
    @SaCheckPermission("image:findById")
    public BaseVo<ImageEntity> findById(@PathVariable String id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/page")
    @Operation(summary = "分页查询图片", operationId = "findByImagePage")
    @SaCheckPermission("image:findByPage")
    public BaseVo<PageVo<ImageEntity>> findByPage(@RequestBody @Valid ImagePageDto pageDto) {
        return super.findByPage(pageDto);
    }

    @Override
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除图片", operationId = "deleteImage")
    @SaCheckPermission("image:delete")
    public BaseVo<Integer[]> delete(@PathVariable String ids) {
        return super.delete(ids);
    }

    @GetMapping("/download/{id}")
    @Operation(summary = "下载图片", operationId = "downloadImage")
    @SaCheckPermission("image:download")
    public ResponseEntity<InputStreamResource> download(@PathVariable String id) {
        GetObjectResponse fileStream = this.imageService.getImageStream(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(fileStream));
    }
}
