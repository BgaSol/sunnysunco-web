package com.sunnysunco.cloud.codegeneration.tablecolum;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunnysunco.cloud.business.base.BaseEntity;
import com.sunnysunco.cloud.codegeneration.table.TableEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder

@TableName("t_table_column")
@Entity()
@Table(name = "t_table_column")
@Schema(description = "数据库表结构-字段")
public class TableColumnEntity extends BaseEntity {

    @TableField("table_colum_name")
    @Schema(description = "表字段")
    @Column(name = "table_colum_name")
    private String tableColumnName;

    @TableField("entity_colum_name")
    @Schema(description = "实体字段")
    @Column(name = "entity_colum_name")
    private String entityColumnName;

    @Schema(description = "所在表id")
    @Column(name = "in_table_id")
    @Transient
    @TableField("in_table_id")
    private String inTableId;

    @Schema(description = "所在表id")
    @TableField(exist = false)
    @JoinColumn(name = "in_table_id")
    @ManyToOne()
    @JsonIgnore()
    private TableEntity inTable;

    @Schema(description = "关联表ID")
    @Column(name = "associated_table_id")
    @Transient
    @TableField("associated_table_id")
    private String associatedTableId;

    @Schema(description = "关联表")
    @TableField(exist = false)
    @JoinColumn(name = "associated_table_id")
    @ManyToOne()
    private TableEntity associatedTable;

    @Schema(description = "关联字段")
    @TableField("associated_column")
    @Column(name = "associated_column")
    private String associatedColumn;

    @Schema(description = "关联方式")
    @TableField("association_method")
    @Column(name = "association_method")
    private String associationMethod;

    @Schema(description = "是否是主要维护端")
    @TableField("is_master")
    @Column(name = "is_master")
    private Boolean isMaster;

    @Schema(description = "类型")
    @TableField("type")
    @Column(name = "type")
    private String type;
}
