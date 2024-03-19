/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { ClientPageEntity } from './ClientPageEntity';
/**
 * 分页响应数据
 */
export type PageVoClientPageEntity = {
    /**
     * 总条数
     */
    total?: number;
    /**
     * 当前页码
     */
    page?: number;
    /**
     * 每页条数
     */
    size?: number;
    /**
     * 响应数据
     */
    result?: Array<ClientPageEntity>;
};

