package com.sunnysunco.cloud.business.base.dto;

import com.sunnysunco.cloud.business.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data()
@Schema(description = "分页查询参数")
public abstract class PageDto<T extends BaseEntity> {
    @NotNull(message = "页码不能为空")
    @Min(value = 1, message = "页码不能小于1")
    @Schema(description = "页码")
    private Integer page;

    @NotNull(message = "每页条数不能为空")
    @Min(value = 1, message = "每页请求数量过小")
    @Max(value = 1000, message = "每页请求数量过大")
    @Schema(description = "每页条数")
    private Integer size;

    @Schema(description = "排序字段")
    private String sort;

    @Schema(description = "排序方式:asc, desc")
    private PageOrderType order;

    @Schema(hidden = true)
    public Pageable getPageAble() {
        this.initOrderMeta();
        Sort by = Sort.by(Sort.Direction.fromString(order.getValue()), sort);
        return PageRequest.of(page - 1, size, by);
    }

    @Schema(hidden = true)
    public void initOrderMeta() {
        // 如果sort为空，则默认使用id降序
        if (sort == null || sort.isEmpty()) {
            sort = "createTime";
        }
        // 如果order为空，则默认使用降序
        if (order == null) {
            order = PageOrderType.DESC;
        }
    }

    @Schema(hidden = true)
    public Specification<T> getExample() {
        return null;
    }
}
