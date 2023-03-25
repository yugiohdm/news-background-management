package com.example.demo2.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

public class CodeGenerator {
    public static void main(String[] args) {
        fast();
    }
    public static  void fast(){
        String url="jdbc:mysql://localhost:3306/ddd?serverTimezone=UTC&&characterEncoding=utf-8";
        String username="root";
        String password="123456";

        String pkgpath=System.getProperty("user.dir")+"/src/main/java";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("1951300310邓旭波") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(pkgpath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.demo2");// 设置父包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude("userdd"); // 设置需要生成的表名
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
