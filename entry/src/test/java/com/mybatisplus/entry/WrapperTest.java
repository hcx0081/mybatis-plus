package com.mybatisplus.entry;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.toolkit.StringUtils;
import com.mybatisplus.mapper.UserMapper;
import com.mybatisplus.pojo.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Map;

/**
 * @Description:
 */
@SpringBootTest
public class WrapperTest {
    @Autowired
    private UserMapper userMapper;
    
    
    /*==================================QueryWrapper====================================*/
    
    /**
     * 查询用户姓名包含”三“，年龄在20~30之间，邮箱不为null
     */
    @Test
    void test01() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like("name", "三")
                .between("age", "20", "30")
                .isNotNull("email");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }
    
    /**
     * 查询用户年龄信息，按照年龄的降序排序；若年龄相同，则按照id升序排序
     */
    @Test
    void test02() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.orderByDesc("age")
                .orderByAsc("id");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }
    
    /**
     * 删除年龄为null的用户信息
     */
    @Test
    void test03() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.isNull("age");
        int i = userMapper.delete(queryWrapper);
        System.out.println(i);
    }
    
    /**
     * 将（年龄大于20且用户名中包含有“三”）或邮箱为null）的用户信息进行修改：
     * QueryWrapper<User> queryWrapper = new QueryWrapper<>();
     *         queryWrapper.gt("age", 20)
     *                 .like("name", "三")
     *                 .or()
     *                 .isNull("email");
     *
     * UPDATE user SET name=? WHERE (age > ? AND name LIKE ? OR email IS NULL)
     *
     * 如果条件之间没有声明and或者or，那么默认拼接and
     * and的优先级比or高，可以使用and()方法来指定优先级
     */
    /**
     * 将年龄大于20且（用户名中包含有“三”或邮箱为null）的用户信息进行修改
     * <p>
     * UPDATE user SET name=? WHERE (age > ? AND (name LIKE ? OR email IS NULL))
     */
    @Test
    void test04() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.gt("age", 20)
                .and(userQueryWrapper -> userQueryWrapper.like("name", "三")
                        .or()
                        .isNull("email"));
        User user = new User();
        user.setName("张五");
        int i = userMapper.update(user, queryWrapper);
        System.out.println(i);
    }
    
    /**
     * 查询部分字段（用户名，年龄）
     */
    @Test
    void test05() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.select("name", "age");
        List<Map<String, Object>> mapList = userMapper.selectMaps(queryWrapper);
        mapList.forEach(System.out::println);
    }
    
    /**
     * 查询id小于100的用户信息
     */
    @Test
    void test06() {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.inSql("id", "select id from user where id<100");
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }
    
    
    /*==================================UpdateWrapper====================================*/
    
    /**
     * 将年龄大于20且（用户名中包含有“三”或邮箱为null）的用户信息进行修改
     */
    @Test
    void test07() {
        UpdateWrapper<User> updateWrapper = new UpdateWrapper<>();
        updateWrapper.gt("age", "20")
                .and(userUpdateWrapper -> userUpdateWrapper.like("name", "三")
                        .or()
                        .isNull("email"));
        updateWrapper.set("name", "张三三");
        updateWrapper.set("age", "10");
        int i = userMapper.update(null, updateWrapper);//无需创建实体类对象
        System.out.println(i);
    }
    
    
    /*==================================动态封装条件====================================*/
    
    /**
     * 查询指定姓名且年龄在指定区间的用户
     */
    @Test
    void test08() {
        String name = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        
        //判断某个字符串是否为null，全是空白字符
        if (StringUtils.isNotBlank(name)) {
            queryWrapper.like("name", name);
        }
        if (ageBegin != null) {
            queryWrapper.ge("age", ageBegin);
        }
        if (ageEnd != null) {
            queryWrapper.le("age", ageEnd);
        }
        
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }
    
    /**
     * 查询指定姓名且年龄在指定区间的用户（改进版）
     */
    @Test
    void test09() {
        String name = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name)//判断某个字符串是否为null，全是空白字符
                .ge(ageBegin != null, "age", ageBegin)
                .le(ageEnd != null, "age", ageEnd);
        
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }
    
    
    /**
     * 查询指定姓名且年龄在指定区间的用户（改进防错版，LambdaQueryWrapper）
     * 通过访问实体类的属性名自动映射为数据库中的字段名
     */
    @Test
    void test10() {
        String name = "";
        Integer ageBegin = 20;
        Integer ageEnd = 30;
        LambdaQueryWrapper<User> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.like(StringUtils.isNotBlank(name), User::getName, name)//判断某个字符串是否为null，全是空白字符
                .ge(ageBegin != null, User::getAge, ageBegin)
                .le(ageEnd != null, User::getAge, ageEnd);
        
        List<User> userList = userMapper.selectList(queryWrapper);
        userList.forEach(System.out::println);
    }
    
    
    /**
     * 将年龄大于20且（用户名中包含有“三”或邮箱为null）的用户信息进行修改（防错版，LambdaUpdateWrapper）
     * 通过访问实体类的属性名自动映射为数据库中的字段名
     */
    @Test
    void test11() {
        LambdaUpdateWrapper<User> updateWrapper = new LambdaUpdateWrapper<>();
        updateWrapper.gt(User::getAge,20)
                .and(userUpdateWrapper -> userUpdateWrapper.like(User::getName, "三")
                        .or()
                        .isNull(User::getEmail));
        updateWrapper.set(User::getName,"张三三");
        updateWrapper.set(User::getAge, "10");
        int i = userMapper.update(null, updateWrapper);//无需创建实体类对象
        System.out.println(i);
    }
}
