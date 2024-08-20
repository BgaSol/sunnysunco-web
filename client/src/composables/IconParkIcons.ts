import {App} from "vue";
import {install} from '@icon-park/vue-next/es/all';
import '@icon-park/vue-next/styles/index.css';

export const initIconParkIcons = (app: App) => {
    install(app)
}