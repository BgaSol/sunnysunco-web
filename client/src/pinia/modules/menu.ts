import {defineStore} from "pinia";
import {ref} from "vue";
import {MenuEntity, Service} from "~/generated";

export const useMenu = defineStore('menu', () => {
    // 活动菜单
    const activeMenu = ref<string>('');
    const setActiveMenu = (menu: string) => {
        activeMenu.value = menu;
    };
    const menus = ref<MenuEntity[]>([]);
    const getMenuList = async () => {
        const menuList = await Service.findAdminMenuGroup()
        menus.value = <MenuEntity[]>menuList.data;
    }
    const collapse = ref<boolean>(false);
    return {activeMenu, setActiveMenu, menus, getMenuList, collapse};
})
