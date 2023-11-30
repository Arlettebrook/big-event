package com.example.bigevent.controller;

import com.example.bigevent.pojo.Category;
import com.example.bigevent.pojo.Result;
import com.example.bigevent.service.CategoryService;
import com.example.bigevent.utils.ThreadLocalUtil;
import jakarta.validation.constraints.NotEmpty;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/category")
@Validated
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    //    添加文章分类
    @PostMapping
    public Result add(@RequestBody @Validated(Category.Add.class) Category category) {
        categoryService.add(category);
        return Result.success();
    }

    //查询当前用户的所有文章分类列表
    @GetMapping
    public Result<List<Category>> list() {
        List<Category> categories = categoryService.list();
        return Result.success(categories);
    }


    //获取文章分类详情：根据id查询
    @GetMapping("/detail")
    public Result<Category> detail(Integer id) {
        Category category = categoryService.findById(id);
        return Result.success(category);
    }

    //修改文章分类详情
    @PutMapping
    public Result update(@RequestBody @Validated(Category.Update.class) Category category){
        categoryService.update(category);
        return Result.success();
    }

    //删除文章分类：根据id进行删除
    @DeleteMapping
    public Result delete(@NotEmpty Integer id){
        //        判断是否有权利就删除
        Map<String,Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
//        根据id查询文章分类
        Category category = categoryService.findById(id);
        if (category == null) {
            return Result.error("文章不存在！");
        }
        if(userId.equals(category.getCreateUser())){
            categoryService.delete(id);
            return Result.success();
        }else{
            return Result.error("没有能力删除！");
        }

    }

}
