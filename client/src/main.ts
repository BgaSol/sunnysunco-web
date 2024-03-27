import '~/styles/index.scss';
import 'virtual:uno.css'
import 'animate.css';

import {createApp} from 'vue';
import "./api/HttpRequest";
import App from './views/App.vue';
import pinia from '~/pinia';
import {initRouter} from '~/router';
import ElementPlusIcons from '~/composables/ElementPlusIcons';

const app = createApp(App);
app.use(pinia);

app.use(initRouter());

app.use(ElementPlusIcons);

app.mount('#app');
