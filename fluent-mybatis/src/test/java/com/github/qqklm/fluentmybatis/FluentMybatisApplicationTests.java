package com.github.qqklm.fluentmybatis;

import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;
import com.github.qqklm.fluentmybatis.config.IsolateEntity;
import com.github.qqklm.fluentmybatis.config.IsolateSetter;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class FluentMybatisApplicationTests {

    @Test
    void contextLoads() {
    }

    /**
     * 生成dao相关代码
     */
    @Test
    public void generate() {
        // 引用配置类，build方法允许有多个配置类
        FileGenerator.build(Empty.class);
    }

    @Tables(
            // 设置数据库连接信息
            url = "jdbc:mysql://localhost:3306/wb_test?useUnicode=true&characterEncoding=utf8",
            username = "root",
            password = "wb_239500",
            // 设置entity类生成src目录, 相对于 user.dir
            srcDir = "src/main/java",
            // 设置entity类的package值
            basePack = "com.github.qqklm.fluentmybatis",
            // 设置dao接口和实现的src目录, 相对于 user.dir
            daoDir = "src/main/java",
            // 此处设置是针对所有表
            gmtCreated = "",
            gmtModified = "",
            logicDeleted = "",
            // 设置哪些表要生成Entity文件
            tables = {@Table(value = "user", gmtModified = "updated_time", gmtCreated = "created_time", logicDeleted = "deleted", entity = IsolateEntity.class, defaults = IsolateSetter.class)}
    )
    static class Empty { //类名随便取, 只是配置定义的一个载体
    }

}
