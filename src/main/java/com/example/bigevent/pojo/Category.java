package com.example.bigevent.pojo;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.groups.Default;
import lombok.Data;

import java.time.LocalDateTime;

@Data
public class Category {
    @NotNull(groups = Update.class)
    private Integer id;//主键ID
    @NotEmpty//(groups = {Add.class,Update.class})
    private String categoryName;//分类名称
    @NotEmpty//(groups={Add.class,Update.class})
    private String categoryAlias;//分类别名
    private Integer createUser;//创建人ID
    @JsonFormat(pattern = "yyyy-HH-dd HH:mm:ss")
    private LocalDateTime createTime;//创建时间
    @JsonFormat(pattern = "yyyy-HH-dd HH:mm:ss")
    private LocalDateTime updateTime;//更新时间

    /**
     * 分组校验：
     *      如果没有指定分组，那么就是默认分组
     *      可以使用继承默认分组使用，简化都要校验的属性
     */
    public interface Add extends Default {}

    public interface Update extends Default{}
}
