package com.mybatisplus.entity;

import lombok.Data;

@Data
public class User {
    /**
     * 主键ID
     */
    // @TableId("id")
    private Long id;
    
    /**
     * 姓名
     */
    private String name;
    
    /**
     * 年龄
     */
    private Integer age;
    
    /**
     * 邮箱
     */
    private String email;
}