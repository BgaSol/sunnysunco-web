import 'nprogress/nprogress.css';
import {createRouter, createWebHistory} from 'vue-router';
import {useNProgress} from '@vueuse/integrations/useNProgress';
import routes from '~/router/routes';
import {useMenu} from "~/pinia/modules/menu";

export const initRouter = () => {
    const menu = useMenu();
    const router = createRouter({
        history: createWebHistory(),
        routes,
    });
    // 页面加载进度条
    const nProgress = useNProgress();
    router.beforeEach(async (to, from, next) => {
        nProgress.start();
        menu.setActiveMenu(to.name as string);
        next();
    });
    router.afterEach(() => {
        nProgress.done();
    });
    return router;
};
export default initRouter;