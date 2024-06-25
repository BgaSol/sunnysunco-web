/* generated using openapi-typescript-codegen -- do no edit */
/* istanbul ignore file */
/* tslint:disable */
/* eslint-disable */
import type { BaseVoClientPageEntity } from '../models/BaseVoClientPageEntity';
import type { BaseVoDepartmentEntity } from '../models/BaseVoDepartmentEntity';
import type { BaseVoFileEntity } from '../models/BaseVoFileEntity';
import type { BaseVoImageEntity } from '../models/BaseVoImageEntity';
import type { BaseVoInteger } from '../models/BaseVoInteger';
import type { BaseVoListClientPageEntity } from '../models/BaseVoListClientPageEntity';
import type { BaseVoListDepartmentEntity } from '../models/BaseVoListDepartmentEntity';
import type { BaseVoListMenuEntity } from '../models/BaseVoListMenuEntity';
import type { BaseVoListPermissionEntity } from '../models/BaseVoListPermissionEntity';
import type { BaseVoListRoleEntity } from '../models/BaseVoListRoleEntity';
import type { BaseVoListUserEntity } from '../models/BaseVoListUserEntity';
import type { BaseVoObject } from '../models/BaseVoObject';
import type { BaseVoPageVoClientPageEntity } from '../models/BaseVoPageVoClientPageEntity';
import type { BaseVoPageVoImageEntity } from '../models/BaseVoPageVoImageEntity';
import type { BaseVoPageVoLogEntity } from '../models/BaseVoPageVoLogEntity';
import type { BaseVoPageVoTagEntity } from '../models/BaseVoPageVoTagEntity';
import type { BaseVoPageVoUserEntity } from '../models/BaseVoPageVoUserEntity';
import type { BaseVoRoleEntity } from '../models/BaseVoRoleEntity';
import type { BaseVoSaTokenInfo } from '../models/BaseVoSaTokenInfo';
import type { BaseVoTagEntity } from '../models/BaseVoTagEntity';
import type { BaseVoUserEntity } from '../models/BaseVoUserEntity';
import type { BaseVoVerificationVo } from '../models/BaseVoVerificationVo';
import type { ClientPagePageDto } from '../models/ClientPagePageDto';
import type { CreateClientPageDto } from '../models/CreateClientPageDto';
import type { CreateDepartmentDto } from '../models/CreateDepartmentDto';
import type { CreateFileDto } from '../models/CreateFileDto';
import type { CreateImageDto } from '../models/CreateImageDto';
import type { CreateRoleDto } from '../models/CreateRoleDto';
import type { CreateTagDto } from '../models/CreateTagDto';
import type { CreateUserDto } from '../models/CreateUserDto';
import type { ImagePageDto } from '../models/ImagePageDto';
import type { LoginUserDto } from '../models/LoginUserDto';
import type { LogPageDto } from '../models/LogPageDto';
import type { ResetUserPasswordDto } from '../models/ResetUserPasswordDto';
import type { TagPageDto } from '../models/TagPageDto';
import type { UpdateClientPageDto } from '../models/UpdateClientPageDto';
import type { UpdateDepartmentDto } from '../models/UpdateDepartmentDto';
import type { UpdateImageDto } from '../models/UpdateImageDto';
import type { UpdateRoleDto } from '../models/UpdateRoleDto';
import type { UpdateTagDto } from '../models/UpdateTagDto';
import type { UpdateUserDto } from '../models/UpdateUserDto';
import type { UpdateUserPasswordDto } from '../models/UpdateUserPasswordDto';
import type { UserPageDto } from '../models/UserPageDto';
import type { CancelablePromise } from '../core/CancelablePromise';
import { OpenAPI } from '../core/OpenAPI';
import { request as __request } from '../core/request';
export class Service {
    /**
     * 查询所有用户
     * @returns BaseVoListUserEntity OK
     * @throws ApiError
     */
    public static findAllUser(): CancelablePromise<BaseVoListUserEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/user',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 更新用户
     * @param requestBody
     * @returns BaseVoUserEntity OK
     * @throws ApiError
     */
    public static updateUser(
        requestBody: UpdateUserDto,
    ): CancelablePromise<BaseVoUserEntity> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/user',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 保存用户
     * @param requestBody
     * @returns BaseVoUserEntity OK
     * @throws ApiError
     */
    public static saveUser(
        requestBody: CreateUserDto,
    ): CancelablePromise<BaseVoUserEntity> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/user',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 修改用户密码
     * @param requestBody
     * @returns BaseVoUserEntity OK
     * @throws ApiError
     */
    public static updateUserPassword(
        requestBody: UpdateUserPasswordDto,
    ): CancelablePromise<BaseVoUserEntity> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/user/update-password',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 重置用户密码
     * @param requestBody
     * @returns BaseVoUserEntity OK
     * @throws ApiError
     */
    public static resetUserPassword(
        requestBody: ResetUserPasswordDto,
    ): CancelablePromise<BaseVoUserEntity> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/user/reset-password',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 更新标签
     * @param requestBody
     * @returns BaseVoTagEntity OK
     * @throws ApiError
     */
    public static updateTag(
        requestBody: UpdateTagDto,
    ): CancelablePromise<BaseVoTagEntity> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/tag',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 新增标签
     * @param requestBody
     * @returns BaseVoTagEntity OK
     * @throws ApiError
     */
    public static saveTag(
        requestBody: CreateTagDto,
    ): CancelablePromise<BaseVoTagEntity> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/tag',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 查询所有角色
     * @returns BaseVoListRoleEntity OK
     * @throws ApiError
     */
    public static findAllRole(): CancelablePromise<BaseVoListRoleEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/role',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 更新角色
     * @param requestBody
     * @returns BaseVoRoleEntity OK
     * @throws ApiError
     */
    public static updateRole(
        requestBody: UpdateRoleDto,
    ): CancelablePromise<BaseVoRoleEntity> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/role',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 新增角色
     * @param requestBody
     * @returns BaseVoRoleEntity OK
     * @throws ApiError
     */
    public static saveRole(
        requestBody: CreateRoleDto,
    ): CancelablePromise<BaseVoRoleEntity> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/role',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 更新图片
     * @param requestBody
     * @returns BaseVoImageEntity OK
     * @throws ApiError
     */
    public static updateImage(
        requestBody: UpdateImageDto,
    ): CancelablePromise<BaseVoImageEntity> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/image',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 新增图片
     * @param requestBody
     * @returns BaseVoImageEntity OK
     * @throws ApiError
     */
    public static saveImage(
        requestBody: CreateImageDto,
    ): CancelablePromise<BaseVoImageEntity> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/image',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 查询所有部门
     * @returns BaseVoListDepartmentEntity OK
     * @throws ApiError
     */
    public static findAllDepartment(): CancelablePromise<BaseVoListDepartmentEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/department',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 更新部门
     * @param requestBody
     * @returns BaseVoDepartmentEntity OK
     * @throws ApiError
     */
    public static updateDepartment(
        requestBody: UpdateDepartmentDto,
    ): CancelablePromise<BaseVoDepartmentEntity> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/department',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 新增部门
     * @param requestBody
     * @returns BaseVoDepartmentEntity OK
     * @throws ApiError
     */
    public static saveDepartment(
        requestBody: CreateDepartmentDto,
    ): CancelablePromise<BaseVoDepartmentEntity> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/department',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 查询所有页面
     * @returns BaseVoListClientPageEntity OK
     * @throws ApiError
     */
    public static findAllClientPage(): CancelablePromise<BaseVoListClientPageEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/client-page',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 更新页面
     * @param requestBody
     * @returns BaseVoClientPageEntity OK
     * @throws ApiError
     */
    public static updateClientPage(
        requestBody: UpdateClientPageDto,
    ): CancelablePromise<BaseVoClientPageEntity> {
        return __request(OpenAPI, {
            method: 'PUT',
            url: '/client-page',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 保存页面
     * @param requestBody
     * @returns BaseVoClientPageEntity OK
     * @throws ApiError
     */
    public static saveClientPage(
        requestBody: CreateClientPageDto,
    ): CancelablePromise<BaseVoClientPageEntity> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/client-page',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 分页查询用户
     * @param requestBody
     * @returns BaseVoPageVoUserEntity OK
     * @throws ApiError
     */
    public static findByUserPage(
        requestBody: UserPageDto,
    ): CancelablePromise<BaseVoPageVoUserEntity> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/user/page',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 用户登出
     * @returns BaseVoObject OK
     * @throws ApiError
     */
    public static logout(): CancelablePromise<BaseVoObject> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/user/logout',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 用户登录
     * @param requestBody
     * @returns BaseVoSaTokenInfo OK
     * @throws ApiError
     */
    public static login(
        requestBody: LoginUserDto,
    ): CancelablePromise<BaseVoSaTokenInfo> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/user/login',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 分页查询标签
     * @param requestBody
     * @returns BaseVoPageVoTagEntity OK
     * @throws ApiError
     */
    public static findTagByPage(
        requestBody: TagPageDto,
    ): CancelablePromise<BaseVoPageVoTagEntity> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/tag/page',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 分页查询日志
     * @param requestBody
     * @returns BaseVoPageVoLogEntity OK
     * @throws ApiError
     */
    public static findByLogPage(
        requestBody: LogPageDto,
    ): CancelablePromise<BaseVoPageVoLogEntity> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/log/page',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 分页查询图片
     * @param requestBody
     * @returns BaseVoPageVoImageEntity OK
     * @throws ApiError
     */
    public static findByImagePage(
        requestBody: ImagePageDto,
    ): CancelablePromise<BaseVoPageVoImageEntity> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/image/page',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 保存|上传文件
     * @param requestBody
     * @returns BaseVoFileEntity OK
     * @throws ApiError
     */
    public static saveFile(
        requestBody?: CreateFileDto,
    ): CancelablePromise<BaseVoFileEntity> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/file',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 分页查询页面
     * @param requestBody
     * @returns BaseVoPageVoClientPageEntity OK
     * @throws ApiError
     */
    public static findByClientPagePage(
        requestBody: ClientPagePageDto,
    ): CancelablePromise<BaseVoPageVoClientPageEntity> {
        return __request(OpenAPI, {
            method: 'POST',
            url: '/client-page/page',
            body: requestBody,
            mediaType: 'application/json',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 根据id查询用户
     * @param id
     * @returns BaseVoUserEntity OK
     * @throws ApiError
     */
    public static findUserById(
        id: string,
    ): CancelablePromise<BaseVoUserEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/user/{id}',
            path: {
                'id': id,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 获取用户信息
     * @returns BaseVoUserEntity OK
     * @throws ApiError
     */
    public static getUserInfo(): CancelablePromise<BaseVoUserEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/user/user-info',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 获取验证码
     * @returns BaseVoVerificationVo OK
     * @throws ApiError
     */
    public static getVerificationCode(): CancelablePromise<BaseVoVerificationVo> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/user/get-verification-code',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 查询标签
     * @param id
     * @returns BaseVoTagEntity OK
     * @throws ApiError
     */
    public static findTagById(
        id: string,
    ): CancelablePromise<BaseVoTagEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/tag/{id}',
            path: {
                'id': id,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 根据ID查询角色
     * @param id
     * @returns BaseVoRoleEntity OK
     * @throws ApiError
     */
    public static findRoleById(
        id: string,
    ): CancelablePromise<BaseVoRoleEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/role/{id}',
            path: {
                'id': id,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 查询所有权限
     * @returns BaseVoListPermissionEntity OK
     * @throws ApiError
     */
    public static findAllPermission(): CancelablePromise<BaseVoListPermissionEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/permission',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 查询所有菜单
     * @returns BaseVoListMenuEntity OK
     * @throws ApiError
     */
    public static findAllMenu(): CancelablePromise<BaseVoListMenuEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/menu',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 查询管理员菜单组
     * @returns BaseVoListMenuEntity OK
     * @throws ApiError
     */
    public static findAdminMenuGroup(): CancelablePromise<BaseVoListMenuEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/menu/find-admin-menu-group',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 查询图片
     * @param id
     * @returns BaseVoImageEntity OK
     * @throws ApiError
     */
    public static findImageById(
        id: string,
    ): CancelablePromise<BaseVoImageEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/image/{id}',
            path: {
                'id': id,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 下载图片
     * @param id
     * @returns binary OK
     * @throws ApiError
     */
    public static downloadImage(
        id: string,
    ): CancelablePromise<Blob> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/image/download/{id}',
            path: {
                'id': id,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 根据id查询文件
     * @param id
     * @returns BaseVoFileEntity OK
     * @throws ApiError
     */
    public static findFileById(
        id: string,
    ): CancelablePromise<BaseVoFileEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/file/{id}',
            path: {
                'id': id,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 下载文件
     * @param id
     * @returns binary OK
     * @throws ApiError
     */
    public static downloadFile(
        id: string,
    ): CancelablePromise<Blob> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/file/download/{id}',
            path: {
                'id': id,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 根据ID查询部门
     * @param id
     * @returns BaseVoDepartmentEntity OK
     * @throws ApiError
     */
    public static findDepartmentById(
        id: string,
    ): CancelablePromise<BaseVoDepartmentEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/department/{id}',
            path: {
                'id': id,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 获取我的部门
     * @returns BaseVoDepartmentEntity OK
     * @throws ApiError
     */
    public static getMyDepartment(): CancelablePromise<BaseVoDepartmentEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/department/get-my-department',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 查询默认部门
     * @returns BaseVoDepartmentEntity OK
     * @throws ApiError
     */
    public static findDefaultDepartment(): CancelablePromise<BaseVoDepartmentEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/department/find-by-id-is-default',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 获取部门Logo
     * @returns binary OK
     * @throws ApiError
     */
    public static downloadImage1(): CancelablePromise<Blob> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/department/download-logo',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 根据id查询页面
     * @param id
     * @returns BaseVoClientPageEntity OK
     * @throws ApiError
     */
    public static findByIdClientPage(
        id: string,
    ): CancelablePromise<BaseVoClientPageEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/client-page/{id}',
            path: {
                'id': id,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 查询我的页面
     * @returns BaseVoListClientPageEntity OK
     * @throws ApiError
     */
    public static getMyClientPages(): CancelablePromise<BaseVoListClientPageEntity> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/client-page/get-my-client-pages',
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 查询我的页面图片
     * @param imageId
     * @returns binary OK
     * @throws ApiError
     */
    public static getMyClientPageImage(
        imageId: string,
    ): CancelablePromise<Blob> {
        return __request(OpenAPI, {
            method: 'GET',
            url: '/client-page/get-my-client-page-image/{imageId}',
            path: {
                'imageId': imageId,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 删除用户并强制退出登录
     * @param ids
     * @returns BaseVoInteger<any> OK
     * @throws ApiError
     */
    public static deleteUser(
        ids: string,
    ): CancelablePromise<BaseVoInteger> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/user/{ids}',
            path: {
                'ids': ids,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 删除标签
     * @param ids
     * @returns BaseVoInteger<any> OK
     * @throws ApiError
     */
    public static deleteTag(
        ids: string,
    ): CancelablePromise<BaseVoInteger> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/tag/{ids}',
            path: {
                'ids': ids,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 删除角色
     * @param ids
     * @returns BaseVoInteger<any> OK
     * @throws ApiError
     */
    public static deleteRole(
        ids: string,
    ): CancelablePromise<BaseVoInteger> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/role/{ids}',
            path: {
                'ids': ids,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 删除权限
     * @param ids
     * @returns BaseVoInteger<any> OK
     * @throws ApiError
     */
    public static deletePermission(
        ids: string,
    ): CancelablePromise<BaseVoInteger> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/permission/{ids}',
            path: {
                'ids': ids,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 删除菜单
     * @param ids
     * @returns BaseVoInteger<any> OK
     * @throws ApiError
     */
    public static deleteMenu(
        ids: string,
    ): CancelablePromise<BaseVoInteger> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/menu/{ids}',
            path: {
                'ids': ids,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 删除日志
     * @param ids
     * @returns BaseVoInteger<any> OK
     * @throws ApiError
     */
    public static deleteLog(
        ids: string,
    ): CancelablePromise<BaseVoInteger> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/log/{ids}',
            path: {
                'ids': ids,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 删除图片
     * @param ids
     * @returns BaseVoInteger<any> OK
     * @throws ApiError
     */
    public static deleteImage(
        ids: string,
    ): CancelablePromise<BaseVoInteger> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/image/{ids}',
            path: {
                'ids': ids,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 删除部门
     * @param ids
     * @returns BaseVoInteger<any> OK
     * @throws ApiError
     */
    public static deleteDepartment(
        ids: string,
    ): CancelablePromise<BaseVoInteger> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/department/{ids}',
            path: {
                'ids': ids,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
    /**
     * 删除页面
     * @param ids
     * @returns BaseVoInteger<any> OK
     * @throws ApiError
     */
    public static deleteClientPage(
        ids: string,
    ): CancelablePromise<BaseVoInteger> {
        return __request(OpenAPI, {
            method: 'DELETE',
            url: '/client-page/{ids}',
            path: {
                'ids': ids,
            },
            errors: {
                400: `参数校验异常`,
                500: `业务异常`,
            },
        });
    }
}
