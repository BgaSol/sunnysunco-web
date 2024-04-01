package ${packageName};

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnysunco.cloud.business.base.<#if isTree>BaseTreeEntity<#else>BaseEntity</#if>;
import com.sunnysunco.cloud.business.information.file.FileEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;
import org.apache.ibatis.annotations.One;

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
    <#if tableColumn.type?exists>
    @TableField("${tableColumn.tableColumnName}")
    @Column(name = "${tableColumn.tableColumnName}")
    </#if>
    @Schema(description = "${tableColumn.description}")
    <#if tableColumn.type?exists>
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
    @OneToMany(mappedBy = "<#list tableColumn.associatedTable.tableColumns as cTableColumn></#list>")
            </#if>
    private List<${tableColumn.associatedTable.entityName}> ${tableColumn.associatedTable.tableName[2..]}List;
        </#if>
        <#if tableColumn.associationMethod == "OneToOne">
    @TableField(exist = false)
    @OneToOne(targetEntity = ${tableColumn.associatedTable.entityName}.class)
            <#if tableColumn.isMaster>
    @JoinColumn(name = "${tableColumn.associatedTable.tableName[2..]}_id")
            </#if>
        </#if>
        <#if tableColumn.associationMethod == "OneToOne">
            @OneToMany

        </#if>
    </#if>

</#list>
}