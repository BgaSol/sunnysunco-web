package com.sunnysunco.cloud.codegeneration;

import com.sunnysunco.cloud.business.base.BaseTreeEntity;
import com.sunnysunco.cloud.business.base.exception.BaseException;
import com.sunnysunco.cloud.codegeneration.table.TableEntity;
import com.sunnysunco.cloud.codegeneration.table.TableMapper;
import com.sunnysunco.cloud.codegeneration.table.TableService;
import com.sunnysunco.cloud.codegeneration.tablecolum.TableColumEntity;
import com.sunnysunco.cloud.codegeneration.tablecolum.TableColumService;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.core.type.filter.AnnotationTypeFilter;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;
import java.util.Set;

@Component
@Slf4j
@RequiredArgsConstructor
public class EntityScanner implements ApplicationRunner {
    private final TableService tableService;

    private final TableColumService tableColumService;

    private final TableMapper tableMapper;

    @Override
    public void run(ApplicationArguments args) {
        scanEntities();
    }

    public void scanEntities() {
        // 清空表结构记录
        tableColumService.truncateTable();
        tableService.truncateTable();
        // 扫描所有entity类
        String basePackage = "com.sunnysunco";
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new AnnotationTypeFilter(Entity.class));
        Set<BeanDefinition> candidateComponents = provider.findCandidateComponents(basePackage);
        for (BeanDefinition candidateComponent : candidateComponents) {
            getTableEntity(candidateComponent);
        }
    }

    private void getTableEntity(BeanDefinition beanDefinition) {
        Class<?> entityClass;
        try {
            entityClass = Class.forName(beanDefinition.getBeanClassName());
        } catch (ClassNotFoundException e) {
            log.error("未找到类", e);
            throw new BaseException("未找到类");
        }
        boolean isEntity = entityClass.isAnnotationPresent(Entity.class)
                && entityClass.isAnnotationPresent(Table.class)
                && entityClass.isAnnotationPresent(Schema.class);
        if (isEntity) {
            // 获取类的详细信息
            TableEntity tableEntity = getTableEntityByClass(entityClass);
            if (tableMapper.selectById(tableEntity.getId()) == null) {
                tableService.save(tableEntity);
            }
            log.info("正在扫描Entity: {}", tableEntity.getEntityName());
            // 获取所有字段
            List<Field> allFieldsList = FieldUtils.getAllFieldsList(entityClass);
            for (Field field : allFieldsList) {
                boolean fieldIsTableColum = field.isAnnotationPresent(Column.class)
                        || field.isAnnotationPresent(ManyToMany.class)
                        || field.isAnnotationPresent(ManyToOne.class)
                        || field.isAnnotationPresent(OneToMany.class)
                        || field.isAnnotationPresent(OneToOne.class);
                if (fieldIsTableColum) {
                    TableColumEntity tableColumEntity = getTableColumEntityByClass(field, entityClass, tableEntity);
                    tableColumService.save(tableColumEntity);
                }
            }
            TableColumEntity tableColumEntity = new TableColumEntity();
            tableColumEntity.setInTableId(tableEntity.getId());
        }
    }

    private TableColumEntity getTableColumEntityByClass(Field field, Class<?> entityClass, TableEntity tableEntity) {

        TableColumEntity tableColumEntity = new TableColumEntity();

        tableColumEntity.setInTableId(tableEntity.getId());
        tableColumEntity.setEntityColumName(field.getName());

        if (field.isAnnotationPresent(Column.class)) {
            String name = field.getAnnotation(Column.class).name();
            tableColumEntity.setTableColumName(name);
        }

        Boolean isToMany = null;
        if (field.isAnnotationPresent(ManyToMany.class)) {
            tableColumEntity.setAssociationMethod("ManyToMany");
            isToMany = true;
        } else if (field.isAnnotationPresent(OneToMany.class)) {
            tableColumEntity.setAssociationMethod("OneToMany");
            isToMany = true;
        } else if (field.isAnnotationPresent(ManyToOne.class)) {
            tableColumEntity.setAssociationMethod("ManyToOne");
            isToMany = false;
        } else if (field.isAnnotationPresent(OneToOne.class)) {
            tableColumEntity.setAssociationMethod("OneToOne");
            isToMany = false;
        }
        if (ObjectUtils.isNotEmpty(isToMany)) {
            Class<?> associatedClass;
            if (isToMany) {
                tableColumEntity.setIsMaster(field.isAnnotationPresent(JoinTable.class));

                // 获取关联表类型
                ParameterizedType parameterizedType = (ParameterizedType) field.getGenericType();
                Type[] typeArguments = parameterizedType.getActualTypeArguments();
                Type typeArgument = typeArguments[0];
                try {
                    associatedClass = (Class<?>) typeArgument;
                } catch (RuntimeException e) {
                    associatedClass = entityClass;
                }
            } else {
                tableColumEntity.setIsMaster(field.isAnnotationPresent(JoinColumn.class));
                // 获取关联表类型
                Class<?> type = field.getType();
                if (type.isAssignableFrom(BaseTreeEntity.class)) {
                    associatedClass = entityClass;
                } else {
                    associatedClass = type;
                }
            }
            // 设置关联表类型
            TableEntity associatedTableEntity = getTableEntityByClass(associatedClass);
            if (tableMapper.selectById(associatedTableEntity.getId()) == null) {
                tableService.save(associatedTableEntity);
            }
            tableColumEntity.setAssociatedTableId(associatedTableEntity.getId());
        }
        tableColumEntity.setId(tableEntity.getId() + " " + tableColumEntity.getEntityColumName());
        return tableColumEntity;
    }

    private static TableEntity getTableEntityByClass(Class<?> entityClass) {
        String packageAndName = entityClass.getName();
        String name = packageAndName.substring(packageAndName.lastIndexOf(".") + 1);
        String tableName = entityClass.getAnnotation(Table.class).name();
        String schemaDescription = entityClass.getAnnotation(Schema.class).description();
        String packageName = entityClass.getPackage().getName();
        TableEntity tableEntity = new TableEntity();
        tableEntity.setTableName(tableName);
        tableEntity.setEntityName(name);
        tableEntity.setPackageName(packageName);
        tableEntity.setDescription(schemaDescription);
        tableEntity.setId(packageAndName);
        return tableEntity;
    }
}
