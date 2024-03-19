package com.sunnysunco.cloud.business.auth.department;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.sunnysunco.cloud.business.auth.clientpage.ClientPageEntity;
import com.sunnysunco.cloud.business.base.BaseTreeEntity;
import com.sunnysunco.cloud.business.information.image.ImageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.*;

import javax.persistence.*;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data

@AllArgsConstructor
@NoArgsConstructor
@Builder

@Table(name = "t_department")
@Schema(description = "部门实体")
@TableName("t_department")
@Entity
public class DepartmentEntity extends BaseTreeEntity<DepartmentEntity> {

    @Schema(description = "部门名")
    @Column(name = "name")
    @TableField("name")
    private String name;

    @Schema(description = "部门编码")
    @Column(name = "code")
    @TableField("code")
    private String code;

    @Schema(description = "部门域名")
    @Column(name = "domain")
    @TableField("domain")
    private String domain;

    @Schema(description = "部门页面")
    @ManyToMany
    @JoinTable(name = "c_department_page",
            joinColumns = @JoinColumn(name = "department_id"),
            inverseJoinColumns = @JoinColumn(name = "page_id"))
    @TableField(exist = false)
    @OrderBy("sort ASC")
    private List<ClientPageEntity> pages;

    @Schema(description = "部门地址")
    @Column(name = "address")
    @TableField("address")
    private String address;

    @Schema(description = "部门电话")
    @Column(name = "phone")
    @TableField("phone")
    private String phone;

    @Schema(description = "部门备注HTML")
    @Column(name = "html")
    @TableField("html")
    private String html;

    @Schema(description = "部门图标")
    @TableField(exist = false)
    @JoinColumn(name = "icon_id")
    @ManyToOne()
    private ImageEntity icon;

    @Schema(description = "部门图标id")
    @Column(name = "icon_id")
    @Transient
    @TableField("icon_id")
    private String iconId;
}
