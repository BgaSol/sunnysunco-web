/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
/**
 * 客户端自定义页面分页dto
 */
export type ClientPagePageDto = {
    /**
     * 页码
     */
    page: number;
    /**
     * 每页条数
     */
    size: number;
    /**
     * 排序字段
     */
    sort?: string;
    /**
     * 排序方式:asc, desc
     */
    order?: ClientPagePageDto.order;
    /**
     * 页面名称
     */
    name?: string;
    /**
     * 页面描述
     */
    description?: string;
};
export namespace ClientPagePageDto {
    /**
     * 排序方式:asc, desc
     */
    export enum order {
        ASC = 'ASC',
        DESC = 'DESC',
    }
}

