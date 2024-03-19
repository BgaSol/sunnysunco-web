/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $LoginUserDto = {
    description: `登录用户数据传输对象`,
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
        verificationCode: {
            type: 'string',
            description: `验证码`,
            isRequired: true,
        },
        verificationCodeKey: {
            type: 'string',
            description: `验证码key`,
            isRequired: true,
        },
    },
} as const;
