<script lang='ts' setup>
import {onBeforeUnmount, onMounted} from 'vue';

const glassThemeClass = 'admin-glass-theme';

onMounted(() => {
  document.body.classList.add(glassThemeClass);
});

onBeforeUnmount(() => {
  document.body.classList.remove(glassThemeClass);
});
</script>
<template>
  <div class='admin-app app-backdrop'>
    <div class='header glass-surface'>
      <router-view name='header'></router-view>
    </div>
    <div class='lower'>
      <div class='menu-view glass-surface'>
        <router-view name='menu'></router-view>
      </div>
      <div class='main-view glass-surface'>
        <router-view v-slot="{ Component }">
          <transition enter-active-class="animate__animated animate__fadeIn"
                      leave-active-class="animate__animated animate__fadeOut"
                      mode="out-in">
            <component :is="Component"/>
          </transition>
        </router-view>
      </div>
    </div>
  </div>
</template>
<style lang='scss' scoped>
.admin-app {
  overflow: hidden;
  position: absolute;
  box-sizing: border-box;
  width: 100vw;
  height: 100vh;
  padding: 12px;
  display: flex;
  flex-direction: column;
  gap: 12px;

  .header {
    position: relative;
    z-index: 100;
    flex: 0 0 auto;
    border-radius: 8px;

    :deep(.el-menu) {
      border-radius: inherit;
    }
  }

  .lower {
    position: relative;
    flex: 1;
    display: flex;
    flex-direction: row;
    gap: 12px;
    min-height: 0;
    min-width: 0;

    .menu-view {
      z-index: 50;
      height: 100%;
      position: relative;
      min-height: 0;
      overflow-y: auto;
      border-radius: 8px;

      :deep(.el-menu) {
        border-radius: inherit;
      }
    }

    .main-view {
      flex: 1;
      position: relative;
      min-width: 0;
      overflow: hidden;
      border-radius: 8px;
    }
  }
}

@media (max-width: 767px) {
  .admin-app {
    padding: 8px;
    gap: 8px;

    .lower {
      gap: 8px;
    }
  }
}

@media (prefers-reduced-motion: reduce) {
  .admin-app :deep(.animate__animated) {
    animation: none !important;
  }
}
</style>
