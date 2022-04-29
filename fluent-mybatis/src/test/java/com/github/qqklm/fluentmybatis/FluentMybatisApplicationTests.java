package com.github.qqklm.fluentmybatis;

import cn.org.atool.fluent.mybatis.metadata.DbType;
import cn.org.atool.generator.FileGenerator;
import cn.org.atool.generator.annotation.Table;
import cn.org.atool.generator.annotation.Tables;
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
        // 引用配置类，build方法允许有多个配置类，生成多个数据源
        FileGenerator.build( MySqlEmpty.class, PgEmpty.class);
    }

    @Tables(
            // 设置数据库连接信息
            url = "jdbc:mysql://192.168.0.54:3306/wb_test",
            username = "root",
            password = "wb_239500",
            dbType = DbType.MYSQL,
            // entity和dao的package,多数据源下该属性应该一致，否则生成的bean会有多个重名，区分用entitySuffix属性区分
            basePack = "com.github.qqklm.fluentmybatis.mysql",
            // 设置哪些表要生成Entity文件
            tables = {@Table(value = "user")},

            // 设置entity类生成src目录, 相对于 user.dir
            srcDir = "src/main/java",
            // 设置dao接口和实现的src目录, 相对于 user.dir
            daoDir = "src/main/java",
            // 此处设置是针对所有表
            gmtCreated = "",
            gmtModified = "",
            logicDeleted = ""
    )
    static class MySqlEmpty { //类名随便取, 只是配置定义的一个载体
    }

    @Tables(
            // 设置数据库连接信息
            url = "jdbc:postgresql://localhost:5432/postgres",
            username = "postgres",
            password = "wb_239500",
            // 对于MySQL无需指定，但是对于类似pg等一定要指定
            schema = "nifi",
            dbType = DbType.POSTGRE_SQL,
            // entity和dao的package,多数据源下该属性应该一致，否则生成的bean会有多个重名，区分用entitySuffix属性区分
            basePack = "com.github.qqklm.fluentmybatis.pg",
            // 设置哪些表要生成Entity文件
            tables = {@Table(value = "data_from")},
            // 设置entity类生成src目录, 相对于 user.dir
            srcDir = "src/main/java",
            // 设置dao接口和实现的src目录, 相对于 user.dir
            daoDir = "src/main/java",
            // 此处设置是针对所有表
            gmtCreated = "",
            gmtModified = "",
            logicDeleted = ""
    )
    static class PgEmpty { //类名随便取, 只是配置定义的一个载体
    }

}
