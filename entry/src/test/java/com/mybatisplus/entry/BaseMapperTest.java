package com.mybatisplus.entry;

import com.mybatisplus.mapper.UserMapper;
import com.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
@SpringBootTest
public class BaseMapperTest {
    
    @Autowired
    private UserMapper userMapper;
    
    /**
     * 测试BaseMapper新增操作
     */
    @Test
    void testInsert() {
        User user = new User();
        user.setName("张三");
        user.setAge(20);
        user.setEmail("zs@qq.com");
        //int insert(T entity);
        int insert = userMapper.insert(user);//传入的参数为实体类
        System.out.println(insert);
        /*如果没有指定id，MyBatis-Plus会根据雪花算法计算出id进行插入*/
        System.out.println(user.getId());//1553949203944251393，
    }
    
    
    /**
     * 测试BaseMapper删除操作
     */
    @Test
    void testDelete() {
        /*int i = userMapper.deleteById(6);*/
    
        /*HashMap<String, Object> map = new HashMap<>();
        map.put("id","6");
        int i = userMapper.deleteByMap(map);*/
        
        int i = userMapper.deleteBatchIds(Arrays.asList(6, 1553948610236350466L));//批量删除
        
        System.out.println(i);
    }
    
    
    /**
     * 测试BaseMapper修改操作
     */
    @Test
    void testUpdate() {
        User user = new User();
        user.setId(6L);
        user.setName("李四");
        user.setEmail("li@qq.com");
        int i = userMapper.updateById(user);
        System.out.println(i);
    }
    
    
    /**
     * 测试BaseMapper查询操作
     */
    @Test
    void testSelect() {
        /*User user = userMapper.selectById(6);
        System.out.println(user);*/
        
        /*HashMap<String, Object> map = new HashMap<>();
        map.put("id", 6);
        List<User> userList = userMapper.selectByMap(map);
        userList.forEach(System.out::println);*/
        
        /*List<User> userList = userMapper.selectBatchIds(Arrays.asList(6, 1553948610236350466L));//批量查询
        userList.forEach(System.out::println);*/
    
    
        //List<T> selectList(@Param(Constants.WRAPPER) Wrapper<T> queryWrapper);
        List<User> userList = userMapper.selectList(null);//传入null表示没有指定条件,即查询所有数据
        userList.forEach(System.out::println);
    }
    
    
    /**
     * 测试自定义功能
     */
    @Test
    void testCustom() {
        Map<String, Object> map = userMapper.selectMapById(6L);
        System.out.println(map);
    }
}
