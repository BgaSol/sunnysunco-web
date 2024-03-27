import * as ElementPlusIconsVue from '@element-plus/icons-vue';
import {useIcons} from '~/pinia/modules/icons';
import {Plugin} from '@vue/runtime-core';

const ElementPlusIcons: Plugin = {
    install(app) {
        const icons = useIcons();
        const entries = Object.entries(ElementPlusIconsVue);
        icons.setElIcons(entries.map(([key]) => key))
        for (const [key, component] of entries) {
            app.component(key, component);
        }
    },
};
export default ElementPlusIcons;
