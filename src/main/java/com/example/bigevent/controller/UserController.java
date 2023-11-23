package com.example.bigevent.controller;

import com.example.bigevent.pojo.Result;
import com.example.bigevent.pojo.User;
import com.example.bigevent.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(String username,String password){
        // 查询用户
        User u = userService.findByUserName(username);
        if(u == null){
            // 没有占用注册
            userService.register(username,password);
            return Result.success();
        }else{
            //用户名被占用
            return Result.error("用户名已被占用！");
        }
    }
}
