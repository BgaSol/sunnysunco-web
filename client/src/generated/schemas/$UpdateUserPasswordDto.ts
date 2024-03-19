/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $UpdateUserPasswordDto = {
    description: `更新用户密码数据传输对象`,
    properties: {
        id: {
            type: 'string',
            description: `主键`,
            isRequired: true,
        },
        username: {
            type: 'string',
            description: `用户名`,
            isRequired: true,
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
        description: {
            type: 'string',
            description: `描述`,
        },
        activatedAt: {
            type: 'string',
            description: `激活时间`,
            isRequired: true,
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
            isRequired: true,
        },
        oldPassword: {
            type: 'string',
            description: `旧密码`,
            isRequired: true,
        },
        newPassword: {
            type: 'string',
            description: `新密码`,
            isRequired: true,
        },
    },
} as const;
