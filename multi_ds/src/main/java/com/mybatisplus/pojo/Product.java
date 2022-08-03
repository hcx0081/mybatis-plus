package com.mybatisplus.pojo;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

/**
 * @TableName product
 */
@TableName(value = "product")
@Data
public class Product {
    /**
     * 主键ID
     */
    @TableId
    private Long id;
    
    /**
     * 商品名称
     */
    private String name;
    
    /**
     * 价格
     */
    private Integer price;
    
    /**
     * 乐观锁版本号
     */
    private Integer version;
}