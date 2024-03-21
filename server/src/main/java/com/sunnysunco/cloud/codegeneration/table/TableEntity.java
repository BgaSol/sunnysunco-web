package com.sunnysunco.cloud.codegeneration.table;

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

@TableName("t_table")
@Entity()
@Table(name = "t_table")
@Schema(description = "数据库表结构-库")
public class TableEntity extends BaseEntity {
    @TableField("table_name")
    @Schema(description = "表名")
    @Column(name = "table_name")
    private String tableName;

    @TableField("eneity_name")
    @Schema(description = "实体名")
    @Column(name = "eneity_name")
    private String entityName;

    @TableField("package_name")
    @Schema(description = "包名")
    @Column(name = "package_name")
    private String packageName;

}
