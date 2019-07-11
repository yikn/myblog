package ynu.myblog.dao;

import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.GetMapping;
import ynu.myblog.entity.Article;
import ynu.myblog.entity.ArticleVo;

import java.util.List;
import java.util.Map;

@Mapper
public interface ArticleDao {

    @Select("select * from article limit #{start},#{end}")
    List<Article> listArticle(@Param("start") int start ,@Param("end") int end);

    @Select("select * from article where id=#{id}")
    Article listArticleById(@Param("id") int id );

    @Select("select * from article where category_id=#{category_id}")
    List<Article> listArticleByCategoryId(@Param("category_id") int categoryid );

    @Delete("delete from article where id=#{article_id}")
    boolean deletearticleByid(@Param("article_id") int article_id);

    @Results({
            @Result(id=true,column="id",property="id"),//这句不加id全为零
            @Result(property = "category_id", column = "pid",
                    one = @One(select = "ynu.myblog.dao.CategoryDao.listPidbyId"))
    })
    @Select("select * from article where id=#{id}")
    List<Article> listArticleWithPid(@Param("id") int id);

    @Insert("insert into article(title,description,category_id,content,content_format) values (" +
            "#{title},#{description},#{category_id},#{content},#{content_format})")
    boolean addArticle(ArticleVo articleVo);

    @Update("update article set title=#{articleVo.title},description=#{articleVo.description},category_id=#{articleVo.category_id},content=#{articleVo.content},content_format=#{articleVo.content_format} where id =#{id}")
    boolean updateArticle(int id,ArticleVo articleVo);

    @Select("select count(*) from article")
    int countPages();

}
