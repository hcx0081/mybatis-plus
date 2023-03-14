package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName
public class User {
    // 主键ID
    @TableId("id")
    private Long id;
    
    // 姓名
    @TableLogic
    private String name;
    
    // 年龄
    private Integer age;
    
    // 邮箱
    private String email;
}