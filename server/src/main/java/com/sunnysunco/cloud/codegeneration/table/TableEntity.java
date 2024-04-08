package com.sunnysunco.cloud.codegeneration.table;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnysunco.cloud.business.base.BaseEntity;
import com.sunnysunco.cloud.codegeneration.tablecolum.TableColumnEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

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

    @TableField("entity_name")
    @Schema(description = "实体名")
    @Column(name = "entity_name")
    private String entityName;

    @TableField("is_tree")
    @Schema(description = "是树结构表")
    @Column(name = "is_tree")
    private Boolean isTree;

    @TableField("package_name")
    @Schema(description = "包名")
    @Column(name = "package_name")
    private String packageName;

    @OneToMany(mappedBy = "inTable")
    @Schema(description = "表内字段")
    @TableField(exist = false)
    private List<TableColumnEntity> tableColumns;
}
