import {RouteRecordRaw} from 'vue-router';

const routes: Readonly<RouteRecordRaw[]> = [
    {
        path: '/',
        name: 'home',
        redirect: {
            name: "app_client_page" // 如果首页为动态的自定义页面则改为 'app_client_page'
        }
    },
    {
        path: '/app',
        component: () => import('~/views/app/pages/index.vue'),
        redirect: {
            name: "app_client_page"
        },
        children: [
            // {
            //     path: '',
            //     name: 'app_home',
            //     components: {
            //         default: () => import('~/views/app/pages/home/index.vue'),
            //         header: () => import('~/views/app/layout/AppHeader.vue'),
            //     }
            // },
            {
                path: 'client_page',
                name: "app_client_page",
                components: {
                    default: () => import('~/views/app/layout/CustomClientPage.vue'),
                    header: () => import('~/views/app/layout/AppHeader.vue'),
                }
            },
        ]
    },
    {
        path: '/login',
        name: 'login',
        component: () => import('~/views/pages/Login.vue'),
    },
    {
        path: '/admin',
        component: () => import('~/views/admin/pages/index.vue'),
        children: [
            {
                path: '',
                name: 'admin_home',
                components: {
                    default: () => import('~/views/admin/layout/AdminHome.vue'),
                    menu: () => import('~/views/admin/layout/AdminMenu.vue'),
                    header: () => import('~/views/admin/layout/AdminHeader.vue'),
                }
            },
            {
                path: 'user',
                name: 'admin_user',
                components: {
                    default: () => import('~/views/admin/pages/auth/user/index.vue'),
                    menu: () => import('~/views/admin/layout/AdminMenu.vue'),
                    header: () => import('~/views/admin/layout/AdminHeader.vue'),
                },
            },
            {
                path: 'role',
                name: 'admin_role',
                components: {
                    default: () => import('~/views/admin/pages/auth/role/index.vue'),
                    menu: () => import('~/views/admin/layout/AdminMenu.vue'),
                    header: () => import('~/views/admin/layout/AdminHeader.vue'),
                },
            },
            {
                path: 'permission',
                name: 'admin_permission',
                components: {
                    default: () => import('~/views/admin/pages/auth/permission/index.vue'),
                    menu: () => import('~/views/admin/layout/AdminMenu.vue'),
                    header: () => import('~/views/admin/layout/AdminHeader.vue'),
                },
            },
            {
                path: 'menu',
                name: 'admin_menu',
                components: {
                    default: () => import('~/views/admin/pages/auth/menu/index.vue'),
                    menu: () => import('~/views/admin/layout/AdminMenu.vue'),
                    header: () => import('~/views/admin/layout/AdminHeader.vue'),
                },
            },
            {
                path: 'log',
                name: 'admin_log',
                components: {
                    default: () => import('~/views/admin/pages/information/log/index.vue'),
                    menu: () => import('~/views/admin/layout/AdminMenu.vue'),
                    header: () => import('~/views/admin/layout/AdminHeader.vue'),
                },
            },
            {
                path: 'image',
                name: 'admin_image',
                components: {
                    default: () => import('~/views/admin/pages/information/image/index.vue'),
                    menu: () => import('~/views/admin/layout/AdminMenu.vue'),
                    header: () => import('~/views/admin/layout/AdminHeader.vue'),
                },
            },
            {
                path: 'client-page',
                name: 'admin_client_page',
                components: {
                    default: () => import('~/views/admin/pages/auth/client-page/index.vue'),
                    menu: () => import('~/views/admin/layout/AdminMenu.vue'),
                    header: () => import('~/views/admin/layout/AdminHeader.vue'),
                },
            },
            {
                path: 'department',
                name: 'admin_department',
                components: {
                    default: () => import('~/views/admin/pages/auth/department/index.vue'),
                    menu: () => import('~/views/admin/layout/AdminMenu.vue'),
                    header: () => import('~/views/admin/layout/AdminHeader.vue'),
                },
            },
            {
                path: 'tag',
                name: 'admin_tag',
                components: {
                    default: () => import('~/views/admin/pages/information/tag/index.vue'),
                    menu: () => import('~/views/admin/layout/AdminMenu.vue'),
                    header: () => import('~/views/admin/layout/AdminHeader.vue'),
                },
            },
        ]
    },
    {
        path: '/403',
        name: '403',
        component: () => import('~/views/pages/NoPermission.vue'),
    },
    {
        path: '/500',
        name: '500',
        component: () => import('~/views/pages/Error.vue'),
    },
    {
        path: '/404',
        name: '404',
        component: () => import('~/views/pages/NotFound.vue'),
    },
    {
        path: '/:catchAll(.*)*',
        redirect: {
            name: '404',
        },
    },
];
export default routes;