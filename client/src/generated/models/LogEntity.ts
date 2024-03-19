/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { UserEntity } from './UserEntity';
/**
 * 日志实体类
 */
export type LogEntity = {
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
     * 日志类型
     */
    type?: string;
    /**
     * 请求地址
     */
    url?: string;
    /**
     * 请求方法
     */
    method?: string;
    /**
     * 请求来源
     */
    referer?: string;
    /**
     * 客户端ip
     */
    ip?: string;
    /**
     * 客户端浏览器
     */
    browser?: string;
    /**
     * 客户端操作系统
     */
    os?: string;
    /**
     * 用户id
     */
    userId?: string;
    user?: UserEntity;
};

