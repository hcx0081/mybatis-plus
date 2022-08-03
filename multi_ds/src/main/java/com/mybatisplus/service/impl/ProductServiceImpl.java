package com.mybatisplus.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.mybatisplus.pojo.Product;
import com.mybatisplus.service.ProductService;
import com.mybatisplus.mapper.ProductMapper;
import org.springframework.stereotype.Service;

@Service
@DS("slave_1")//操作slave_1组的数据源
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product>
        implements ProductService {
    
}




