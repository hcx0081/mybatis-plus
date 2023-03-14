package com.mybatisplus.multi_ds;

import com.mybatisplus.entity.Product;
import com.mybatisplus.entity.User;
import com.mybatisplus.service.ProductService;
import com.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class MultiDsApplicationTests {
    @Autowired
    private UserService userService;
    
    @Autowired
    private ProductService productService;
    
    @Test
    void contextLoads() {
        // 从mybatis_plus数据库中查询user表
        User user = userService.getById(1);
        System.out.println(user);
        
        // 从mybatis_plus_dup数据库中查询product表
        Product product = productService.getById(1);
        System.out.println(product);
    }
}