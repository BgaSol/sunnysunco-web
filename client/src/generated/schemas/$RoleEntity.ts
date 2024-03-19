/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $RoleEntity = {
    description: `角色实体`,
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
            description: `角色名`,
        },
        description: {
            type: 'string',
            description: `角色描述`,
        },
        code: {
            type: 'string',
            description: `角色编码`,
        },
        status: {
            type: 'number',
            description: `角色状态`,
            format: 'int32',
        },
        type: {
            type: 'number',
            description: `角色类型`,
            format: 'int32',
        },
        users: {
            type: 'array',
            contains: {
                type: 'UserEntity',
            },
        },
        permissions: {
            type: 'array',
            contains: {
                type: 'PermissionEntity',
            },
        },
    },
} as const;
