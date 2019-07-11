package ynu.myblog.controller;


import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.jboss.logging.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigurationPackage;
import org.springframework.web.bind.annotation.*;
import ynu.myblog.entity.Article;
import ynu.myblog.entity.ArticleVo;
import ynu.myblog.service.ArticleService;

import java.util.Map;

@RestController
public class ArticleController {

    @Autowired
    private ArticleService articleService;


    @GetMapping("/articles")
    public JSONObject listArticle(@RequestParam Map<String,Object> params){
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("page",articleService.listArticle(params));
        jsonObject.put("total",articleService.countPages());
        return jsonObject;
    }

    @GetMapping("/article/{id}")
    public JSONObject listArticleById(@RequestParam Map<String,Object> params,@PathVariable("id")int id){
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("article",articleService.listArticleById(id));
        jsonObject.put("code",200);

        return jsonObject;
    }

    @GetMapping("/delarticle/{id}")
    public JSONObject deletearticle(@PathVariable("id")int id){
        JSONObject jsonObject =new JSONObject();
        if(articleService.deletearticleByid(id)){
            jsonObject.put("code",200);
        }
        else
            jsonObject.put("code",404);

        return jsonObject;
    }

    @GetMapping("/find/{id}")
    public JSONObject test(@PathVariable("id") int id){
        JSONObject jsonObject =new JSONObject();

        jsonObject.put("code",200);
        jsonObject.put("data",articleService.test(id));
        return jsonObject;
    }

    @PostMapping("/addarticle")
    public JSONObject addArticle(@RequestBody ArticleVo articleVo){
        JSONObject jsonObject=new JSONObject();
        if(articleService.addarticle(articleVo)){
            jsonObject.put("code",200);
        }
        else
            jsonObject.put("code",404);
        return jsonObject;
    }

    @PostMapping("/updatearticle/{id}")
    public JSONObject updateArticle(@PathVariable("id")int id, @RequestBody ArticleVo articleVo){
        JSONObject jsonObject=new JSONObject();
        if(articleService.updatearticle(id,articleVo)){
            jsonObject.put("code",200);
        }
        else
            jsonObject.put("code",404);
        return jsonObject;
    }

}
