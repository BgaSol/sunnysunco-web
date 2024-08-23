import * as IconParkIcons from '@icon-park/vue-next/es/map'
import '@icon-park/vue-next/styles/index.css';
import {useIcons} from "~/pinia/modules/icons";
import {Plugin} from "@vue/runtime-core";

const IconParkIconsPlugin: Plugin = {
    install(app) {
        const icons = useIcons();
        const entries = Object.entries(IconParkIcons);
        icons.addIcon(...entries.map(([key]) => key))
        for (const [key, component] of entries) {
            app.component(key, component);
        }
    },
}
export default IconParkIconsPlugin;