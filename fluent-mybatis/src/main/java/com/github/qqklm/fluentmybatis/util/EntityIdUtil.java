package com.github.qqklm.fluentmybatis.util;

import cn.hutool.core.lang.Snowflake;
import cn.hutool.core.util.IdUtil;

/**
 * 雪花算法ID生成器
 *
 * @author wb
 * @date 2022/4/25 11:27
 */
public class EntityIdUtil {
    private static final Snowflake SNOW_FLAKE = IdUtil.getSnowflake(1L, 1L);

    public static Long nextId() {

        return SNOW_FLAKE.nextId();
    }
}
