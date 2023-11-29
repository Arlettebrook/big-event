package com.example.bigevent.service;

import com.example.bigevent.pojo.Article;
import com.example.bigevent.pojo.PageBean;

public interface ArticleService {
    void add(Article article);

    // 添加分页列表查询
    PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state);
}
