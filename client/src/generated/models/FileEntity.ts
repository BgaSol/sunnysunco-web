/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
/**
 * 文件实体类
 */
export type FileEntity = {
    id?: string;
    /**
     * 排序
     */
    sort?: number;
    /**
     * 创建时间
     */
    createTime?: string;
    /**
     * 更新时间
     */
    updateTime?: string;
    /**
     * 版本号
     */
    version?: number;
    /**
     * 描述
     */
    description?: string;
    /**
     * 文件名称
     */
    name?: string;
    /**
     * 文件地址
     */
    url?: string;
    /**
     * 文件类型
     */
    type?: string;
    /**
     * 文件大小
     */
    size?: string;
    /**
     * 文件hash
     */
    hash?: string;
    /**
     * 文件状态
     */
    status?: string;
    /**
     * 文件后缀
     */
    suffix?: string;
    /**
     * 文件来源
     */
    source?: string;
    /**
     * 文件所在minio桶
     */
    bucket?: string;
};

