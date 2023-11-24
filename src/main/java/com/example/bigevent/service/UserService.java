package com.example.bigevent.service;

import com.example.bigevent.pojo.User;

public interface UserService {
    // 根据用户名查询用户
    User findByUserName(String username);

    // 注册用户
    void register(String username, String password);

//    修改用户信息
    void update(User user);

//    更新头像
    void updateAvatar(String avatarUrl);

    //修改密码
    void updatePwd(String newPwd, Integer id);

    User findUserById(Integer id);
}
