package com.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatisplus.entity.Product;
import com.mybatisplus.mapper.ProductMapper;
import com.mybatisplus.service.ProductService;
import org.springframework.stereotype.Service;

@Service
@DS("slave_1")// 操作slave_1数据源
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {
    
}