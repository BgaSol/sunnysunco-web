package com.sunnysunco.cloud.business.auth.user;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.stp.SaTokenInfo;
import com.sunnysunco.cloud.business.auth.user.dto.*;
import com.sunnysunco.cloud.business.auth.user.vo.VerificationVo;
import com.sunnysunco.cloud.business.base.BaseController;
import com.sunnysunco.cloud.business.base.vo.BaseVo;
import com.sunnysunco.cloud.business.base.vo.PageVo;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@Tag(name = "用户管理")
@RequestMapping("/user")
@Validated
public class UserController extends BaseController<
        UserEntity,
        UserPageDto,
        CreateUserDto,
        UpdateUserDto> {
    private final UserService userService;

    @Override
    public UserService commonBaseService() {
        return userService;
    }

    @GetMapping("/get-verification-code")
    @Operation(summary = "获取验证码", operationId = "getVerificationCode")
    public BaseVo<VerificationVo> getVerificationCode() {
        return BaseVo.success(this.userService.getVerificationCode());
    }

    @PostMapping("/login")
    @Operation(summary = "用户登录", operationId = "login")
    public BaseVo<SaTokenInfo> login(@RequestBody @Valid LoginUserDto loginUserDto) {
        return BaseVo.success(this.userService.login(loginUserDto));
    }

    @PostMapping("/logout")
    @Operation(summary = "用户登出", operationId = "logout")
    @SaCheckPermission({"user:logout"})
    public BaseVo<?> logout() {
        this.userService.logout();
        return BaseVo.success("登出成功");
    }

    @GetMapping("/user-info")
    @Operation(summary = "获取用户信息", operationId = "getUserInfo")
    @SaCheckPermission({"user:getUserInfo"})
    public BaseVo<UserEntity> getUserInfo() {
        UserEntity user = this.userService.getUserInfo();
        return BaseVo.success(user);
    }

    @PutMapping("/update-password")
    @Operation(summary = "修改用户密码", operationId = "updateUserPassword")
    @SaCheckPermission("user:updatePassword")
    public BaseVo<UserEntity> updatePassword(@RequestBody @Valid UpdateUserPasswordDto updateUserPasswordDto) {
        UserEntity userEntity = this.userService.updatePassword(updateUserPasswordDto);
        return BaseVo.success(userEntity, "修改密码成功");
    }

    @PutMapping("/reset-password")
    @Operation(summary = "重置用户密码", operationId = "resetUserPassword")
    @SaCheckPermission("user:resetPassword")
    public BaseVo<UserEntity> resetPassword(@RequestBody @Valid ResetUserPasswordDto resetUserPasswordDto) {
        UserEntity userEntity = this.userService.resetPassword(resetUserPasswordDto);
        return BaseVo.success(userEntity, "重置密码成功");
    }

    @Override
    @PostMapping("/page")
    @Operation(summary = "分页查询用户", operationId = "findByUserPage")
    @SaCheckPermission("user:findByPage")
    public BaseVo<PageVo<UserEntity>> findByPage(@RequestBody @Valid UserPageDto pageDto) {
        return super.findByPage(pageDto);
    }

    @Override
    @PostMapping()
    @Operation(summary = "保存用户", operationId = "saveUser")
    @SaCheckPermission("user:save")
    public BaseVo<UserEntity> save(@RequestBody CreateUserDto entity) {
        return super.save(entity);
    }

    @Override
    @PutMapping()
    @Operation(summary = "更新用户", operationId = "updateUser")
    @SaCheckPermission("user:update")
    public BaseVo<UserEntity> update(@RequestBody UpdateUserDto entity) {
        return super.update(entity);
    }

    @Override
    @DeleteMapping("/{ids}")
    @Operation(summary = "删除用户并强制退出登录", operationId = "deleteUser")
    @SaCheckPermission("user:delete")
    public BaseVo<Integer[]> delete(@PathVariable String ids) {
        return super.delete(ids);
    }

    @Override
    @GetMapping("/{id}")
    @Operation(summary = "根据id查询用户", operationId = "findUserById")
    @SaCheckPermission("user:findById")
    public BaseVo<UserEntity> findById(@PathVariable String id) {
        return super.findById(id);
    }

    @Override
    @GetMapping()
    @Operation(summary = "查询所有用户", operationId = "findAllUser")
    @SaCheckPermission("user:findAll")
    public BaseVo<List<UserEntity>> findAll() {
        return super.findAll();
    }
}
