/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $PermissionEntity = {
    description: `权限实体`,
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
            description: `权限名`,
        },
        description: {
            type: 'string',
            description: `权限描述`,
        },
        code: {
            type: 'string',
            description: `权限编码`,
        },
        status: {
            type: 'number',
            description: `权限状态`,
            format: 'int32',
        },
        type: {
            type: 'number',
            description: `权限类型`,
            format: 'int32',
        },
        roles: {
            type: 'array',
            contains: {
                type: 'RoleEntity',
            },
        },
    },
} as const;
