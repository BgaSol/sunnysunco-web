/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
/**
 * 菜单实体
 */
export type MenuEntity = {
    id?: string;
    /**
     * 排序
     */
    sort?: number;
    /**
     * 创建时间
     */
    createTime?: string;
    /**
     * 更新时间
     */
    updateTime?: string;
    /**
     * 版本号
     */
    version?: number;
    /**
     * 描述
     */
    description?: string;
    /**
     * 子权限
     */
    children?: Array<MenuEntity>;
    /**
     * 菜单名
     */
    name?: string;
    /**
     * 菜单状态
     */
    status?: number;
    /**
     * 菜单类型
     */
    type?: MenuEntity.type;
    /**
     * 菜单路由地址
     */
    path?: string;
    /**
     * 菜单图标
     */
    icon?: string;
    /**
     * 菜单路由名
     */
    routeName?: string;
    /**
     * 按钮代码
     */
    buttonCode?: string;
    /**
     * 是否是外链
     */
    isExternal?: boolean;
    /**
     * 外链地址
     */
    externalUrl?: string;
    /**
     * 是否外链新窗口打开
     */
    isExternalOpen?: boolean;
    /**
     * 是否停用（置灰）
     */
    isDisabled?: boolean;
    /**
     * 是否隐藏（不显示）
     */
    isHidden?: boolean;
    /**
     * 菜单组
     */
    menuGroup?: string;
    /**
     * 父id
     */
    parentId?: string;
};
export namespace MenuEntity {
    /**
     * 菜单类型
     */
    export enum type {
        MENU = 'MENU',
        PAGE = 'PAGE',
        BUTTON = 'BUTTON',
    }
}

