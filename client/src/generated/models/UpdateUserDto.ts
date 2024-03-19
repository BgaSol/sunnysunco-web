/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
/**
 * 更新用户数据传输对象
 */
export type UpdateUserDto = {
    /**
     * 主键
     */
    id: string;
    /**
     * 排序
     */
    sort?: number;
    /**
     * 描述
     */
    description?: string;
    /**
     * 用户名
     */
    username: string;
    /**
     * 昵称
     */
    nickname?: string;
    /**
     * 邮箱
     */
    email?: string;
    /**
     * 手机号
     */
    phone?: string;
    /**
     * 账户锁定
     */
    locked: boolean;
    /**
     * 角色id列表
     */
    roleIds?: Array<string>;
    /**
     * 部门id
     */
    departmentId?: string;
};

