package com.example.bigevent.controller;

import com.example.bigevent.pojo.Result;
import com.example.bigevent.utils.JwtUtil;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

@RestController
@RequestMapping("/article")
public class ArticleController {

    @GetMapping("/list")
//    @RequestHeader(name="Authorization") String token, HttpServletResponse response
    public Result<String> list(){
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
}
