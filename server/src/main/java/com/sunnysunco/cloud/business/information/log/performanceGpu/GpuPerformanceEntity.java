package com.sunnysunco.cloud.business.information.log.performanceGpu;

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

@TableName(value = "t_gpu_performance")
@Entity
@Table(name = "t_gpu_performance")
@Schema(description = "GPU性能实体")
public class GpuPerformanceEntity extends BaseEntity {

    @Schema(description = "GPU索引")
    @Column(name = "index")
    @TableField("index")
    private Integer index;

    @Schema(description = "GPU名称")
    @Column(name = "name")
    @TableField("name")
    private String name;

    @Schema(description = "GPU使用率(%)")
    @Column(name = "usage_percent")
    @TableField("usage_percent")
    private Double usagePercent;

    @Schema(description = "显存使用率(%)")
    @Column(name = "memory_percent")
    @TableField("memory_percent")
    private Double memoryPercent;

    @Schema(description = "显存总量(MB)")
    @Column(name = "memory_total")
    @TableField("memory_total")
    private Double memoryTotal;

    @Schema(description = "显存使用量(MB)")
    @Column(name = "memory_usage")
    @TableField("memory_usage")
    private Double memoryUsage;

    @Schema(description = "GPU温度(°C)")
    @Column(name = "temperature")
    @TableField("temperature")
    private Double temperature;

    @Schema(description = "GPU功耗(W)")
    @Column(name = "power")
    @TableField("power")
    private Double power;

    @Schema(description = "风扇")
    @Column(name = "fan")
    @TableField("fan")
    private String fan;

}
