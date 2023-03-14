package com.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.mybatisplus.entity.User;

import java.util.Map;

public interface UserMapper extends BaseMapper<User> {
    Map<String, User> selectMapById(Long id);
}