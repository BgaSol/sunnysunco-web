package com.sunnysunco.cloud.business.auth.user;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.sunnysunco.cloud.business.auth.user.dto.LoginUserDto;
import com.sunnysunco.cloud.business.auth.user.dto.ResetUserPasswordDto;
import com.sunnysunco.cloud.business.auth.user.dto.UpdateUserPasswordDto;
import com.sunnysunco.cloud.business.auth.user.dto.UserPageDto;
import com.sunnysunco.cloud.business.auth.user.vo.VerificationVo;
import com.sunnysunco.cloud.business.base.BaseService;
import com.sunnysunco.cloud.business.base.exception.BaseException;
import com.wf.captcha.ArithmeticCaptcha;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class UserService extends BaseService<UserEntity, UserPageDto> {
    private final UserMapper userMapper;

    private final UserRepository userRepository;

    private final RedisTemplate<String, String> redisTemplate;

    @Override
    public UserMapper commonBaseMapper() {
        return userMapper;
    }

    @Override
    public UserRepository commonBaseRepository() {
        return userRepository;
    }

    @Override
    public Class<UserEntity> commonBaseEntityClass() {
        return UserEntity.class;
    }

    @Override
    public Integer[] delete(String[] ids) {
        // 先删除用户
        Integer[] delete = super.delete(ids);
        // 再删除用户的token
        for (String id : ids) {
            StpUtil.logout(id);
        }
        return delete;
    }

    @Override
    public UserEntity save(UserEntity entity) {
        String password = entity.getPassword();
        if (ObjectUtils.isNotEmpty(password)) {
            // todo 密码加密 password
        }
        return super.save(entity);
    }

    @Override
    public UserEntity update(UserEntity entity) {
        String password = entity.getPassword();
        if (ObjectUtils.isNotEmpty(password)) {
            // todo 密码加密 password
        }
        return super.update(entity);
    }

    public UserEntity updatePassword(UpdateUserPasswordDto updateUserPasswordDto) {
        String userid = StpUtil.getLoginIdAsString();
        UserEntity userEntity = this.userMapper.selectById(userid);
        String userInputOldPassword = updateUserPasswordDto.getOldPassword();
        // todo 密码加密 userInputOldPassword
        if (userEntity.getPassword().equals(userInputOldPassword)) {
            String newPassword = updateUserPasswordDto.getNewPassword();
            // todo 密码加密 newPassword
            userEntity.setPassword(newPassword);
            return this.update(userEntity);
        }
        throw new BaseException("原密码错误");
    }

    public UserEntity resetPassword(ResetUserPasswordDto resetUserPasswordDto) {
        return this.update(resetUserPasswordDto.toEntity());
    }

    public SaTokenInfo login(LoginUserDto loginUserDto) {
        // 获取验证码
        String verificationCode = redisTemplate.opsForValue().get("verification-code:" + loginUserDto.getVerificationCodeKey());
        if (verificationCode == null) {
            log.error("验证码已过期");
            throw new BaseException("验证码已过期");
        }
        // 删除验证码
        redisTemplate.delete("verification-code:" + loginUserDto.getVerificationCodeKey());
        if (!verificationCode.equals(loginUserDto.getVerificationCode())) {
            log.error("验证码错误");
            throw new BaseException("验证码错误");
        }
        QueryWrapper<UserEntity> query = new QueryWrapper<>();
        query.eq("username", loginUserDto.getUsername());
        UserEntity userEntity = this.userMapper.selectOne(query);
        if (userEntity == null) {
            log.error("用户不存在");
            throw new BaseException("用户名或密码错误");
        }
        String password = loginUserDto.getPassword();
        // todo 密码加密 password
        if (!userEntity.getPassword().equals(password)) {
            log.error("密码错误");
            throw new BaseException("用户名或密码错误");
        }
        if (userEntity.getLocked()) {
            log.error("用户已锁定");
            throw new BaseException("用户已锁定");
        }
        StpUtil.login(userEntity.getId());
        return StpUtil.getTokenInfo();
    }

    public UserEntity getUserInfo() {
        return this.getUserInfo(StpUtil.getLoginIdAsString());
    }

    public UserEntity getUserInfo(String id) {
        // 查询用户信息 以及角色 权限
        return this.userRepository.findById(id).orElseThrow(() -> new BaseException("用户不存在"));
    }

    public VerificationVo getVerificationCode() {
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(100, 35, 2);
        // 获取运算的结果：5
        String text = captcha.text();
        // 生成验证码的key
        String key = UUID.randomUUID().toString();
        // 保存到redis 5分钟有效
        redisTemplate.opsForValue().set("verification-code:" + key, text, 5 * 60, java.util.concurrent.TimeUnit.SECONDS);
        VerificationVo verificationVo = new VerificationVo();
        verificationVo.setVerificationCode(captcha.toBase64());
        verificationVo.setVerificationId(key);
        return verificationVo;
    }

    public void logout() {
        StpUtil.logout();
    }
}
