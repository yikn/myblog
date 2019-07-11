package ynu.myblog.service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ynu.myblog.dao.CategoryDao;
import ynu.myblog.entity.Category;
import ynu.myblog.entity.ChildrenCategory;
import ynu.myblog.entity.SimpleCategory;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Service
public class CategoryService implements ynu.myblog.service.impl.CategoryImpl {

    @Autowired
    private CategoryDao categoryDao;

    @Override
    public List<Category> getCategoryList(){
        return categoryDao.listCategory(0);
    }

    @Override
    public List<SimpleCategory> getCascadeCategory(){
        List<Category> listCategory= categoryDao.listCategory(0);

        List<SimpleCategory> simpleCategoryList = new ArrayList<>();
        for( Category i : listCategory){
            SimpleCategory simpleCategory =new SimpleCategory(i.getId(),i.getName(),i.getPid());
            simpleCategoryList.add(simpleCategory);
            for (Category j:i.getCategoryList()){
                simpleCategory.getChildren().add(new ChildrenCategory(j.getId(),j.getName(),j.getPid()));
            }
        }
        return simpleCategoryList;

        /*
        String listjson =JSON.toJSONString(listCategory);
        listjson.replaceAll("/id/g","value");
        System.out.println(listjson);
         */

    }

    @Override
    public List<Category> listParentCategory(){
        return categoryDao.listParentCategory();
    }


    @Override
    public boolean deletecategoryByid(int id){
        return categoryDao.deletecategoryByid(id);
    }


    @Override
    public boolean updatecategory(int id, String name){
        return categoryDao.updatecategory(id,name);
    }

    @Override
    public boolean addcategory(int pid, String name){
        return categoryDao.addchildcategory(pid,name);
    }


}
