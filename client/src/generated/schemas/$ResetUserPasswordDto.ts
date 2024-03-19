/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $ResetUserPasswordDto = {
    description: `重置用户密码数据传输对象`,
    properties: {
        id: {
            type: 'string',
            description: `主键`,
            isRequired: true,
        },
        password: {
            type: 'string',
            description: `新密码`,
            isRequired: true,
        },
    },
} as const;
