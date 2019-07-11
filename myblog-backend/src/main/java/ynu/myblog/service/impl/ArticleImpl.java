package ynu.myblog.service.impl;

import ynu.myblog.entity.Article;
import ynu.myblog.entity.ArticleVo;

import java.util.List;
import java.util.Map;

public interface ArticleImpl {
    List<Article> listArticle(Map<String, Object> params);

    Article listArticleById(int id);

    boolean deletearticleByid(int id);

    List<Article> test(int id);

    boolean addarticle(ArticleVo articleVo);

    boolean updatearticle(int id, ArticleVo articleVo);

    int countPages();
}
