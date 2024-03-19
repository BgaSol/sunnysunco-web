/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $MenuEntity = {
    description: `菜单实体`,
    properties: {
        id: {
            type: 'string',
        },
        sort: {
            type: 'number',
            description: `排序`,
            format: 'int32',
        },
        createTime: {
            type: 'string',
            description: `创建时间`,
            format: 'date-time',
        },
        updateTime: {
            type: 'string',
            description: `更新时间`,
            format: 'date-time',
        },
        version: {
            type: 'number',
            description: `版本号`,
            format: 'int32',
        },
        name: {
            type: 'string',
            description: `菜单名`,
        },
        description: {
            type: 'string',
            description: `菜单描述`,
        },
        status: {
            type: 'number',
            description: `菜单状态`,
            format: 'int32',
        },
        type: {
            type: 'Enum',
        },
        parentId: {
            type: 'string',
            description: `父菜单id`,
        },
        parent: {
            type: 'MenuEntity',
        },
        path: {
            type: 'string',
            description: `菜单路由地址`,
        },
        icon: {
            type: 'string',
            description: `菜单图标`,
        },
        routeName: {
            type: 'string',
            description: `菜单路由名`,
        },
        buttonCode: {
            type: 'string',
            description: `按钮代码`,
        },
        isExternal: {
            type: 'boolean',
            description: `是否是外链`,
        },
        externalUrl: {
            type: 'string',
            description: `外链地址`,
        },
        isExternalOpen: {
            type: 'boolean',
            description: `是否外链新窗口打开`,
        },
        isDisabled: {
            type: 'boolean',
            description: `是否停用（置灰）`,
        },
        isHidden: {
            type: 'boolean',
            description: `是否隐藏（不显示）`,
        },
        menuGroup: {
            type: 'string',
            description: `菜单组`,
        },
    },
} as const;
