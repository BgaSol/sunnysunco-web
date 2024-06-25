package com.sunnysunco.cloud.business.information.log.performanceGpu.dto;

import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.business.information.log.performanceGpu.GpuPerformanceEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "GPU性能分页请求数据")
@Data()
public class GpuPerformancePageDto extends BasePageDto<GpuPerformanceEntity> {
}
