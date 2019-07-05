package com.mock.config;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @ClassName AbstartConfig
 * @Description
 * @Author 梁臣
 * @Date 2019/7/5 15:12
 * @Version 1.0
 **/
public abstract class AbstractBeanConfig {

    /**
     * 需要特殊处理的字段放在这里
     */
    protected final Map<String, Object> fields = new ConcurrentHashMap<>();

    public abstract Object getField(String fieldName);

    public abstract AbstractBeanConfig setField(String key, Object value);
}
