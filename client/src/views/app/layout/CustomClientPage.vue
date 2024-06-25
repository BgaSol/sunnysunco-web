<script lang="ts" setup>
import {useRoute, useRouter} from "vue-router";
import {ref, watch} from "vue";
import {ClientPageEntity, Service} from "~/generated";
import {getMyClientPageImageUrl} from "~/api/HttpRequest";
import Home from "~/views/app/pages/home/index.vue";

const route = useRoute();
const router = useRouter();
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
  <template v-if="route.query?.page">
    <transition-group enter-active-class="animate__animated animate__zoomInRight">
      <el-image v-for="image in clientPage.images"
                :key="image.id" :src="getMyClientPageImageUrl(image.id as string)"
                class="w-full pointer-events-none el-image-block important-block">
      </el-image>
    </transition-group>
  </template>
  <home v-else/>
</template>
