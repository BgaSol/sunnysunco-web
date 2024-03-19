/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $IPageFileEntity = {
    description: `响应数据`,
    properties: {
        size: {
            type: 'number',
            format: 'int64',
        },
        records: {
            type: 'array',
            contains: {
                type: 'FileEntity',
            },
        },
        current: {
            type: 'number',
            format: 'int64',
        },
        pages: {
            type: 'number',
            format: 'int64',
        },
        total: {
            type: 'number',
            format: 'int64',
        },
    },
} as const;
