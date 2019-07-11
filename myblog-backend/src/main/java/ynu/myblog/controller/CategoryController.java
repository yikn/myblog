package ynu.myblog.controller;

import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import ynu.myblog.dao.CategoryDao;
import ynu.myblog.entity.Category;
import ynu.myblog.service.CategoryService;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@RestController
public class CategoryController {

    @Autowired
    private  CategoryService categoryService;

    @GetMapping("/category")
    public JSONObject getCategoryList(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("categoryList",categoryService.getCategoryList());
        return jsonObject;
    }

    @GetMapping("/casadecategory")
    public JSONObject getCascadeCategory(){
        JSONObject jsonObject =new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("list",categoryService.getCascadeCategory());
        return jsonObject;
    }

    @GetMapping("/addcategory")
    public JSONObject addArticle(@RequestParam("pid") int pid, @RequestParam("name") String name){
        JSONObject jsonObject=new JSONObject();
        if(categoryService.addcategory(pid,name)){
            jsonObject.put("code",200);
        }
        else
            jsonObject.put("code",404);
        return jsonObject;
    }

    @GetMapping("/deletecategory/{id}")
    public JSONObject deleteArticle(@PathVariable("id") int id){
        JSONObject jsonObject =new JSONObject();
        if(categoryService.deletecategoryByid(id)){
            jsonObject.put("code",200);
        }
        else
            jsonObject.put("code",404);
        return jsonObject;
    }

    @GetMapping("/updatecategory")
    public JSONObject upodateArticle(@RequestParam ("id") int id,@RequestParam("name")String name){
        JSONObject jsonObject =new JSONObject();
        if(categoryService.updatecategory(id,name)){
            jsonObject.put("code",200);
        }
        else
            jsonObject.put("code",404);
        return jsonObject;
    }

    @GetMapping("/getparentcategory")
    public JSONObject listParentById(){
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code",200);
        jsonObject.put("list",categoryService.listParentCategory());
        return jsonObject;
    }

}
