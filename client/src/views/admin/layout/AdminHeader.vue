<script lang='ts' setup>
import {useDark} from "@vueuse/core";

import {useMenu} from "~/pinia/modules/menu";
import {computed, ref} from "vue";
import {MenuEntity} from "~/generated";
import {getImageUrl} from "~/api/HttpRequest";
import {useUser} from "~/pinia/modules/user";
import UpdatePassword from "~/views/admin/pages/auth/user/UpdatePassword.vue";

const isDark = useDark();
const menu = useMenu();
const user = useUser();
const activeMenuList = computed(() => {
  return findMenuListByMenusTree(menu.menus, menu.activeMenu);
});

const findMenuListByMenusTree = (menus: MenuEntity[], routeName: string): MenuEntity[] => {
  let result: MenuEntity[] = [];
  for (let menu of menus) {
    if (menu.routeName === routeName) {
      result.push(menu);
      return result;
    }
    if (menu.children) {
      result = findMenuListByMenusTree(menu.children, routeName);
      if (result.length > 0) {
        result.unshift(menu);
        return result;
      }
    }
  }
  return result;
};
const updatePasswordRef = ref<InstanceType<typeof UpdatePassword>>();
</script>

<template>
  <el-menu :default-active="menu.activeMenu" :ellipsis="false" mode="horizontal">
    <el-icon class='m-auto important-w-64px cursor-pointer' @click='menu.collapse = !menu.collapse'>
      <component :is='menu.collapse ? "Expand":"Fold"'/>
    </el-icon>
    <el-menu-item v-if="user.user?.department" @click="$router.push({name:'admin_home'})">
      <el-image v-if="user?.user?.department?.icon?.id" fit="contain"
                :src="getImageUrl(user.user.department.icon.id as string)"
                class="h-9 m-auto el-image-block"></el-image>
      <div v-if="user.user?.department?.name" class="title important-ml-4 font-bold font-size-5">
        {{ user.user?.department?.name }}
      </div>
    </el-menu-item>
    <div class="flex-1 flex-row flex flex-items-center ml-20px">
      <el-breadcrumb separator-icon='ArrowRight'>
        <transition-group enter-active-class="animate__animated animate__fadeInRight">
          <el-breadcrumb-item v-for='breadcrumb in activeMenuList' :key='breadcrumb.id'
                              :to='breadcrumb.routeName && {name:breadcrumb.routeName}' replace>
            {{ breadcrumb.name }}
          </el-breadcrumb-item>
        </transition-group>
      </el-breadcrumb>
    </div>
    <el-switch v-model="isDark" active-action-icon="Sunny"
               class="mt-auto mb-auto mr-20px" inactive-action-icon="Moon">
    </el-switch>
    <el-menu-item @click="$router.push({name:'home'})">客户端</el-menu-item>
    <el-sub-menu index="other-menus">
      <template #title>{{ user.user?.username }}</template>
      <el-menu-item @click="updatePasswordRef?.openDialog">修改密码</el-menu-item>
      <update-password ref="updatePasswordRef"/>
      <el-menu-item @click="user.logout(()=>$router.replace({name:'home'}))">
        退出登录
      </el-menu-item>
    </el-sub-menu>
  </el-menu>
</template>
