/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $SaTokenInfo = {
    description: `响应数据`,
    properties: {
        tokenName: {
            type: 'string',
        },
        tokenValue: {
            type: 'string',
        },
        isLogin: {
            type: 'boolean',
        },
        loginId: {
            type: 'dictionary',
            contains: {
                properties: {
                },
            },
        },
        loginType: {
            type: 'string',
        },
        tokenTimeout: {
            type: 'number',
            format: 'int64',
        },
        sessionTimeout: {
            type: 'number',
            format: 'int64',
        },
        tokenSessionTimeout: {
            type: 'number',
            format: 'int64',
        },
        tokenActiveTimeout: {
            type: 'number',
            format: 'int64',
        },
        loginDevice: {
            type: 'string',
        },
        tag: {
            type: 'string',
        },
    },
} as const;
