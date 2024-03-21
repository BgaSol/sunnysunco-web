package com.sunnysunco.cloud.business.base;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.sunnysunco.cloud.business.base.dto.BasePageDto;
import com.sunnysunco.cloud.business.base.exception.BaseException;
import com.sunnysunco.cloud.business.base.vo.PageVo;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.reflect.FieldUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.JoinTable;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.persistence.criteria.Predicate;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Transactional
@Slf4j
public abstract class BaseService<ENTITY extends BaseEntity, PAGEDTO extends BasePageDto<ENTITY>> {

    abstract public BaseSCMapper<ENTITY> commonBaseMapper();

    abstract public BaseRepository<ENTITY> commonBaseRepository();

    abstract public Class<ENTITY> commonBaseEntityClass();

    /**
     * 保存实体
     * 如果实体有中间表，也会保存中间表
     *
     * @param entity 实体
     * @return 实体
     */
    @Transactional()
    public ENTITY save(ENTITY entity) {
        // 反射获取entity的所有字段
        Class<? extends BaseEntity> entityClass = entity.getClass();
//        Field[] fields = entityClass.getDeclaredFields();
        List<Field> fields = FieldUtils.getAllFieldsList(entityClass);
        // 检索所有的joinColumn字段为空字符串的字段，将其设置为null
        for (Field field : fields) {
            // 判断字段是否有注解Transient并且字段尾部是id结尾并且有TableField注解
            if (field.isAnnotationPresent(Transient.class)
                    && field.getName().toLowerCase().endsWith("id")
                    && field.isAnnotationPresent(TableField.class)) {
                // 获取TableField注解
//                TableField tableField = field.getAnnotation(TableField.class);
                // 获取字段名
                // String tableFieldName = tableField.value();
                // 获取字段的值
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(entity);
                } catch (IllegalAccessException e) {
                    log.error("获取字段值失败", e);
                    throw new BaseException("获取字段值失败");
                }
                if ("".equals(value)) {
                    try {
                        field.set(entity, null);
                    } catch (IllegalAccessException e) {
                        log.error("设置字段值失败", e);
                        throw new BaseException("设置字段值失败");
                    }
                }
            }
        }
        // 插入实体
        commonBaseMapper().insert(entity);
        for (Field field : fields) {
            // 判断字段是否有注解JoinTable
            if (field.isAnnotationPresent(JoinTable.class)) {
                // 获取字段的JoinTable注解
                JoinTable joinTable = field.getAnnotation(JoinTable.class);
                // 获取中间表的表名
                String tableName = joinTable.name();
                // 获取中间表字段
                String masterName = joinTable.joinColumns()[0].name();
                String slaveName = joinTable.inverseJoinColumns()[0].name();
                // 获取中间表字段的值
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(entity);
                } catch (IllegalAccessException e) {
                    log.error("获取字段值失败", e);
                    throw new BaseException("获取字段值失败");
                }
                if (value != null) {
                    List<BaseEntity> childrenEntities = (List<BaseEntity>) value;
                    for (BaseEntity childrenEntity : childrenEntities) {
                        // 获取中间表字段的值
                        String masterValue = entity.getId();
                        String slaveValue = childrenEntity.getId();
                        // 插入中间表
                        commonBaseMapper().insertIntoTable(tableName, masterName, masterValue, slaveName, slaveValue);
                    }
                }
            }
        }
        return this.findById(entity.getId());
    }

    /**
     * 更新实体
     * 如果实体有中间表，也会更新中间表
     *
     * @param entity 实体
     * @return 实体
     */
    @Transactional()
    public ENTITY update(ENTITY entity) {
        ENTITY queryEntity = commonBaseMapper().selectById(entity.getId());
        if (queryEntity == null) {
            throw new BaseException("更新失败，更新数据不存在");
        }
        // 反射获取entity的所有字段
        Class<? extends BaseEntity> entityClass = entity.getClass();
        List<Field> fields = FieldUtils.getAllFieldsList(entityClass);
        UpdateWrapper<ENTITY> updateWrapper = new UpdateWrapper<>();
        updateWrapper.eq("id", entity.getId());
        // 检索所有的joinColumn字段为空字符串的字段，将其设置为null
        // 处理关联表字段 JoinColumn 字段为空字符串的情况
        for (Field field : fields) {
            // 判断字段是否有注解Transient并且字段尾部是id结尾并且有TableField注解
            if (field.isAnnotationPresent(Transient.class)
                    && field.getName().toLowerCase().endsWith("id")
                    && field.isAnnotationPresent(TableField.class)) {
                // 获取TableField注解
                TableField tableField = field.getAnnotation(TableField.class);
                // 获取字段名
                String tableFieldName = tableField.value();
                // 获取字段的值
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(entity);
                } catch (IllegalAccessException e) {
                    log.error("获取字段值失败", e);
                    throw new BaseException("获取字段值失败");
                }
                if ("".equals(value)) {
                    try {
                        field.set(entity, null);
                    } catch (IllegalAccessException e) {
                        log.error("设置字段值失败", e);
                        throw new BaseException("设置字段值失败");
                    }
                    updateWrapper.set(tableFieldName, null);
                }
            }
        }
        // 更新实体
        commonBaseMapper().update(entity, updateWrapper);
        // 反射获取entity的所有字段
        for (Field field : fields) {
            // 判断字段是否有注解
            if (field.isAnnotationPresent(JoinTable.class)) {
                // 获取字段的JoinTable注解
                JoinTable joinTable = field.getAnnotation(JoinTable.class);
                // 获取中间表的表名
                String tableName = joinTable.name();
                // 获取中间表字段
                String masterName = joinTable.joinColumns()[0].name();
                String slaveName = joinTable.inverseJoinColumns()[0].name();
                // 获取中间表字段的值
                field.setAccessible(true);
                Object value;
                try {
                    value = field.get(entity);
                } catch (IllegalAccessException e) {
                    log.error("获取字段值失败", e);
                    throw new BaseException("获取字段值失败");
                }
                if (value != null) {
                    // 删除中间表的数据
                    commonBaseMapper().deleteFromTable(tableName, masterName, entity.getId());
                    List<BaseEntity> childrenEntities = (List<BaseEntity>) value;
                    for (BaseEntity childrenEntity : childrenEntities) {
                        // 获取中间表字段的值
                        String masterValue = entity.getId();
                        String slaveValue = childrenEntity.getId();
                        // 插入中间表
                        commonBaseMapper().insertIntoTable(tableName, masterName, masterValue, slaveName, slaveValue);
                    }
                }
            }
        }
        return this.findById(entity.getId());
    }

    /**
     * 删除实体
     *
     * @param ids 实体id
     * @return 删除数量
     */
    @Transactional()
    public Integer[] delete(String... ids) {
        return Arrays.stream(ids).map(id -> {
            ENTITY entity = commonBaseMapper().selectById(id);
            if (entity == null) {
                throw new BaseException("删除失败，删除数据不存在");
            }
            // 反射获取entity的所有字段
            Class<? extends BaseEntity> entityClass = entity.getClass();
            Field[] fields = entityClass.getDeclaredFields();
            for (Field field : fields) {
                // 判断字段是否有注解
                if (field.isAnnotationPresent(JoinTable.class)) {
                    // 获取字段的JoinTable注解
                    JoinTable joinTable = field.getAnnotation(JoinTable.class);
                    // 获取中间表的表名
                    String tableName = joinTable.name();
                    // 获取中间表字段
                    String masterName = joinTable.joinColumns()[0].name();
                    commonBaseMapper().deleteFromTable(tableName, masterName, entity.getId());
                }
            }
            return commonBaseMapper().deleteById(id);
        }).toArray(Integer[]::new);
    }

    /**
     * 根据id查询实体
     *
     * @param id 实体id
     * @return 实体
     */
    @Transactional(readOnly = true)
    public ENTITY findById(String id) {
        return commonBaseRepository().findById(id).orElseThrow(() -> new BaseException("数据不存在"));
    }

    /**
     * 分页查询
     *
     * @param pageDto 分页查询条件
     * @return 分页数据
     */
    @Transactional(readOnly = true)
    public PageVo<ENTITY> findByPage(PAGEDTO pageDto) {
        // 获取分页条件
        Pageable pageAble = pageDto.getPageAble();
        // 获取查询条件
        Specification<ENTITY> example = pageDto.getExample();

        Page<ENTITY> all;
        if (example == null) {
            all = commonBaseRepository().findAll(pageAble);
        } else {
            all = commonBaseRepository().findAll(example, pageAble);
        }
        return new PageVo<>(all);
    }

    /**
     * 查询所有实体
     * 如果实体是树形结构，查询所有根节点
     *
     * @return 实体列表
     */
    @Transactional(readOnly = true)
    public List<ENTITY> findAll() {
        // 判断ENTITY是否是树形结构
        if (BaseTreeEntity.class.isAssignableFrom(commonBaseEntityClass())) {
            Specification<ENTITY> example = (root, query, criteriaBuilder) -> {
                //用列表装载断言对象
                List<Predicate> predicates = new ArrayList<>();
                // 查询 parent为空的所有项目
                predicates.add(criteriaBuilder.isNull(root.get("parent")));
                Predicate[] p = new Predicate[predicates.size()];
                return criteriaBuilder.and(predicates.toArray(p));
            };
            return commonBaseRepository().findAll(example);
        }
        return commonBaseRepository().findAll();
    }

    /**
     * 清空表内所有数据
     */
    public void truncateTable() {
        Class<ENTITY> entityClass = this.commonBaseEntityClass();
        String tableName = entityClass.getAnnotation(Table.class).name();
        this.commonBaseMapper().truncateTable(tableName);
    }


}
