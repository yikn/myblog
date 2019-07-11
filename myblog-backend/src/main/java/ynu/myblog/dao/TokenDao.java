package ynu.myblog.dao;


import org.apache.ibatis.annotations.*;
import ynu.myblog.entity.Token;

@Mapper
public interface TokenDao {

    @Insert("insert into token_user(userid,token) values(#{userid},#{token})")
    boolean createToken(@Param("userid")int userid ,@Param("token") String token);

    @Select("select * from token_user where token=#{token}")
    int getIdByToken(@Param("token")String token);

    @Select("select userid from token_user where userid=#{userid}")
    Token getTokenById(@Param("userid") int id);

    @Update("update token_user set token=#{token} where userid=#{userid}")
    boolean updateToken(@Param("userid") int userid,@Param("token") String token);

    @Delete("delete from token_user where userid=#{userid}")
    boolean deleteToken(@Param("userid") int userid);
}
