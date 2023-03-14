package com.mybatisplus.hello;

import com.mybatisplus.entity.User;
import com.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * {@code @Description:}
 */
@SpringBootTest
public class BaseMapperTest {
    
    @Autowired
    private UserMapper userMapper;
    
    @Test
    void insertTest() {
        User user = new User();
        user.setName("张三");
        user.setAge(20);
        user.setEmail("zs@qq.com");
        int insert = userMapper.insert(user);
        System.out.println(insert);
        System.out.println(user.getId());// 1553949203944251393
    }
    
    
    @Test
    void deleteTest() {
        // int i = userMapper.deleteById(6);
        
        // HashMap<String, Object> map = new HashMap<>();
        // map.put("id", "6");
        // int i = userMapper.deleteByMap(map);
        
        int i = userMapper.deleteBatchIds(Arrays.asList(6, 1553948610236350466L));// 批量删除
        System.out.println(i);
    }
    
    
    @Test
    void updateTest() {
        User user = new User();
        user.setId(6L);
        user.setName("李四");
        user.setEmail("li@qq.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }
    
    
    @Test
    void selectTest() {
        User user = userMapper.selectById(6);
        System.out.println(user);
        
        // HashMap<String, Object> map = new HashMap<>();
        // map.put("id", 6);
        // List<User> userList = userMapper.selectByMap(map);
        // userList.forEach(System.out::println);
        
        // List<User> userList = userMapper.selectBatchIds(Arrays.asList(6, 1553948610236350466L));// 批量查询
        // userList.forEach(System.out::println);
        
        List<User> userList = userMapper.selectList(null);// 传入null表示没有指定条件：查询所有数据
        userList.forEach(System.out::println);
    }
    
    
    @Test
    void customTest() {
        Map<String, User> map = userMapper.selectMapById(6L);
        System.out.println(map);
    }
}