package ynu.myblog.dao;

import org.apache.ibatis.annotations.*;
import ynu.myblog.entity.Category;

import java.util.List;

@Mapper
public interface CategoryDao {

    // 递归查询菜单
    @Select("select * from category where pid=#{pid}")
    @Results({
            @Result(id=true,column="id",property="id"),//这句不加id全为零
            @Result(column = "pid" ,property="pid"),
            @Result(property = "categoryList", column = "id",
                    many = @Many(select = "ynu.myblog.dao.CategoryDao.listCategory"))
    })
    List<Category> listCategory(@Param("pid") int pid);

    @Select("select pid from category where id=#{id} ")
    List<Category> listParentById(@Param("id") int id);

    @Select("select * from category where pid=0")
    List<Category> listParentCategory();

    @Delete("delete from category where id=#{id} or pid=#{id}")
    boolean deletecategoryByid(@Param("id") int id);

    @Update("update category set name=#{name} where id=#{id}")
    boolean updatecategory(@Param("id") int id ,@Param("name") String name);

    @Insert("insert into category(name,pid) values (#{name},#{pid})")
    boolean addchildcategory(@Param("pid") int pid,@Param("name") String name);

}
