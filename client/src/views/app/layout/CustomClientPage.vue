<script lang="ts" setup>
import {useRoute} from "vue-router";
import {ref, watch} from "vue";
import {ClientPageEntity, Service} from "~/generated";
import {getMyClientPageImageUrl} from "~/api/HttpRequest";

const route = useRoute();
const clientPage = ref<ClientPageEntity>({});

watch(route, async () => {
  if (route.query?.page) {
    const {code, data: pages} = await Service.getMyClientPages()
    if (code !== 200) {
      return;
    }
    const page = (pages as ClientPageEntity[]).find(page => page.id === route.query.page)
    if (page) {
      clientPage.value = page;
    }
  }
}, {immediate: true})
</script>

<template>
  <transition-group enter-active-class="animate__animated animate__zoomInRight">
    <el-image v-for="image in clientPage.images"
              :key="image.id" :src="getMyClientPageImageUrl(image.id as string)"
              class="w-full pointer-events-none el-image-block important-block">
    </el-image>
  </transition-group>
</template>
