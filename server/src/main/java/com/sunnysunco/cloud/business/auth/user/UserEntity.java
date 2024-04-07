package com.sunnysunco.cloud.business.auth.user;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sunnysunco.cloud.business.auth.department.DepartmentEntity;
import com.sunnysunco.cloud.business.auth.role.RoleEntity;
import com.sunnysunco.cloud.business.base.BaseEntity;
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

@Schema(description = "用户实体类")
@Table(name = "t_user")
@TableName("t_user")
@Entity()
public class UserEntity extends BaseEntity {

    @Schema(description = "用户名")
    @TableField("username")
    @Column(name = "username")
    String username;

    @Schema(description = "密码")
    @TableField("password")
    @Column(name = "password")
    @JsonIgnore
    String password;

    @Schema(description = "昵称")
    @TableField("nickname")
    @Column(name = "nickname")
    String nickname;

    @Schema(description = "邮箱")
    @TableField("email")
    @Column(name = "email")
    String email;

    @Schema(description = "手机号")
    @TableField("phone")
    @Column(name = "phone")
    String phone;

    @Schema(description = "状态")
    @TableField("status")
    @Column(name = "status")
    String status;

    @Schema(description = "头像id")
    @TableField("avatar_id")
    @Transient
    @Column(name = "avatar_id")
    String avatarId;

    @Schema(description = "头像")
    @TableField(exist = false)
    @OneToOne()
    @JoinColumn(name = "avatar_id")
    ImageEntity avatar;

//    @Schema(description = "激活时间")
//    @TableField("activated_at")
//    @Column(name = "activated_at")
//    Date activatedAt;
//
//    @Schema(description = "停用时间")
//    @TableField("deactivated_at")
//    @Column(name = "deactivated_at")
//    Date deactivatedAt;

    @Schema(description = "账户锁定")
    @TableField("locked")
    @Column(name = "locked")
    Boolean locked;

    @Schema(description = "角色")
    @TableField(exist = false)
    @ManyToMany(targetEntity = RoleEntity.class)
    @JoinTable(
            name = "c_user_role",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id")
    )
    @OrderBy("createTime DESC")
    List<RoleEntity> roles;

    @Schema(description = "部门id")
    @TableField("department_id")
    @Column(name = "department_id")
    @Transient
    String departmentId;

    @Schema(description = "部门")
    @TableField(exist = false)
    @ManyToOne
    @JoinColumn(name = "department_id")
    private DepartmentEntity department;
}
