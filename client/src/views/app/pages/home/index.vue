<script setup lang="ts">
import {onMounted, ref} from "vue";

const isShow = ref(false);
onMounted(() => {
  isShow.value = true;
})
</script>
<template>
  <div>
    这里是首页
  </div>
</template>