package ynu.myblog.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ynu.myblog.dao.ArticleDao;
import ynu.myblog.entity.Article;
import ynu.myblog.entity.ArticleVo;

import java.util.List;
import java.util.Map;

@Service
public class ArticleService implements  ynu.myblog.service.impl.ArticleImpl {

    @Autowired
    private ArticleDao articleDao;

    @Override
    public List<Article> listArticle(Map<String, Object> params){
        System.out.println(params);
        int current = Integer.parseInt((String) params.get("current"));
        int size = Integer.parseInt((String) params.get("size"));
        if (params.get("categoryId")!=null){
            return articleDao.listArticleByCategoryId(18);
        }
        return articleDao.listArticle((current-1)*size,(current-1)*size+size);
    }

    @Override
    public Article listArticleById(int id){
        return articleDao.listArticleById(id);
    }

    @Override
    public boolean deletearticleByid(int id){
        return articleDao.deletearticleByid(id);
    }

    @Override
    public List<Article> test(int id){
        return articleDao.listArticleWithPid(id);
    }

    @Override
    public boolean addarticle(ArticleVo articleVo){
        return articleDao.addArticle(articleVo);
    }

    @Override
    public boolean updatearticle(int id, ArticleVo articleVo){
        return articleDao.updateArticle(id,articleVo);
    }

    @Override
    public int countPages(){return articleDao.countPages();};
}
