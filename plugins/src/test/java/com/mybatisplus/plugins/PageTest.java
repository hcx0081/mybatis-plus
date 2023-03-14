package com.mybatisplus.plugins;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.entity.User;
import com.mybatisplus.mapper.UserMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PageTest {
    
    @Autowired
    private UserMapper userMapper;
    
    /**
     * 测试基础分页功能
     */
    @Test
    void pageTest() {
        Page<User> page = new Page<>(2, 3);// 指定当前页码和每页显示记录数
        userMapper.selectPage(page, null);
        System.out.println("总页数：" + page.getPages());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("有无上一页：" + page.hasPrevious());
        System.out.println("有无下一页：" + page.hasNext());
    }
    
    /**
     * 测试自定义分页功能
     */
    @Test
    void pageVoTest() {
        Page<User> page = new Page<>(2, 3);// 指定当前页码和每页显示记录数
        userMapper.selectPageVo(page, 10);
        System.out.println("总页数：" + page.getPages());
        System.out.println("总记录数：" + page.getTotal());
        System.out.println("有无上一页：" + page.hasPrevious());
        System.out.println("有无下一页：" + page.hasNext());
    }
}