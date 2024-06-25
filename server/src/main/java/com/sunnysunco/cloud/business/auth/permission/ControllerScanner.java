package com.sunnysunco.cloud.business.auth.permission;

import cn.dev33.satoken.annotation.SaCheckPermission;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ArrayUtils;
import org.jetbrains.annotations.NotNull;
import org.springframework.aop.support.AopUtils;
import org.springframework.beans.factory.config.BeanPostProcessor;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.Method;
import java.util.Arrays;

@Component
//@ComponentScan(basePackages = "com.sunnysunco")
@RequiredArgsConstructor
@Slf4j
public class ControllerScanner implements BeanPostProcessor {

    private final PermissionMapper permissionMapper;

    /**
     * 扫描controller类和方法，将权限信息存入数据库
     */
    @Override
    public Object postProcessAfterInitialization(@NotNull Object bean, @NotNull String beanName) {
        Class<?> beanClass;
        if (AopUtils.isAopProxy(bean)) {
            beanClass = AopUtils.getTargetClass(bean);
            // 获取被代理的原bean
        } else {
            beanClass = bean.getClass();
        }
        // 只扫描controller的类
        boolean isRequestController = beanClass.isAnnotationPresent(RestController.class)
                && beanClass.isAnnotationPresent(RequestMapping.class);
        if (isRequestController) {
            // 获取controller的路径
            String annotation = beanClass.getAnnotation(RequestMapping.class).value()[0];
            // 获取controller的名称
            String controllerName = annotation.startsWith("/") ? annotation.substring(1) : annotation;
            log.info("正在扫描Controller: {}", controllerName);
            // 获取controller的描述
            String controllerDescription = controllerName + "Controller";
            if (beanClass.isAnnotationPresent(Tag.class)) {
                Tag tag = beanClass.getAnnotation(Tag.class);
                controllerDescription = tag.name();
            }
            // 创建controller的权限
            PermissionEntity parentPermissionEntity = new PermissionEntity();
            parentPermissionEntity.setId(controllerName);
            parentPermissionEntity.setName(controllerName);
            parentPermissionEntity.setDescription(controllerDescription);
            parentPermissionEntity.setPath(annotation);
            if (permissionMapper.selectById(parentPermissionEntity.getId()) == null) {
                permissionMapper.insert(parentPermissionEntity);
            }
            // 获取controller内部的方法
            Method[] methods = beanClass.getMethods();
            for (Method method : methods) {
                // 判断是否是请求方法 若是请求方法则获取请求路径
                String path = null;
                boolean isRequestMethod = false;
                String type = null;
                if (method.isAnnotationPresent(RequestMapping.class)) {
                    RequestMapping requestMapping = method.getAnnotation(RequestMapping.class);
                    path = ArrayUtils.get(requestMapping.value(), 0);
                    isRequestMethod = true;
                    type = "ALL";
                } else if (method.isAnnotationPresent(GetMapping.class)) {
                    GetMapping getMapping = method.getAnnotation(GetMapping.class);
                    path = ArrayUtils.get(getMapping.value(), 0);
                    isRequestMethod = true;
                    type = "GET";
                } else if (method.isAnnotationPresent(PostMapping.class)) {
                    PostMapping postMapping = method.getAnnotation(PostMapping.class);
                    path = ArrayUtils.get(postMapping.value(), 0);
                    isRequestMethod = true;
                    type = "POST";
                } else if (method.isAnnotationPresent(PutMapping.class)) {
                    PutMapping putMapping = method.getAnnotation(PutMapping.class);
                    path = ArrayUtils.get(putMapping.value(), 0);
                    isRequestMethod = true;
                    type = "PUT";
                } else if (method.isAnnotationPresent(DeleteMapping.class)) {
                    DeleteMapping deleteMapping = method.getAnnotation(DeleteMapping.class);
                    path = ArrayUtils.get(deleteMapping.value(), 0);
                    isRequestMethod = true;
                    type = "DELETE";
                }
                // 判断是否是权限检查方法
                boolean isSaCheckPermission = method.isAnnotationPresent(SaCheckPermission.class);
                if (isRequestMethod && isSaCheckPermission) {
                    // 获取方法的描述
                    String methodDescription = method.getName();
                    if (method.isAnnotationPresent(Operation.class)) {
                        Operation operation = method.getAnnotation(Operation.class);
                        methodDescription = operation.summary();
                    }
                    // 获取方法的权限
                    String[] permissionValues = method.getAnnotation(SaCheckPermission.class).value();
                    for (String permissionValue : permissionValues) {
                        PermissionEntity permissionEntity = new PermissionEntity();
                        permissionEntity.setParentId(parentPermissionEntity.getId());
                        permissionEntity.setId(permissionValue);
                        permissionEntity.setName(method.getName());
                        permissionEntity.setCode(permissionValue);
                        permissionEntity.setDescription(methodDescription);
                        permissionEntity.setPath(path);
                        permissionEntity.setType(type);
                        if (permissionMapper.selectById(permissionEntity.getId()) == null) {
                            permissionMapper.insert(permissionEntity);
                        }
                    }
                }
            }
        }
        return bean;
    }
}