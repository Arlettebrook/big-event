package com.example.bigevent.service;

import com.example.bigevent.pojo.Category;

import java.util.List;

public interface CategoryService {
    //添加文章分类
    void add(Category category);

    //查询当前用户的所有文章分类列表
    List<Category> list();

    //根据id查询文章分类
    Category findById(Integer id);
}
