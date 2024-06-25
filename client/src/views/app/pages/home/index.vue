<script lang="ts" setup>
import {useUser} from "~/pinia/modules/user";

const user = useUser();
</script>
<template>
  <div class='welcome-page'>
    <div class='welcome-page__content'>
      <div class='welcome-page__content__title'>Welcome</div>
      <div class='welcome-page__content__desc'>{{ user?.user?.username }}</div>
      <div class='welcome-page__content__action'>
      </div>
    </div>
  </div>
</template>
<style lang='scss' scoped>
.welcome-page {
  height: 100%;
  display: flex;
  justify-content: center;
  align-items: center;

  &__content {
    text-align: center;

    &__title {
      font-size: 72px;
      font-weight: 600;
      color: var(--el-color-primary);
    }

    &__desc {
      font-size: 24px;
      color: var(--el-color-primary);
    }

    &__action {
      margin-top: 24px;
    }
  }
}
</style>
