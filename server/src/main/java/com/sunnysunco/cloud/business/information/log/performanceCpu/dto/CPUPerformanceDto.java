package com.sunnysunco.cloud.business.information.log.performanceCpu.dto;

import com.sunnysunco.cloud.business.base.dto.BaseCreateDto;
import com.sunnysunco.cloud.business.information.log.performanceCpu.CpuPerformanceEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Schema(description = "CPU性能Dto")
public class CPUPerformanceDto extends BaseCreateDto<CpuPerformanceEntity> {
    @Schema(description = "CPU使用率(%)")
    private Double usagePercent;

    @Schema(description = "CPU每个核心使用率")
    private List<Double> coreUsagePercent;

    @Schema(description = "CPU型号")
    private String model;

    @Schema(description = "CPU温度(°C)")
    private Double temperature;

    @Schema(description = "CPU功耗(W)")
    private Double power;

    @Schema(description = "内存使用率(%)")
    private Double memoryPercent;

    @Schema(description = "内存总量(MB)")
    private Double memoryTotal;

    @Schema(description = "内存使用量(MB)")
    private Double memoryUsage;

    @Schema(description = "时间")
    private Date time;

    @Override
    public String toString() {
        return model + ": " + String.format("%.2f", usagePercent) + "% " +
                String.format("%.2f", memoryUsage) + "MB/" + String.format("%.2f", memoryTotal) + "MB " + String.format("%.2f", memoryPercent) + "%\n" +
                "Cores: " + coreUsagePercent.stream().map(num -> String.format("%.2f", num) + "%").collect(Collectors.toList());
    }

    @Override
    public CpuPerformanceEntity toEntity() {
        CpuPerformanceEntity cpuPerformanceEntity = new CpuPerformanceEntity();
        cpuPerformanceEntity.setUsagePercent(usagePercent);
        // 将coreUsagePercent 转为 逗号分割的字符串
        cpuPerformanceEntity.setCoreUsagePercent(coreUsagePercent.stream().map(num -> String.format("%.2f", num)).collect(Collectors.joining(",")));
        cpuPerformanceEntity.setModel(model);
        cpuPerformanceEntity.setTemperature(temperature);
        cpuPerformanceEntity.setPower(power);
        cpuPerformanceEntity.setMemoryPercent(memoryPercent);
        cpuPerformanceEntity.setMemoryTotal(memoryTotal);
        cpuPerformanceEntity.setMemoryUsage(memoryUsage);
        cpuPerformanceEntity.setCreateTime(time);
        cpuPerformanceEntity.setId(this.initId(cpuPerformanceEntity));
        return cpuPerformanceEntity;
    }
}
