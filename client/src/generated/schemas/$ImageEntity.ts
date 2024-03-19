/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $ImageEntity = {
    description: `图片实体类`,
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
            description: `图片名称`,
        },
        url: {
            type: 'string',
            description: `图片地址`,
        },
        type: {
            type: 'string',
            description: `图片类型`,
        },
        size: {
            type: 'string',
            description: `图片大小`,
        },
        status: {
            type: 'string',
            description: `图片状态`,
        },
        fileId: {
            type: 'string',
            description: `图片文件id`,
        },
        file: {
            type: 'FileEntity',
        },
    },
} as const;
