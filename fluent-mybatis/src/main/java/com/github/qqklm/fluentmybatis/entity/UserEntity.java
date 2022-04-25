package com.github.qqklm.fluentmybatis.entity;

import cn.org.atool.fluent.mybatis.annotation.FluentMybatis;
import cn.org.atool.fluent.mybatis.annotation.LogicDelete;
import cn.org.atool.fluent.mybatis.annotation.TableField;
import cn.org.atool.fluent.mybatis.annotation.TableId;
import cn.org.atool.fluent.mybatis.base.RichEntity;
import com.github.qqklm.fluentmybatis.config.IsolateEntity;
import com.github.qqklm.fluentmybatis.config.IsolateSetter;
import java.util.Date;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.experimental.Accessors;

/**
 * UserEntity: 数据映射实体定义
 *
 * @author Powered By Fluent Mybatis
 */
@SuppressWarnings({"rawtypes", "unchecked"})
@Data
@Accessors(
    chain = true
)
@EqualsAndHashCode(
    callSuper = false
)
@FluentMybatis(
    table = "user",
    schema = "wb_test",
    defaults = IsolateSetter.class
)
public class UserEntity extends RichEntity implements IsolateEntity {
  private static final long serialVersionUID = 1L;

  /**
   * 主键
   */
  @TableId(
      value = "id",
      auto = false
  )
  private Long id;

  /**
   */
  @TableField(
      value = "created_time",
      insert = "now()"
  )
  private Date createdTime;

  /**
   */
  @TableField(
      value = "updated_time",
      insert = "now()",
      update = "now()"
  )
  private Date updatedTime;

  /**
   */
  @TableField(
      value = "deleted",
      insert = "0"
  )
  @LogicDelete
  private Boolean deleted;

  /**
   * 年龄
   */
  @TableField("age")
  private Integer age;

  /**
   */
  @TableField("created_by")
  private Long createdBy;

  /**
   */
  @TableField("dept_id")
  private Integer deptId;

  /**
   * 姓名
   */
  @TableField("name")
  private String name;

  /**
   */
  @TableField("updated_by")
  private Long updatedBy;

  @Override
  public final Class entityClass() {
    return UserEntity.class;
  }
}
