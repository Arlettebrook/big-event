package com.example.bigevent.service.impl;

import com.example.bigevent.mapper.ArticleMapper;
import com.example.bigevent.pojo.Article;
import com.example.bigevent.pojo.PageBean;
import com.example.bigevent.pojo.Result;
import com.example.bigevent.service.ArticleService;
import com.example.bigevent.utils.ThreadLocalUtil;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Map;

@Slf4j
@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    ArticleMapper articleMapper;

    @Override
    public void add(Article article) {
        //补充属性值
        article.setCreateTime(LocalDateTime.now());
        article.setUpdateTime(LocalDateTime.now());

        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        article.setCreateUser(userId);
        articleMapper.add(article);

    }

    @Override
    public PageBean<Article> list(Integer pageNum, Integer pageSize, Integer categoryId, String state) {
        // 1.创建pageBean对象
        PageBean<Article> pageBean = new PageBean<>();
        // 2.开启分页查询
        PageHelper.startPage(pageNum, pageSize);

        // 3.调用mapper
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer userId = (Integer) map.get("id");
        List<Article> articles = articleMapper.list(userId, categoryId, state);
//        log.info("articles:"+articles.toString());

        // Page中提供了方法，可以获取到PageHelper分页查询后，得到的总记录数和当前页数据
        Page<Article> page = (Page<Article>) articles;
        // Page是List的实现类，向下强转使用Page封装的方法

        // 把数据填充到PageBean对象中
        pageBean.setTotal(page.getTotal());
        pageBean.setItems(page.getResult());
        return pageBean;
    }

    @Override
    public Result detail(Integer id) {
        Article article = articleMapper.getArticleById(id);
        if (article == null) {
            return Result.error("文章不存在");
        }
        return Result.success(article);
    }
}
