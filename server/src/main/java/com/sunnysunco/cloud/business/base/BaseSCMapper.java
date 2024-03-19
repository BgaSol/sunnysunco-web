package com.sunnysunco.cloud.business.base;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface BaseSCMapper<T> extends BaseMapper<T> {
    /**
     * @param tableName   中间表名
     * @param masterName  主表主键名
     * @param masterValue 主表主键值
     * @param slaveName   从表主键名
     * @param slaveValue  从表主键值
     */
    @Insert("insert into ${tableName} (${masterName}, ${slaveName}) values (#{masterValue}, #{slaveValue})")
    void insertIntoTable(@Param("tableName") String tableName,
                         @Param("masterName") String masterName,
                         @Param("masterValue") String masterValue,
                         @Param("slaveName") String slaveName,
                         @Param("slaveValue") String slaveValue);

    /**
     * @param tableName  中间表名
     * @param masterName 主表主键名
     * @param id         主表主键值
     */
    @Delete("delete from ${tableName} where ${masterName} = #{id}")
    void deleteFromTable(@Param("tableName") String tableName,
                         @Param("masterName") String masterName,
                         @Param("id") String id);
}
