/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $FileEntity = {
    description: `文件实体类`,
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
            description: `文件名称`,
        },
        url: {
            type: 'string',
            description: `文件地址`,
        },
        type: {
            type: 'string',
            description: `文件类型`,
        },
        size: {
            type: 'string',
            description: `文件大小`,
        },
        hash: {
            type: 'string',
            description: `文件hash`,
        },
        status: {
            type: 'string',
            description: `文件状态`,
        },
        suffix: {
            type: 'string',
            description: `文件后缀`,
        },
        source: {
            type: 'string',
            description: `文件来源`,
        },
    },
} as const;
