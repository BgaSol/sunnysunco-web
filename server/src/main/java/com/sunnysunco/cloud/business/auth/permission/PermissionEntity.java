package com.sunnysunco.cloud.business.auth.permission;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnysunco.cloud.business.base.BaseTreeEntity;
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

@Schema(description = "权限实体")
@TableName("t_permission")
@Table(name = "t_permission")
@Entity
public class PermissionEntity extends BaseTreeEntity<PermissionEntity> {

    @Schema(description = "权限名")
    @Column(name = "name")
    @TableField("name")
    private String name;

    @Schema(description = "权限编码")
    @Column(name = "code")
    @TableField("code")
    private String code;

    @Schema(description = "权限路径")
    @Column(name = "path")
    @TableField("path")
    private String path;

    @Schema(description = "权限状态")
    @Column(name = "status")
    @TableField("status")
    private Integer status;
}
