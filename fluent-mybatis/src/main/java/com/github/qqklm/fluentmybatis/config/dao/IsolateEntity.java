package com.github.qqklm.fluentmybatis.config.dao;

/**
 * entity的统一处理,比如使用雪花算法设置ID，设置新增人、设置修改人
 * 此处的接口字段应该和表的列对应的Java字段一致，返回值应该满足lombok需求，
 * 让其通过@Data和@Accessors注解生成的方法满足接口实现需求
 *
 * @author wb
 * @date 2022/4/25 11:21
 */
public interface IsolateEntity {
    /**
     * 获取主键
     *
     * @return 主键
     */
    Long getId();

    /**
     * 设置主键
     *
     * @param id 主键
     */
    IsolateEntity setId(Long id);

    /**
     * 获取新增人
     *
     * @return 新增人
     */
    Long getCreatedBy();

    /**
     * 设置新增人
     *
     * @param createdBy 新增人
     */
    IsolateEntity setCreatedBy(Long createdBy);

    /**
     * 获取更新人
     *
     * @return 更新人
     */
    Long getUpdatedBy();

    /**
     * 设置更新人
     *
     * @param updatedBy 更新人
     */
    IsolateEntity setUpdatedBy(Long updatedBy);
}
