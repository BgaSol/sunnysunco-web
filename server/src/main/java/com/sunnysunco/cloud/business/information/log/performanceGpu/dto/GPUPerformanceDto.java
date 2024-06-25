package com.sunnysunco.cloud.business.information.log.performanceGpu.dto;

import com.sunnysunco.cloud.business.base.dto.BaseCreateDto;
import com.sunnysunco.cloud.business.information.log.performanceGpu.GpuPerformanceEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

@Data
@Schema(description = "GPU性能Dto")
public class GPUPerformanceDto extends BaseCreateDto<GpuPerformanceEntity> {
    @Schema(description = "GPU索引")
    private Integer index;

    @Schema(description = "GPU名称")
    private String name;

    @Schema(description = "GPU使用率(%)")
    private Double usagePercent;

    @Schema(description = "显存使用率(%)")
    private Double memoryPercent;

    @Schema(description = "显存总量(MB)")
    private Double memoryTotal;

    @Schema(description = "显存使用量(MB)")
    private Double memoryUsage;

    @Schema(description = "GPU温度(°C)")
    private Double temperature;

    @Schema(description = "GPU功耗(W)")
    private Double power;

    @Schema(description = "风扇")
    private String fan;

    @Schema(description = "时间")
    private Date time;

    @Override
    public String toString() {
        return "GPU" + index + " " + name + ": " + String.format("%.2f", usagePercent) + "% " +
                String.format("%.2f", memoryUsage) + "MB/" + String.format("%.2f", memoryTotal) + "MB " + String.format("%.2f", memoryPercent) + "% " +
                String.format("%.2f", temperature) + "°C " + String.format("%.2f", power) + "W " + "fan" + fan + "%";
    }

    @Override
    public GpuPerformanceEntity toEntity() {
        GpuPerformanceEntity entity = new GpuPerformanceEntity();
        entity.setIndex(index);
        entity.setName(name);
        entity.setUsagePercent(usagePercent);
        entity.setMemoryPercent(memoryPercent);
        entity.setMemoryTotal(memoryTotal);
        entity.setMemoryUsage(memoryUsage);
        entity.setTemperature(temperature);
        entity.setPower(power);
        entity.setFan(fan);
        entity.setCreateTime(time);
        return entity;
    }
}
