package com.github.qqklm.fluentmybatis.config.dao;

import cn.org.atool.fluent.mybatis.spring.MapperFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@MapperScan(
        basePackages = "com.github.qqklm.fluentmybatis.pg.mapper",
        sqlSessionTemplateRef = PgSqlDataSource.PG_SQL_SESSION_TEMPLATE_NAME
)
@Configuration
public class PgSqlDataSource {
    public static final String PG_DATASOURCE_NAME = "pgDataSource";
    public static final String PG_SQL_SESSION_FACTORY_NAME = "pgSqlSessionFactory";
    public static final String PG_DATASOURCE_PROPERTIES = "spring.datasource.pg";
    public static final String PG_TRANSACTION_MANAGER_NAME = "pgTransactionManager";
    public static final String PG_SQL_SESSION_TEMPLATE_NAME = "pgSqlSessionTemplate";
    public static final String PG_JDBC_TEMPLATE_NAME = "pgJdbcTemplate";
    public static final String PG_MAPPER_FACTORY_NAME = "pgMapperFactory";

    @Primary
    @Bean(name = PG_DATASOURCE_NAME)
    @ConfigurationProperties(prefix = PG_DATASOURCE_PROPERTIES)
    public DataSource dataSource() {
        return DataSourceBuilder.create().build();
    }

    @Primary
    @Bean(name = PG_SQL_SESSION_FACTORY_NAME)
    public SqlSessionFactory sqlSessionFactory(@Qualifier(PG_DATASOURCE_NAME) DataSource dataSource) throws Exception {
        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
        bean.setDataSource(dataSource);
        // 如果使用mybatis原生方式，需要在此处配置xml文件
//        bean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:mapper/*.xml"));
        return bean.getObject();
    }

    @Primary
    @Bean(name = PG_TRANSACTION_MANAGER_NAME)
    public DataSourceTransactionManager transactionManager(@Qualifier(PG_DATASOURCE_NAME) DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Primary
    @Bean(name = PG_SQL_SESSION_TEMPLATE_NAME)
    public SqlSessionTemplate sqlSessionTemplate(@Qualifier(PG_SQL_SESSION_FACTORY_NAME) SqlSessionFactory sqlSessionFactory) {
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    @Primary
    @Bean(name = PG_JDBC_TEMPLATE_NAME)
    public JdbcTemplate jdbcTemplate(@Qualifier(PG_DATASOURCE_NAME) DataSource dataSource) {
        return new JdbcTemplate(dataSource);
    }

    @Primary
    @Bean(name = PG_MAPPER_FACTORY_NAME)
    public MapperFactory mapperFactory() {
        return new MapperFactory();
    }
}
