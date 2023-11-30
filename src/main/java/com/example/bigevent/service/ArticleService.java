package com.example.bigevent.service;

import com.example.bigevent.pojo.Article;
import com.example.bigevent.pojo.PageBean;
import com.example.bigevent.pojo.Result;

public interface ArticleService {
    void add(Article article);

    // 添加分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);

    // 根据id获取文章信息
    Result detail(Integer id);

    // 更新文章
    Result update(Article article);

    Result deleteById(Integer id);
}
