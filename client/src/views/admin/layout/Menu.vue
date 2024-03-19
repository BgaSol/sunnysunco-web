<script lang='ts' setup>

import {useMenu} from "~/pinia/modules/menu";
import MenuNode from "./MenuNode.vue";

const menu = useMenu();
menu.getMenuList();
</script>

<template>
  <el-menu :collapse='menu.collapse' :default-active='String(menu.activeMenu)' class="h-full" mode='vertical'>
    <template v-for='menuItem in menu.menus' :key='menu.id'>
      <menu-node v-if='!menuItem.isHidden' :menu='menuItem'></menu-node>
    </template>
  </el-menu>
</template>