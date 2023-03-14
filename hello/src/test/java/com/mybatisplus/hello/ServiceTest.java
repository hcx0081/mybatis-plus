package com.mybatisplus.hello;

import com.mybatisplus.entity.User;
import com.mybatisplus.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.ArrayList;

/**
 * {@code @Description:}
 */
@SpringBootTest
public class ServiceTest {
    
    @Autowired
    private UserService userService;
    
    @Test
    void countTest() {
        long count = userService.count();// 查询总记录数
        System.out.println(count);
    }
    
    /**
     * 测试添加或修改
     */
    @Test
    void saveOrUpdateBatchTest() {
        ArrayList<User> Users = new ArrayList<>();
        
        // 设置id：修改
        User user1 = new User();
        user1.setId(6L);
        user1.setName("李四");
        Users.add(user1);
        
        // 没有设置id：添加
        for (int i = 1; i <= 10; i++) {
            User user2 = new User();
            user2.setName("王五" + i);
            Users.add(user2);
        }
        
        boolean b = userService.saveOrUpdateBatch(Users);
        System.out.println(b);
    }
}