/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { RoleEntity } from './RoleEntity';
/**
 * 标签实体类
 */
export type TagEntity = {
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
     * 标签名称
     */
    name?: string;
    /**
     * 标签状态
     */
    status?: string;
    /**
     * 标签分组
     */
    tagGroup?: string;
};

