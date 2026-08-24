<template>
  <div class="welcome-container">
    <h1>Welcome to Admin Console!</h1>
    <el-button type="primary" @click="goToDashboard">控制台</el-button>
  </div>
</template>

<script lang="ts" setup>
import {useRouter} from 'vue-router';

const router = useRouter();

const goToDashboard = () => {
  router.push({name: 'AdminHome'});
};
</script>

<style lang="scss" scoped>
.welcome-container {
  text-align: center;
  box-sizing: border-box;
  padding: 48px 24px;
  color: var(--app-text-primary);
  min-height: 100%;
  display: flex;
  flex-direction: column;
  justify-content: center;
  align-items: center;

  h1 {
    font-size: 36px;
    color: var(--app-text-primary);
    margin-bottom: 20px;
    letter-spacing: 0;
  }

  .el-button {
    min-width: 120px;
  }
}
</style>
