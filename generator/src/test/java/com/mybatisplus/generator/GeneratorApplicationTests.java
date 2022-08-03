package com.mybatisplus.generator;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.config.OutputFile;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.io.IOException;
import java.util.Collections;

@SpringBootTest
class GeneratorApplicationTests {
    
    @Test
    void contextLoads() throws IOException {
        String finalProjectPath = new File("").getCanonicalPath(); // 获取当前项目的路径
        FastAutoGenerator.create("jdbc:mysql://localhost:3306/mybatis_plus", "root", "623363564")
                .globalConfig(builder -> {
                    builder
                            //.author("baomidou") // 设置作者
                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .disableOpenDir() // 禁止打开输出目录
                            .outputDir(finalProjectPath + "/src/main/java"); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.mybatisplus") // 设置父包名
                            .entity("pojo") // 设置实体类包名
                            .pathInfo(Collections.singletonMap(OutputFile.xml, finalProjectPath + "/src/main/java/com/mybatisplus/mapper")); // 设置mapperXml生成路径
                })
                .strategyConfig(builder -> {
                    builder.addInclude("user") // 设置需要生成的表名
                            //.addTablePrefix("t_", "c_"); // 设置过滤表前缀
                            .entityBuilder() // Entity 策略配置
                            .enableLombok() // 开启 lombok 模型
                            .enableTableFieldAnnotation() // 开启生成实体时生成字段注解
                            .disableSerialVersionUID(); // 禁用生成 serialVersionUID
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();
    }
}