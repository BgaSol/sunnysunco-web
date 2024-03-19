/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $PageDtoImageEntity = {
    description: `分页查询参数`,
    properties: {
        page: {
            type: 'number',
            description: `页码`,
            format: 'int32',
        },
        size: {
            type: 'number',
            description: `每页条数`,
            format: 'int32',
        },
        sort: {
            type: 'string',
            description: `排序字段`,
        },
        order: {
            type: 'string',
            description: `排序方式:asc, desc`,
        },
    },
} as const;
