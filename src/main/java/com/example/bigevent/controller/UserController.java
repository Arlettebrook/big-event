package com.example.bigevent.controller;

import com.example.bigevent.pojo.Result;
import com.example.bigevent.pojo.User;
import com.example.bigevent.service.UserService;
import com.example.bigevent.utils.JwtUtil;
import com.example.bigevent.utils.Md5Util;
import com.example.bigevent.utils.ThreadLocalUtil;
import jakarta.validation.constraints.Pattern;
import org.hibernate.validator.constraints.URL;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/user")
@Validated
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public Result register(@Pattern(regexp = "^\\S{5,16}$") String username,
                           @Pattern(regexp = "^\\S{5,16}$") String password) {
        // 查询用户
        User u = userService.findByUserName(username);
        if (u == null) {
            // 没有占用注册
            userService.register(username, password);
            return Result.success();
        } else {
            //用户名被占用
            return Result.error("用户名已被占用！");
        }
    }

    @PostMapping("/login")
    public Result login(@Pattern(regexp = "^\\S{5,16}$") String username,
                        @Pattern(regexp = "^\\S{5,16}$") String password) {
        //根据用户名查询用户
        User loginUser = userService.findByUserName(username);
        //判断用户是否存在
        if (loginUser == null) {
            return Result.error("用户不存在！");
        }

        // 判断密码是否正确
        if (Md5Util.getMD5String(password).equals(loginUser.getPassword())) {
            //登录成功，生成token
            Map<String, Object> claims = new HashMap<>();
            claims.put("id", loginUser.getId());
            claims.put("username", loginUser.getUsername());
            String token = JwtUtil.genToken(claims);
            return Result.success(token);
        }

        return Result.error("密码错误！");
    }

    @GetMapping("/userInfo")
    public Result<User> userInfo(/*@RequestHeader("Authorization") String token*/) {
//        根据token获取用户名
//        Map<String, Object> map = JwtUtil.parseToken(token);
//        String username = (String) map.get("username");

//        使用ThreadLocal获取业务数据
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
//        根据用户名查询用户，获取用户信息
        User user = userService.findByUserName(username);
        return Result.success(user);
    }

    @PutMapping("update")
    public Result update(@RequestBody @Validated User user) {
//        判断修改的信息是否与token一直：防止修改别人的
        Map<String, Object> map = ThreadLocalUtil.get();
        Integer id = (Integer) map.get("id");
        if (id.equals(user.getId())) {
            userService.update(user);
            return Result.success();
        } else {
            return Result.error("修改失败！");
        }

    }

    @PatchMapping("updateAvatar")
    public Result updateAvatar(@RequestParam @URL String avatarUrl) {
        userService.updateAvatar(avatarUrl);
        return Result.success();
    }

    @PatchMapping("/updatePwd")
    public Result updatePwd(@RequestBody Map<String, String> params) {
//        获取密码参数
        String oldPwd = params.get("old_pwd");
        String newPwd = params.get("new_pwd");
        String rePwd = params.get("re_pwd");

//        参数校验
        if (!StringUtils.hasLength(oldPwd) || !StringUtils.hasLength(newPwd) || !StringUtils.hasLength(rePwd)) {
            return Result.error("缺少必要的参数！");
        }

//        从token中获取username，以及id
        Map<String, Object> map = ThreadLocalUtil.get();
        String username = (String) map.get("username");
        Integer id = (Integer) map.get("id");

//        判断旧密码是否正确
//       获取当前用户密码
        User user = userService.findByUserName(username);
        if (!user.getPassword().equals(Md5Util.getMD5String(oldPwd))) {
            return Result.error("原密码错误！");
        }

//        判断新密码是否一直
        if (!newPwd.equals(rePwd)) {
            return Result.error("两次填写的新密码不一样！");
        }
        // 以上都满足去修改密码
        userService.updatePwd(newPwd, id);
        return Result.success();
    }
}
