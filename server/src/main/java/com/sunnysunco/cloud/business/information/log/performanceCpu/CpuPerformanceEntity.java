package com.sunnysunco.cloud.business.information.log.performanceCpu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnysunco.cloud.business.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@EqualsAndHashCode(callSuper = true)
@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder

@TableName( value = "t_cpu_performance" )
@Entity
@Table(name = "t_cpu_performance")
@Schema(description = "CPU性能实体")
public class CpuPerformanceEntity extends BaseEntity {

    @Schema(description = "CPU使用率(%)")
    @TableField("usage_percent")
    @Column(name = "usage_percent")
    private Double usagePercent;

    @Schema(description = "CPU每个核心使用率")
    @TableField("core_usage_percent")
    @Column(name = "core_usage_percent")
    private String coreUsagePercent;

    @Schema(description = "CPU型号")
    @TableField("model")
    @Column(name = "model")
    private String model;

    @Schema(description = "CPU温度(°C)")
    @TableField("temperature")
    @Column(name = "temperature")
    private Double temperature;

    @Schema(description = "CPU功耗(W)")
    @TableField("power")
    @Column(name = "power")
    private Double power;

    @Schema(description = "内存使用率(%)")
    @TableField("memory_percent")
    @Column(name = "memory_percent")
    private Double memoryPercent;

    @Schema(description = "内存总量(MB)")
    @TableField("memory_total")
    @Column(name = "memory_total")
    private Double memoryTotal;

    @Schema(description = "内存使用量(MB)")
    @TableField("memory_usage")
    @Column(name = "memory_usage")
    private Double memoryUsage;
}
