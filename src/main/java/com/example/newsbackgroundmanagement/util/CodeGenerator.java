package com.example.newsbackgroundmanagement.util;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;

public class CodeGenerator {
    public static void main(String[] args) {
        fast();
    }
    public static  void fast(){
        String url="jdbc:mysql://8.134.162.148:3305/dxb?useUnicode=true&characterEncoding=utf-8&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
        String username="root";
        String password="fariytail";

        String pkgpath=System.getProperty("user.dir")+"/src/main/java";
        FastAutoGenerator.create(url, username, password)
                .globalConfig(builder -> {
                    builder.author("邓旭波") // 设置作者
//                            .enableSwagger() // 开启 swagger 模式
                            .fileOverride() // 覆盖已生成文件
                            .outputDir(pkgpath); // 指定输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("com.example.newsbackgroundmanagement");// 设置父包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude("releasenews"); // 设置需要生成的表名
                })
//                .templateEngine(new FreemarkerTemplateEngine()) // 使用Freemarker引擎模板，默认的是Velocity引擎模板
                .execute();

    }
}
