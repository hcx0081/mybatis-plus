package com.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.Version;
import lombok.Data;

/**
 * @Description:
 */
@Data
public class Product {
    private Long id;
    private String name;
    private Integer price;
    
    @Version//标识为乐观锁
    private Integer version;
}
