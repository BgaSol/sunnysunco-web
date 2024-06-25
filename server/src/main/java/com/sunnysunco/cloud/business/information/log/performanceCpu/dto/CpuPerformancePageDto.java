package com.sunnysunco.cloud.business.information.log.performanceCpu.dto;

import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.business.information.log.performanceCpu.CpuPerformanceEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

@EqualsAndHashCode(callSuper = true)
@Schema(description = "CPU性能分页请求数据")
@Data()
public class CpuPerformancePageDto extends BasePageDto<CpuPerformanceEntity> {
}
