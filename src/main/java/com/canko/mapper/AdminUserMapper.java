package com.canko.mapper;

import com.canko.domain.AdminUser;
import org.apache.ibatis.annotations.*;

@Mapper
public interface AdminUserMapper {

    @Select("select * from admin_user where username = #{name}")
    AdminUser getUserByUsername(@Param("name") String username);

    @Insert("insert into admin_user(username,password,create_time) values(#{username},#{password},UNIX_TIMESTAMP())")
    void insertAdminUser(AdminUser user);

    @Update("update admin_user set username = #{username},password = #{password} where id = #{id} ")
    void updateAdminUserById(AdminUser user);

}
