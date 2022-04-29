package com.github.qqklm.fluentmybatis.config.dao;

import cn.org.atool.fluent.mybatis.spring.MapperFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;


/**
 * @author wb
 */
@MapperScan(
        basePackages = "com.github.qqklm.fluentmybatis.mysql.mapper",
        sqlSessionTemplateRef = MySqlDataSource.MYSQL_SQL_SESSION_TEMPLATE_NAME
)
@Configuration
public class MySqlDataSource {
    public static final String MYSQL_DATASOURCE_NAME = "mysqlDataSource";
    public static final String MYSQL_SQL_SESSION_FACTORY_NAME = "mysqlSqlSessionFactory";
    public static final String MYSQL_DATASOURCE_PROPERTIES = "spring.datasource.mysql";
    public static final String MYSQL_TRANSACTION_MANAGER_NAME = "mysqlTransactionManager";
    public static final String MYSQL_SQL_SESSION_TEMPLATE_NAME = "mysqlSqlSessionTemplate";
    public static final String MYSQL_JDBC_TEMPLATE_NAME = "mysqlJdbcTemplate";
    public static final String MYSQL_MAPPER_FACTORY_NAME = "mysqlMapperFactory";

    @Bean(name = MYSQL_DATASOURCE_NAME)
    @ConfigurationProperties(prefix = MYSQL_DATASOURCE_PROPERTIES)
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean(name = MYSQL_SQL_SESSION_FACTORY_NAME)
    public SqlSessionFactory sqlSessionFactory(@Qualifier(MYSQL_DATASOURCE_NAME) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 如果使用mybatis原生方式，需要在此处配置xml文件
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Bean(name = MYSQL_TRANSACTION_MANAGER_NAME)
    public DataSourceTransactionManager transactionManager(@Qualifier(MYSQL_DATASOURCE_NAME) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = MYSQL_SQL_SESSION_TEMPLATE_NAME)
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(MYSQL_SQL_SESSION_FACTORY_NAME) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Bean(name = MYSQL_JDBC_TEMPLATE_NAME)
    public JdbcTemplate jdbcTemplate(@Qualifier(MYSQL_DATASOURCE_NAME) DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Bean(name = MYSQL_MAPPER_FACTORY_NAME)
    public MapperFactory mapperFactory() {
        return new MapperFactory();
    }
}
