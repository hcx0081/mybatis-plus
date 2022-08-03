package com.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.pojo.User;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    Map<String, Object> selectMapById(Long id);
}




