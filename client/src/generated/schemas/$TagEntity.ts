/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $TagEntity = {
    description: `标签实体类`,
    properties: {
        id: {
            type: 'string',
        },
        sort: {
            type: 'number',
            description: `排序`,
            format: 'int32',
        },
        createTime: {
            type: 'string',
            description: `创建时间`,
            format: 'date-time',
        },
        updateTime: {
            type: 'string',
            description: `更新时间`,
            format: 'date-time',
        },
        version: {
            type: 'number',
            description: `版本号`,
            format: 'int32',
        },
        name: {
            type: 'string',
            description: `标签名称`,
        },
        status: {
            type: 'string',
            description: `标签状态`,
        },
        type: {
            type: 'string',
            description: `标签类型`,
        },
    },
} as const;
