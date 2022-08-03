package com.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.mybatisplus.pojo.User;

public interface UserMapper extends BaseMapper<User> {
    
    /**
     * 查询大于指定年龄的分页信息并分页
     *
     * @param page
     * @param age
     * @return
     */
    Page<User> selectPageVo(Page<User> page, Integer age);
}
