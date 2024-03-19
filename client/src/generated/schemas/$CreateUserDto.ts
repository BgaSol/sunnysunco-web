/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $CreateUserDto = {
    description: `创建用户数据传输对象`,
    properties: {
        username: {
            type: 'string',
            description: `用户名`,
            isRequired: true,
        },
        password: {
            type: 'string',
            description: `密码`,
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
            description: `停用时间 没有停用时间则是永久有效`,
            format: 'date-time',
        },
        locked: {
            type: 'boolean',
            description: `账户锁定`,
            isRequired: true,
        },
    },
} as const;
