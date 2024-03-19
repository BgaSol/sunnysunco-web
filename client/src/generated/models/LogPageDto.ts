/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
/**
 * 日志分页请求数据
 */
export type LogPageDto = {
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
    order?: LogPageDto.order;
    /**
     * 用户名
     */
    username?: string;
    /**
     * 客户端ip
     */
    ip?: string;
};
export namespace LogPageDto {
    /**
     * 排序方式:asc, desc
     */
    export enum order {
        ASC = 'ASC',
        DESC = 'DESC',
    }
}

