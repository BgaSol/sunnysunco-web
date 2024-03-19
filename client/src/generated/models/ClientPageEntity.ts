/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { ImageEntity } from './ImageEntity';
/**
 * 客户端页面实体类
 */
export type ClientPageEntity = {
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
     * 页面名称
     */
    name?: string;
    images?: Array<ImageEntity>;
    /**
     * 页面HTML
     */
    html?: string;
};

