package com.github.qqklm.fluentmybatis.config.dao;

import cn.org.atool.fluent.mybatis.base.IEntity;
import cn.org.atool.fluent.mybatis.base.crud.IDefaultSetter;
import cn.org.atool.fluent.mybatis.base.crud.IUpdate;
import com.github.qqklm.fluentmybatis.util.EntityIdUtil;

import java.util.Objects;

/**
 * 统一处理entity的通用设置
 *
 * @author wb
 * @date 2022/4/25 11:25
 */
public interface IsolateSetter extends IDefaultSetter {
    /**
     * 为entity添加默认值
     *
     * @param entity entity
     */
    @Override
    default void setInsertDefault(IEntity entity) {
        IsolateEntity isolateEntity = (IsolateEntity) entity;
        if (isolateEntity.getId() == null) {
            isolateEntity.setId(EntityIdUtil.nextId());
        }
        // TODO 设置新增人
        if (Objects.isNull(isolateEntity.getCreatedBy())) {
//            isolateEntity.setCreatedBy(1L);
        }
    }

    /**
     * 为更新添加默认值或默认条件
     *
     * @param updater updater
     */
    @Override
    default void setUpdateDefault(IUpdate updater) {
        // TODO 设置更新人

//            updater.updateSet("", 1L);
    }
}
