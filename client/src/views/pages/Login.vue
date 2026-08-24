<script lang='ts' setup>
import {onMounted, ref} from 'vue';
import {Service, UserLoginDto, VerificationResult} from "~/generated/system";
import {useRoute, useRouter} from "vue-router";
import {useUser} from "~/pinia/modules/user";
import {ElButton, ElForm, ElFormItem, ElIcon, ElImage, ElInput, ElMessage, FormInstance} from "element-plus";
import {useFormValidation} from "~/composables/FormValidationHook";
import AppHeader from "~/views/app/layout/AppHeader.vue";

const loginDto = ref<UserLoginDto>({
  username: '',
  password: '',
  verificationCode: '',
  verificationCodeKey: ''
});
const needCaptcha = ref<boolean>(true);
const imageBase64 = ref<string>();
const getCaptcha = () => {
  // 获取验证码
  Service.getVerificationCodeUserController().then((res) => {
    if (res.data?.captcha) {
      loginDto.value.verificationCode = res.data?.captcha
      needCaptcha.value = false;
    } else {
      needCaptcha.value = true;
    }
    loginDto.value.verificationCodeKey = res.data?.verificationId as string
    imageBase64.value = res.data?.verificationCode as string;
    setTimeout(getCaptcha, 30 * 1000)
  });
};
onMounted(() => {
  getCaptcha();
});
const router = useRouter();
const route = useRoute();
onMounted(() => {
  if (route.query.error === '401') {
    ElMessage.warning('请先登录');
  }
})
const user = useUser();

const formRef = ref<FormInstance>();

const {errData: loginDtoErr, validate, resetValidate} = useFormValidation();
const loading = ref(false);
// 登录
const login = () => {
  resetValidate();
  loading.value = true;
  Service.loginUserController(loginDto.value).then((res) => {
    if (res.code === 400) {
      validate(res.data as VerificationResult[]);
    } else if (res.code === 200) {
      user.setToken(<string>res.data?.tokenValue)
      if (route.query.redirect) {
        // 跳转到上次访问的页面
        window.location.href = route.query.redirect as string;
      } else {
        router.push({name: "admin_home"});
      }
    }
  }).catch(() => {
    getCaptcha();
  }).finally(() => {
    loading.value = false;
  })
};
const pageLoading = ref(true);
onMounted(() => {
  pageLoading.value = false;
});
</script>

<template>
  <div class='login app-backdrop'>
    <header class="login-header glass-surface">
      <app-header></app-header>
    </header>
    <main class="login-main">
      <section v-motion-roll-bottom :duration="600" v-show="!pageLoading" class="login-panel glass-surface">
        <div class="login-title">
          <span class="login-kicker">SOL CLOUD</span>
          <h1>用户登录</h1>
        </div>
        <el-form ref="formRef" :model='loginDto' class='login-form' label-position="top">
          <el-form-item label="用户名" :error="loginDtoErr.username">
            <el-input v-model='loginDto.username' autocomplete="username" placeholder='请输入用户名'
                      @keyup.enter='login'>
              <template #prefix>
                <el-icon><User/></el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item label="密码" :error="loginDtoErr.password">
            <el-input v-model='loginDto.password' autocomplete="current-password" placeholder='请输入密码'
                      show-password type='password' @keyup.enter='login'>
              <template #prefix>
                <el-icon><Lock/></el-icon>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item v-if="needCaptcha" label="验证码" :error="loginDtoErr.verificationCode">
            <el-input v-model="loginDto.verificationCode" placeholder="请输入验证码" @keyup.enter='login'>
              <template #prefix>
                <el-icon><Key/></el-icon>
              </template>
              <template #append>
                <el-image fit="fill" :src="imageBase64" class="captcha-image el-image-block cursor-pointer"
                          @click="getCaptcha"></el-image>
              </template>
            </el-input>
          </el-form-item>
          <el-form-item class="submit-row">
            <el-button :loading='loading' type='primary' class="login-submit" @click='login'>
              登录
              <el-icon class="el-icon--right"><ArrowRight/></el-icon>
            </el-button>
          </el-form-item>
        </el-form>
      </section>
    </main>
  </div>
</template>

<style lang='scss' scoped>
.login {
  --app-text-primary: var(--el-text-color-primary);
  --app-text-secondary: var(--el-text-color-secondary);
  --glass-control-background: var(--el-fill-color-blank);
  --glass-border-subtle: var(--el-border-color);
  overflow: hidden;
  box-sizing: border-box;
  width: 100%;
  min-height: 100vh;
  position: absolute;
  background:
    radial-gradient(circle at 18% 18%, color-mix(in srgb, var(--el-color-primary) 18%, transparent), transparent 38%),
    linear-gradient(135deg, var(--el-bg-color), var(--el-color-primary-light-9));
  padding: 12px;
  display: flex;
  flex-direction: column;
  gap: 12px;

  .login-header,
  .login-panel {
    background: color-mix(in srgb, var(--el-bg-color-overlay) 84%, transparent);
    border: 1px solid var(--el-border-color);
    box-shadow: var(--el-box-shadow-light);
    backdrop-filter: blur(18px) saturate(125%);
    -webkit-backdrop-filter: blur(18px) saturate(125%);
  }

  .login-header {
    position: relative;
    z-index: 2;
    overflow: hidden;
    border-radius: 8px;

    :deep(.el-menu) {
      --el-menu-bg-color: transparent;
      --el-menu-hover-bg-color: rgba(47, 134, 255, 0.11);
      --el-menu-border-color: transparent;
      background: transparent;
      border: 0;
    }
  }

  .login-main {
    position: relative;
    z-index: 1;
    flex: 1;
    min-height: 0;
    display: grid;
    place-items: center;
    padding: clamp(24px, 6vh, 72px) 20px;
  }

  .login-panel {
    box-sizing: border-box;
    width: min(100%, 420px);
    padding: 32px;
    border-radius: 8px;
  }

  .login-title {
    margin-bottom: 28px;

    .login-kicker {
      display: block;
      margin-bottom: 8px;
      color: var(--el-color-primary);
      font-size: 12px;
      font-weight: 700;
      line-height: 1;
      letter-spacing: 0;
    }

    h1 {
      margin: 0;
      color: var(--app-text-primary);
      font-size: 28px;
      line-height: 1.25;
      letter-spacing: 0;
    }
  }

  .login-form {
    width: 100%;

    :deep(.el-form-item__label) {
      color: var(--app-text-secondary);
      font-weight: 600;
    }

    :deep(.el-input__wrapper) {
      min-height: 42px;
      background: var(--glass-control-background);
      box-shadow: 0 0 0 1px var(--glass-border-subtle) inset;
    }

    :deep(.el-input-group__append) {
      padding: 0;
      overflow: hidden;
      background: var(--glass-control-background);
      border-color: var(--glass-border-subtle);
    }

    .captcha-image {
      width: 104px;
      height: 40px;
    }

    .submit-row {
      margin-top: 8px;
      margin-bottom: 0;

      :deep(.el-form-item__content) {
        width: 100%;
      }
    }

    .login-submit {
      width: 100%;
      min-height: 42px;
      font-weight: 600;
    }
  }
}

@media (max-width: 767px) {
  .login {
    padding: 8px;
    gap: 8px;

    .login-main {
      align-items: center;
      padding: 20px 8px;
    }

    .login-panel {
      padding: 24px 20px;
    }
  }
}

@media (prefers-reduced-motion: reduce) {
  .login-panel {
    animation: none !important;
  }
}
</style>
