package com.sunnysunco.cloud.business.auth.role;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnysunco.cloud.business.auth.menu.MenuEntity;
import com.sunnysunco.cloud.business.auth.permission.PermissionEntity;
import com.sunnysunco.cloud.business.base.BaseEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Schema(description = "角色实体")
@TableName("t_role")
@Table(name = "t_role")
@Entity
public class RoleEntity extends BaseEntity {
    @Schema(description = "角色名")
    @TableField("name")
    @Column(name = "name")
    private String name;

    @Schema(description = "角色编码")
    @TableField("code")
    @Column(name = "code")
    private String code;

    @Schema(description = "角色状态")
    @TableField("status")
    @Column(name = "status")
    private Integer status;

    @Schema(description = "角色权限")
    @TableField(exist = false)
    @ManyToMany(targetEntity = PermissionEntity.class)
    @JoinTable(
            name = "c_role_permission",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "permission_id")
    )
    @OrderBy("createTime DESC")
    private List<PermissionEntity> permissions;

    @Schema(description = "角色菜单")
    @TableField(exist = false)
    @ManyToMany(targetEntity = MenuEntity.class)
    @JoinTable(
            name = "c_role_menu",
            joinColumns = @JoinColumn(name = "role_id"),
            inverseJoinColumns = @JoinColumn(name = "menu_id")
    )
    @OrderBy("createTime DESC")
    private List<MenuEntity> menus;
}
