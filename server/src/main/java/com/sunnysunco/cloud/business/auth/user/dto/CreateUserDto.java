package com.sunnysunco.cloud.business.auth.user.dto;

import com.sunnysunco.cloud.business.auth.role.RoleEntity;
import com.sunnysunco.cloud.business.auth.user.UserEntity;
import com.sunnysunco.cloud.business.base.dto.BaseCreateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.commons.lang3.ObjectUtils;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Data()
@EqualsAndHashCode(callSuper = true)
@Schema(description = "创建用户数据传输对象")
public class CreateUserDto extends BaseCreateDto<UserEntity> {
    @NotBlank(message = "用户名不能为空")
    @Schema(description = "用户名")
    private String username;

    @NotBlank(message = "密码不能为空")
    @Schema(description = "密码")
    private String password;

    @Schema(description = "昵称")
    private String nickname;

    @Schema(description = "邮箱")
//    @Email(message = "邮箱格式不正确")
//    @NotBlank(message = "邮箱不能为空")
    private String email;

    @Schema(description = "手机号")
    private String phone;

    @NotNull
    @Schema(description = "账户锁定")
    private Boolean locked;

    @Schema(description = "角色id列表")
    private List<String> roleIds;

    @Schema(description = "部门id")
    private String departmentId;

    @Override
    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.setUsername(username);
        user.setPassword(password);
        user.setNickname(nickname);
        user.setEmail(email);
        user.setPhone(phone);
        user.setLocked(locked);
        if (ObjectUtils.isNotEmpty(departmentId)) {
            user.setDepartmentId(departmentId);
        } else {
            user.setDepartmentId("default");
        }
        if (ObjectUtils.isNotEmpty(roleIds)) {
            Stream<RoleEntity> roleEntityStream = roleIds.stream().map((id) -> {
                RoleEntity roleEntity = new RoleEntity();
                roleEntity.setId(id);
                return roleEntity;
            });
            List<RoleEntity> collect = roleEntityStream.collect(Collectors.toList());
            user.setRoles(collect);
        }
        return this.toEntity(user);
    }
}
