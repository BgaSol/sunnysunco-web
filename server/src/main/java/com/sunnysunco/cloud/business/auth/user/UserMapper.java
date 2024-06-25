package com.sunnysunco.cloud.business.auth.user;

import com.sunnysunco.cloud.business.base.BaseSCMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface UserMapper extends BaseSCMapper<UserEntity> {

    @Select("SELECT c_base_access_role.base_id " +
            "FROM c_base_access_role " +
            "JOIN c_user_role ON c_base_access_role.role_id = c_user_role.role_id " +
            "WHERE c_user_role.user_id = ${userId}")
    List<String> getAssociatedID(@Param("userId") String userId);
}
