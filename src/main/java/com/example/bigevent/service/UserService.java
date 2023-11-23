package com.example.bigevent.service;

import com.example.bigevent.pojo.User;

public interface UserService {
    // 根据用户名查询用户
    User findByUserName(String username);

    // 注册用户
    void register(String username, String password);
}
