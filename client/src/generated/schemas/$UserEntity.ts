/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $UserEntity = {
    description: `用户实体类`,
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
        username: {
            type: 'string',
            description: `用户名`,
        },
        password: {
            type: 'string',
            description: `密码`,
        },
        nickname: {
            type: 'string',
            description: `昵称`,
        },
        email: {
            type: 'string',
            description: `邮箱`,
        },
        phone: {
            type: 'string',
            description: `手机号`,
        },
        status: {
            type: 'string',
            description: `状态`,
        },
        avatarId: {
            type: 'string',
            description: `头像id`,
        },
        avatar: {
            type: 'ImageEntity',
        },
        description: {
            type: 'string',
            description: `描述`,
        },
        activatedAt: {
            type: 'string',
            description: `激活时间`,
            format: 'date-time',
        },
        deactivatedAt: {
            type: 'string',
            description: `停用时间`,
            format: 'date-time',
        },
        locked: {
            type: 'boolean',
            description: `账户锁定`,
        },
        roles: {
            type: 'array',
            contains: {
                type: 'RoleEntity',
            },
        },
    },
} as const;
