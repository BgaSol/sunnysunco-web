<script lang="ts" setup>
import {useUser} from "~/pinia/modules/user";
import {computed, nextTick, onMounted, ref, watch} from "vue";
import {useMenu} from "~/pinia/modules/menu";
import {useDark, useMediaQuery} from "@vueuse/core";
import UpdatePassword from "~/views/admin/pages/auth/user/UpdatePassword.vue";
import {useRoute, useRouter} from "vue-router";

const user = useUser();

onMounted(() => {
  void user.getDepartment();
});

const menu = useMenu();

const login = () => {
  // 获取当前页面的url
  const url = window.location.href;
  window.location.href = `/login?redirect=${encodeURIComponent(url)}`;
}
const isBackend = computed(() => {
  if (user.user?.id === 'admin') {
    return true;
  }
  return user.user?.roles?.find(role => role.menus?.find(menu => menu.menuGroup === 'admin-master'))
})
const isDark = useDark()

const updatePasswordRef = ref<InstanceType<typeof UpdatePassword>>();

const clientPageList = computed(() => {
  return user.user?.department?.pages || user.defaultDepartment.pages || []
})

const route = useRoute();
const router = useRouter();

const activeMenu = computed(() => route.query.page as string || menu.activeMenu);
const checkActiveMenu = (id: string) => {
  router.push({name: 'app_client_page', query: {page: id}})
}
// 页面初始化时，如果没有page参数，就默认选中第一个
watch(clientPageList, () => {
  if (clientPageList.value.length > 0) {
    if (!route.query.page && route.name === 'app_client_page') {
      nextTick(() => {
        checkActiveMenu(clientPageList.value[0].id as string)
      })
    }
  }
}, {immediate: true})

const toPage = (name: string) => {
  router.push({name});
}

const isPc = useMediaQuery('(min-width: 768px)')
const drawer = ref(false)
</script>

<template>
  <el-menu :default-active="activeMenu" :ellipsis="false" mode="horizontal">
    <el-menu-item @click="$router.push({name:'home'})">
      <el-image v-if="user.departmentLogoAndName.isShowLogo" :src="user.departmentLogoAndName.logoUrl"
                class="h-9 m-auto el-image-block"></el-image>
      <div v-if="user.departmentLogoAndName.isShowLogoName" class="title important-ml-4 font-bold font-size-6">
        {{ user.departmentLogoAndName.logoName }}
      </div>
    </el-menu-item>
    <template v-if="isPc">
      <!--    <el-menu-item @click="toPage('app_home')" index="app_home">首页</el-menu-item>-->
      <el-menu-item v-for="page in clientPageList" :key="page.id" :index="page.id" :page="page.id"
                    @click="checkActiveMenu(page.id as string)">
        {{ page.name }}
      </el-menu-item>
      <div class="flex-grow"/>
      <el-switch v-model="isDark" active-action-icon="Sunny"
                 class="mt-auto mb-auto mr-4" inactive-action-icon="Moon">
      </el-switch>
      <el-menu-item v-if="isBackend" @click="$router.replace({name:'admin_home'})">后台管理</el-menu-item>
      <el-sub-menu v-if="user.userToken" index="users">
        <template #title>{{ user.user?.username }}</template>
        <el-menu-item @click="updatePasswordRef?.openDialog">修改密码</el-menu-item>
        <update-password ref="updatePasswordRef"/>
        <el-menu-item @click="user.logout(()=>$router.replace({name:'home'}))">
          退出登录
        </el-menu-item>
      </el-sub-menu>
      <el-menu-item v-if="(route.name!=='login')&&!user.userToken" @click="login">
        登录
      </el-menu-item>
    </template>
    <template v-if="!isPc">
      <div class="flex-grow"/>
      <el-switch v-model="isDark" active-action-icon="Sunny"
                 class="mt-auto mb-auto mr-4" inactive-action-icon="Moon">
      </el-switch>
      <el-menu-item v-if="(route.name!=='login')" @click="drawer = true">
        <el-icon>
          <Memo/>
        </el-icon>
      </el-menu-item>
      <el-drawer v-model="drawer" size="50%" direction="ttb" :with-header="false">
        <el-menu :default-active="activeMenu" mode="vertical">
          <!--    <el-menu-item @click="toPage('app_home')" index="app_home">首页</el-menu-item>-->
          <el-menu-item v-for="page in clientPageList" :key="page.id" :index="page.id" :page="page.id"
                        @click="checkActiveMenu(page.id as string)">
            {{ page.name }}
          </el-menu-item>
          <el-menu-item v-if="isBackend" @click="$router.replace({name:'admin_home'})">后台管理</el-menu-item>
          <el-sub-menu v-if="user.userToken" index="users">
            <template #title>{{ user.user?.username }}</template>
            <el-menu-item @click="updatePasswordRef?.openDialog">修改密码</el-menu-item>
            <update-password ref="updatePasswordRef"/>
            <el-menu-item @click="user.logout(()=>$router.replace({name:'home'}))">
              退出登录
            </el-menu-item>
          </el-sub-menu>
          <el-menu-item v-if="(route.name!=='login')&&!user.userToken" @click="login">
            登录
          </el-menu-item>
        </el-menu>
      </el-drawer>
    </template>
  </el-menu>
</template>