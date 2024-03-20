package com.sunnysunco.cloud.business.information.file;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sunnysunco.cloud.business.base.BaseController;
import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.business.base.vo.BaseVo;
import com.sunnysunco.cloud.business.information.file.dto.CreateFileDto;
import com.sunnysunco.cloud.business.information.file.dto.UpdateFileDto;
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
@Tag(name = "文件管理")
@RequestMapping("/file")
public class FileController extends BaseController<
        FileEntity,
        BasePageDto<FileEntity>,
        CreateFileDto,
        UpdateFileDto> {
    private final FileService fileService;

    @Override
    public FileService commonBaseService() {
        return fileService;
    }

//    @Override
//    @Operation(summary = "分页查询文件", operationId = "findByFilePage")
//    public BaseVo<PageVo<FileEntity>> findByPage(PageDto<FileEntity> pageDto) {
//        return super.findByPage(pageDto);
//    }

    @Override
    @PostMapping()
    @Operation(summary = "保存|上传文件", operationId = "saveFile")
    @SaCheckPermission("file:save")
    public BaseVo<FileEntity> save(@Valid CreateFileDto entity) {
        return BaseVo.success(fileService.save(entity), "文件上传成功");
    }

//    @Override
//    @Operation(summary = "更新文件", operationId = "updateFile")
//    public BaseVo<FileEntity> update(@RequestBody UpdateFileDto entity) {
//        return super.update(entity);
//    }

    //    @Override
//    @Operation(summary = "删除文件", operationId = "deleteFile")
//    public BaseVo<Integer[]> delete(String ids) {
//        return super.delete(ids);
//    }
//
    @Override
    @GetMapping("/{id}")
    @Operation(summary = "根据id查询文件", operationId = "findFileById")
    @SaCheckPermission("file:findById")
    public BaseVo<FileEntity> findById(@PathVariable String id) {
        return super.findById(id);
    }

    @GetMapping("/download/{id}")
    @Operation(summary = "下载文件", operationId = "downloadFile")
    @SaCheckPermission("file:download")
    public ResponseEntity<InputStreamResource> download(@PathVariable String id) {
        GetObjectResponse fileStream = this.fileService.getFileStream(id);
        return ResponseEntity.ok()
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(fileStream));
    }
}
