package com.example.bigevent.controller;

import com.example.bigevent.pojo.Category;
import com.example.bigevent.pojo.Result;
import com.example.bigevent.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

//    添加文章分类
    @PostMapping
    public Result add(@RequestBody @Validated Category category) {
        categoryService.add(category);
        return Result.success();
    }

    //查询当前用户的所有文章分类列表
    @GetMapping
    public Result<List<Category>> list(){
       List<Category> categories = categoryService.list();
        return Result.success(categories);
    }
}
