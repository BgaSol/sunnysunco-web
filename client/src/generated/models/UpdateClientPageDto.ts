/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
/**
 * 更新客户端页面
 */
export type UpdateClientPageDto = {
    /**
     * 主键
     */
    id: string;
    /**
     * 排序
     */
    sort?: number;
    /**
     * 页面描述
     */
    description: string;
    /**
     * 页面名称
     */
    name: string;
    /**
     * 页面图片id列表
     */
    imageIds?: Array<string>;
    /**
     * 页面HTML
     */
    html?: string;
};

