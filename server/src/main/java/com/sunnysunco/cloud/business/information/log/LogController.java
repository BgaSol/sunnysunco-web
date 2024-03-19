package com.sunnysunco.cloud.business.information.log;

import cn.dev33.satoken.annotation.SaCheckPermission;
import com.sunnysunco.cloud.business.base.BaseController;
import com.sunnysunco.cloud.business.base.dto.BaseCreateDto;
import com.sunnysunco.cloud.business.base.dto.BaseUpdateDto;
import com.sunnysunco.cloud.business.base.vo.BaseVo;
import com.sunnysunco.cloud.business.base.vo.PageVo;
import com.sunnysunco.cloud.business.information.log.dto.LogPageDto;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@Tag(name = "日志管理")
@RequestMapping("/log")
@Validated
public class LogController extends BaseController<
        LogEntity,
        LogPageDto,
        BaseCreateDto<LogEntity>,
        BaseUpdateDto<LogEntity>> {
    private final LogService logService;

    @Override
    public LogService commonBaseService() {
        return logService;
    }

    @Override
    @PostMapping("/page")
    @Operation(summary = "分页查询日志", operationId = "findByLogPage")
    @SaCheckPermission("log:findByPage")
    public BaseVo<PageVo<LogEntity>> findByPage(@RequestBody @Valid LogPageDto pageDto) {
        return super.findByPage(pageDto);
    }

    @Override
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除菜单", operationId = "deleteLog")
    @SaCheckPermission("log:delete")
    public BaseVo<Integer[]> delete(@PathVariable String ids) {
        return super.delete(ids);
    }
}
