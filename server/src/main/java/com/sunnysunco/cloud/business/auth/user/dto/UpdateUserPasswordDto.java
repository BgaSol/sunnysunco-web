package com.sunnysunco.cloud.business.auth.user.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Schema(description = "更新用户密码数据传输对象")
@Data()
public class UpdateUserPasswordDto {
    @Schema(description = "旧密码")
    @NotBlank(message = "旧密码不能为空")
    String oldPassword;

    @Schema(description = "新密码")
    @NotBlank(message = "新密码不能为空")
    String newPassword;

}
