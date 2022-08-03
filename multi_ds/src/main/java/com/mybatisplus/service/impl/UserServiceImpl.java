package com.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatisplus.pojo.User;
import com.mybatisplus.service.UserService;
import com.mybatisplus.mapper.UserMapper;
import org.springframework.stereotype.Service;

@Service
@DS("master")//操作master组的数据源
public class UserServiceImpl extends ServiceImpl<UserMapper, User>
        implements UserService {
    
}



