package com.sunnysunco.cloud.business.information.tag;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sunnysunco.cloud.business.base.BaseController;
import com.sunnysunco.cloud.business.base.vo.BaseVo;
import com.sunnysunco.cloud.business.base.vo.PageVo;
import com.sunnysunco.cloud.business.information.tag.dto.CreateTagDto;
import com.sunnysunco.cloud.business.information.tag.dto.TagPageDto;
import com.sunnysunco.cloud.business.information.tag.dto.UpdateTagDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tag(name = "标签管理")
@RequestMapping("/tag")
public class TagController extends BaseController<
        TagEntity,
        TagPageDto,
        CreateTagDto,
        UpdateTagDto> {
    private final TagService tagService;

    @Override
    public TagService commonBaseService() {
        return tagService;
    }

    @Override
    @PostMapping()
    @Operation(summary = "新增标签", operationId = "saveTag")
    @SaCheckPermission("tag:save")
    public BaseVo<TagEntity> save(@RequestBody @Valid CreateTagDto dto) {
        return super.save(dto);
    }


    @Override
    @PutMapping()
    @Operation(summary = "更新标签", operationId = "updateTag")
    @SaCheckPermission("tag:update")
    public BaseVo<TagEntity> update(@RequestBody @Valid UpdateTagDto entity) {
        return super.update(entity);
    }

    @Override
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除标签", operationId = "deleteTag")
    @SaCheckPermission("tag:delete")
    public BaseVo<Integer[]> delete(@PathVariable String ids) {
        return super.delete(ids);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "查询标签", operationId = "findTagById")
    @SaCheckPermission("tag:findById")
    public BaseVo<TagEntity> findById(@PathVariable String id) {
        return super.findById(id);
    }

    @Override
    @PostMapping("/page")
    @Operation(summary = "分页查询标签", operationId = "findTagByPage")
    @SaCheckPermission("tag:findByPage")
    public BaseVo<PageVo<TagEntity>> findByPage(@RequestBody @Valid TagPageDto pageDto) {
        return super.findByPage(pageDto);
    }
}
