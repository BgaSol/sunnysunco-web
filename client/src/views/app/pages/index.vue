<script lang="ts" setup>

</script>

<template>
  <div class="sticky important-top-0 z-100">
    <router-view name="header"></router-view>
  </div>
  <div>
    <router-view v-slot="{ Component }">
      <transition enter-active-class="animate__animated animate__bounceInLeft"
                  leave-active-class="animate__animated animate__bounceOutRight"
                  mode="out-in">
        <component :is="Component"/>
      </transition>
    </router-view>
  </div>
</template>
