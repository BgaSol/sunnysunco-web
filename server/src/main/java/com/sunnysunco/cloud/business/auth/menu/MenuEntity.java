package com.sunnysunco.cloud.business.auth.menu;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnysunco.cloud.business.base.BaseTreeEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Schema(description = "菜单实体")
@TableName("t_menu")
@Table(name = "t_menu")
@Entity
public class MenuEntity extends BaseTreeEntity<MenuEntity> {

    @Schema(description = "菜单名")
    @TableField("name")
    @Column(name = "name")
    private String name;

    @Schema(description = "菜单状态")
    @TableField("status")
    @Column(name = "status")
    private Integer status;

    @Schema(description = "菜单类型")
    @TableField("menu_type")
    @Column(name = "menu_type")
    @Enumerated(EnumType.STRING)
    private MenuType menuType;

    @Schema(description = "菜单路由地址")
    @TableField("route_path")
    @Column(name = "route_path")
    private String path;

    @Schema(description = "菜单图标")
    @TableField("icon")
    @Column(name = "icon")
    private String icon;

    @Schema(description = "菜单路由名")
    @TableField("route_name")
    @Column(name = "route_name")
    private String routeName;

    @Schema(description = "按钮代码")
    @TableField("button_code")
    @Column(name = "button_code")
    private String buttonCode;

    @Schema(description = "是否是外链")
    @TableField("is_external")
    @Column(name = "is_external")
    private Boolean isExternal;

    @Schema(description = "外链地址")
    @TableField("external_url")
    @Column(name = "external_url")
    private String externalUrl;

    @Schema(description = "是否外链新窗口打开")
    @TableField("is_external_open")
    @Column(name = "is_external_open")
    private Boolean isExternalOpen;

    @Schema(description = "是否停用（置灰）")
    @TableField("is_disabled")
    @Column(name = "is_disabled")
    private Boolean isDisabled;

    @Schema(description = "是否隐藏（不显示）")
    @TableField("is_hidden")
    @Column(name = "is_hidden")
    private Boolean isHidden;

    @Schema(description = "菜单组")
    @TableField("menu_group")
    @Column(name = "menu_group")
    private String menuGroup;
}
