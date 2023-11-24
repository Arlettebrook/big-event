package com.example.bigevent.mapper;

import com.example.bigevent.pojo.Category;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CategoryMapper {

    @Insert("insert category(category_name, category_alias, create_user, create_time, update_time)" +
            "values(#{categoryName},#{categoryAlias},#{createUser},#{createTime},#{updateTime})")
    void add(Category category);

    @Select("select * from category where create_user=#{userId}")
    List<Category> list(Integer userId);
}
