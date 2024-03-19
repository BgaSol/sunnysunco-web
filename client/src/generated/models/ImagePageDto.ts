/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
/**
 * 图片分页查询参数
 */
export type ImagePageDto = {
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
    order?: ImagePageDto.order;
    /**
     * 图片名称
     */
    name?: string;
    /**
     * 图片类型
     */
    type?: string;
};
export namespace ImagePageDto {
    /**
     * 排序方式:asc, desc
     */
    export enum order {
        ASC = 'ASC',
        DESC = 'DESC',
    }
}

