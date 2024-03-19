import {defineStore} from 'pinia';
import {ref} from 'vue';

export const useIcons = defineStore('icons', () => {

    const elIcons = ref<string[]>([]);
    const setElIcons = (icons: string[]) => {
        elIcons.value = icons;
    };
    return {elIcons, setElIcons};
});