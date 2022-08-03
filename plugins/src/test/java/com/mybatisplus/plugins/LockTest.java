package com.mybatisplus.plugins;

import com.mybatisplus.mapper.ProductMapper;
import com.mybatisplus.pojo.Product;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Description:
 */
@SpringBootTest
public class LockTest {
    @Autowired
    private ProductMapper productMapper;
    
    @Test
    void testLock() {
        /*一起查询*/
        //小李查询商品价格
        Product productLi = productMapper.selectById(1);
        System.out.println("小李查询的商品价格：" + productLi.getPrice());
        //小王查询商品价格
        Product productWang = productMapper.selectById(1);
        System.out.println("小王查询的商品价格：" + productWang.getPrice());
        
        /*依次修改*/
        //小李将商品价格+50
        productLi.setPrice(productLi.getPrice() + 50);
        productMapper.updateById(productLi);
        //小王将商品价格-30
        productWang.setPrice(productWang.getPrice() - 30);
        int i = productMapper.updateById(productWang);//设置了乐观锁后version版本不对则不会更新，需要重新查询信息并修改信息
        if (i == 0) {
            //操作失败，重试
            Product productNew = productMapper.selectById(1);
            productNew.setPrice(productNew.getPrice() - 30);
            productMapper.updateById(productNew);
        }
        
        //老板查询商品价格
        Product productBoss = productMapper.selectById(1);
        System.out.println("老板查询的商品价格：" + productBoss.getPrice());
    }
}
