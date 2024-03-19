/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
/**
 * 标签分页实体类
 */
export type TagPageDto = {
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
    order?: TagPageDto.order;
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
    /**
     * 标签类型
     */
    type?: string;
};
export namespace TagPageDto {
    /**
     * 排序方式:asc, desc
     */
    export enum order {
        ASC = 'ASC',
        DESC = 'DESC',
    }
}

