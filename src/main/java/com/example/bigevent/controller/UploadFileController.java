package com.example.bigevent.controller;

import com.example.bigevent.pojo.Result;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/upload")
public class UploadFileController {

    @PostMapping
    public Result<String> uploadAvatar(MultipartFile file) throws IOException {
        //获取源文件名
        String originalFilename = file.getOriginalFilename();
        //将文件保存到本地
        if (originalFilename == null) {
            return Result.error("请上传文件");
        }
        String filename = UUID.randomUUID().toString().replace("-", "") + originalFilename.substring(originalFilename.lastIndexOf("."));
        file.transferTo(new File("F:\\Node\\Springboot3_project\\avatar\\" + filename));
        return Result.success("用户头像url地址");
    }
}
