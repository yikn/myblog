package ynu.myblog.service.impl;

import ynu.myblog.entity.Category;
import ynu.myblog.entity.SimpleCategory;

import java.util.List;

public interface CategoryImpl {
    List<Category> getCategoryList();

    List<SimpleCategory> getCascadeCategory();

    List<Category> listParentCategory();

    boolean deletecategoryByid(int id);

    boolean updatecategory(int id, String name);

    boolean addcategory(int pid, String name);
}
