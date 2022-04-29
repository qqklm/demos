package com.github.qqklm.fluentmybatis;

import com.github.qqklm.fluentmybatis.mysql.dao.base.UserBaseDao;
import com.github.qqklm.fluentmybatis.mysql.entity.UserEntity;
import com.github.qqklm.fluentmybatis.mysql.wrapper.UserQuery;
import com.github.qqklm.fluentmybatis.pg.dao.base.DataFromBaseDao;
import com.github.qqklm.fluentmybatis.pg.entity.DataFromEntity;
import com.github.qqklm.fluentmybatis.pg.wrapper.DataFromQuery;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@SpringBootApplication
public class FluentMybatisApplication {
    private final UserBaseDao userBaseDao;
    private final DataFromBaseDao dataFromBaseDao;

    public FluentMybatisApplication(UserBaseDao userBaseDao, DataFromBaseDao dataFromBaseDao) {
        this.userBaseDao = userBaseDao;
        this.dataFromBaseDao = dataFromBaseDao;
    }

    public static void main(String[] args) {
        SpringApplication.run(FluentMybatisApplication.class, args);
    }

    @GetMapping("/mysql")
    public List<UserEntity> mysql() {
        return this.userBaseDao.listEntity(UserQuery.query());
    }

    @GetMapping("/pg")
    public List<DataFromEntity> pg() {
        return this.dataFromBaseDao.listEntity(DataFromQuery.query());
    }

}
