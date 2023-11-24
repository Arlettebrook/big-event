package com.example.bigevent.service.impl;

import com.example.bigevent.mapper.CategoryMapper;
import com.example.bigevent.pojo.Category;
import com.example.bigevent.service.CategoryService;
import com.example.bigevent.utils.ThreadLocalUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Map;

@Service
public class CategoryServiceImpl implements CategoryService {

    @Autowired
    CategoryMapper categoryMapper;

    @Override
    public void add(Category category) {
        Map<String, Object> map = ThreadLocalUtil.get();
        category.setCreateUser((Integer) map.get("id"));
        category.setCreateTime(LocalDateTime.now());
        category.setUpdateTime(LocalDateTime.now());
        categoryMapper.add(category);
    }
}
