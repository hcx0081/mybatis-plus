package com.mybatisplus.plugins;

import com.mybatisplus.entity.Product;
import com.mybatisplus.mapper.ProductMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * {@code @Description:}
 */
@SpringBootTest
public class LockTest {
    @Autowired
    private ProductMapper productMapper;
    
    @Test
    void lockTest() {
        /* 一起查询 */
        // 小李查询商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询的商品价格：" + productLi.getPrice());
        // 小王查询商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询的商品价格：" + productWang.getPrice());
        
        /* 依次修改 */
        // 小李将商品价格+50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);
        // 小王将商品价格-30
        productWang.setPrice(productWang.getPrice() - 30);
        int i = productMapper.updateById(productWang);
        if (i == 0) {
            // 操作失败，重试
            Product productNew = productMapper.selectById(1);
            productNew.setPrice(productNew.getPrice() - 30);
            productMapper.updateById(productNew);
        }
        
        // 老板查询商品价格
        Product productBoss = productMapper.selectById(1);
        System.out.println("老板查询的商品价格：" + productBoss.getPrice());
    }
}