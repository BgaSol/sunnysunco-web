package ${packageName};

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnysunco.cloud.business.base.<#if isTree>BaseTreeEntity<#else>BaseEntity</#if>;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder

@TableName("${tableName}")
@Entity()
@Table(name = "${tableName}")
@Schema(description = "${description}")
public class ${entityName} extends <#if isTree>BaseTreeEntity<#else>BaseEntity</#if> {
<#list tableColumns as tableColumn>

    <#if tableColumn.type??>
    @TableField("${tableColumn.tableColumnName}")
    @Column(name = "${tableColumn.tableColumnName}")
    </#if>
    @Schema(description = "${tableColumn.description}")
    <#if tableColumn.type??>
    private ${tableColumn.type} ${tableColumn.entityColumnName};
    <#else>
        <#if tableColumn.associationMethod == "OneToMany">
    @TableField(exist = false)
            <#if tableColumn.isMaster>
    @OneToMany()
    @JoinTable(name = "c_${tableName[2..]}_${tableColumn.associatedTable.tableName[2..]}",
            joinColumns = @JoinColumn(name = "${tableName[2..]}_id"),
            inverseJoinColumns = @JoinColumn(name = "${tableColumn.associatedTable.tableName[2..]}_id"))
            <#else>
    @OneToMany(mappedBy = "${tableColumn.associatedColumn}")
            </#if>
    private List<${tableColumn.associatedTable.entityName}> ${tableColumn.associatedTable.tableName[2..]}List;
        </#if>
        <#if tableColumn.associationMethod == "ManyToMany">
    @TableField(exist = false)
            <#if tableColumn.isMaster>
    @ManyToMany()
    @JoinTable(name = "c_${tableName[2..]}_${tableColumn.associatedTable.tableName[2..]}",
            joinColumns = @JoinColumn(name = "${tableName[2..]}_id"),
            inverseJoinColumns = @JoinColumn(name = "${tableColumn.associatedTable.tableName[2..]}_id"))
            <#else>
    @ManyToMany(mappedBy = "${tableColumn.associatedColumn}")
            </#if>
    private List<${tableColumn.associatedTable.entityName}> ${tableColumn.associatedTable.tableName[2..]}List;
        </#if>
        <#if tableColumn.associationMethod == "OneToOne">
    @TableField(exist = false)
            <#if tableColumn.isMaster>
    @OneToOne()
    @JoinColumn(name = "${tableColumn.associatedTable.tableName[2..]}_id")
            <#else>
    @OneToOne(mappedBy = "${tableColumn.associatedColumn}")
            </#if>
    private ${tableColumn.associatedTable.entityName} ${tableColumn.associatedTable.tableName[2..]};
        </#if>
        <#if tableColumn.associationMethod == "ManyToOne">
    @TableField(exist = false)
            <#if tableColumn.isMaster>
    @ManyToOne()
    @JoinColumn(name = "${tableColumn.associatedTable.tableName[2..]}_id")
            <#else>
    @ManyToOne()
    @JoinColumn(name = "${tableColumn.associatedColumn}")
            </#if>
    private ${tableColumn.associatedTable.entityName} ${tableColumn.associatedTable.tableName[2..]};
        </#if>
    </#if>
</#list>
}
