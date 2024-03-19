/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
export const $DepartmentEntity = {
    description: `部门实体`,
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
            description: `部门名`,
        },
        description: {
            type: 'string',
            description: `部门描述`,
        },
        status: {
            type: 'number',
            description: `部门状态`,
            format: 'int32',
        },
        type: {
            type: 'number',
            description: `部门类型`,
            format: 'int32',
        },
        parentId: {
            type: 'string',
            description: `父部门id`,
        },
        parent: {
            type: 'DepartmentEntity',
        },
    },
} as const;
