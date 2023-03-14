package com.mybatisplus.entity;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * {@code @Description:}
 */
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    
    // 乐观锁
    @Version
    private Integer version;
}