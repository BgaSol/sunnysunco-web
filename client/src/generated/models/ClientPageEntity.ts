/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { ImageEntity } from './ImageEntity';
import type { RoleEntity } from './RoleEntity';
/**
 * 客户端页面实体类
 */
export type ClientPageEntity = {
    id?: string;
    /**
     * 类型
     */
    type?: string;
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
     * 描述
     */
    description?: string;
    /**
     * 可访问该行数据的角色
     */
    accessRole?: Array<RoleEntity>;
    /**
     * 页面名称
     */
    name?: string;
    /**
     * 页面图片列表
     */
    images?: Array<ImageEntity>;
    /**
     * 页面HTML
     */
    html?: string;
};

