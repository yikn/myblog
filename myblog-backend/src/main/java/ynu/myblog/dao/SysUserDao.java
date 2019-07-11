package ynu.myblog.dao;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import ynu.myblog.entity.SysUser;

@Mapper
public interface SysUserDao {

    @Select("select user_id from sys_user where username=#{username} and password=#{password}")
    int findUserByPaswd(@Param("username") String username,@Param("password")String password);

    @Select("select user_id from sys_user where username=#{username}")
    int findUserId(@Param("username") String username);

    @Select("select * from sys_user where user_id=#{user_id}")
    SysUser getUser(@Param("user_id")int user_id);
}
