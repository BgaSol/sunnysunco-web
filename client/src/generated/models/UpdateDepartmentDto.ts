/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
/**
 * 更新部门Dto
 */
export type UpdateDepartmentDto = {
    /**
     * 主键
     */
    id: string;
    /**
     * 排序
     */
    sort?: number;
    /**
     * 部门描述
     */
    description?: string;
    /**
     * 部门名
     */
    name?: string;
    /**
     * 部门编码
     */
    code?: string;
    /**
     * 部门域名
     */
    domain?: string;
    /**
     * 部门页面ids
     */
    pageIds?: Array<string>;
    /**
     * 部门地址
     */
    address?: string;
    /**
     * 部门电话
     */
    phone?: string;
    /**
     * 部门备注HTML
     */
    html?: string;
    /**
     * 部门图标id
     */
    iconId?: string;
    /**
     * 父部门id
     */
    parentId?: string;
};

