package com.sunnysunco.cloud.business.auth.user.dto;

import com.sunnysunco.cloud.business.auth.user.UserEntity;
import com.sunnysunco.cloud.business.base.dto.BaseUpdateDto;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotBlank;

@EqualsAndHashCode(callSuper = true)
@Data()
@Schema(description = "重置用户密码数据传输对象")
public class ResetUserPasswordDto extends BaseUpdateDto<UserEntity> {
    @NotBlank(message = "新密码不能为空")
    @Schema(description = "新密码")
    private String password;

    @Override
    public UserEntity toEntity() {
        UserEntity user = new UserEntity();
        user.setId(this.getId());
        user.setPassword(password);
        return user;
    }
}
