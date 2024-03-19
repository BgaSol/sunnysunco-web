package com.sunnysunco.cloud.business.base.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.domain.Page;

import java.util.List;

@Data()
@Schema(description = "分页响应数据")
public class PageVo<ENTITY> {
    @Schema(description = "总条数")
    private Long total;

    @Schema(description = "当前页码")
    private Long page;

    @Schema(description = "每页条数")
    private Long size;

    @Schema(description = "响应数据")
    private List<ENTITY> result;

    public PageVo(Page<ENTITY> data) {
        this.total = data.getTotalElements();
        this.result = data.getContent();
    }
}
