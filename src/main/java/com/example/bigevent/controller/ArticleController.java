package com.example.bigevent.controller;

import com.example.bigevent.pojo.Article;
import com.example.bigevent.pojo.PageBean;
import com.example.bigevent.pojo.Result;
import com.example.bigevent.service.ArticleService;
import com.example.bigevent.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/article")
public class ArticleController {

    @Autowired
    ArticleService articleService;

    @GetMapping("/list")
//    @RequestHeader(name="Authorization") String token, HttpServletResponse response
    public Result<String> list() {
//        //验证token 用拦截器去验证token
//        try {
//            Map<String, Object> claims = JwtUtil.parseToken(token);
//            return Result.success("所有的文章数据。。。");
//        } catch (Exception e) {
////            http响应状态码为401
//            response.setStatus(401);
//            return Result.error("用户未登录！");
//        }

        return Result.success("所有的文章数据。。。");

    }

    @PostMapping
    // 新增文章
    public Result add(@RequestBody @Validated Article article) {
        articleService.add(article);
        return Result.success();
    }

    // 获取文章列表
    @GetMapping
    public Result<PageBean<Article>> list(
            Integer pageNum,
            Integer pageSize,
            @RequestParam(required = false) Integer categoryId,
            @RequestParam(required = false) String state
    ) {
        PageBean<Article> pageBean = articleService.list(pageNum, pageSize, categoryId, state);
        return Result.success(pageBean);
    }
}
