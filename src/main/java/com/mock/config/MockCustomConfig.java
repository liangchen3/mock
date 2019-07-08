package com.mock.config;

import com.mock.config.DataConfig;
import com.mock.config.MockConfig;

/**
 * @ClassName MockCustomSubConfig
 * @Description 用户自定义的配置     todo(这里的描述是否准确？)
 * @Author 梁臣
 * @Date 2019/7/7 23:43
 * @Version 1.0
 **/
public abstract class MockCustomConfig {

    public <T> DataConfig getDataConfig(Class<T> clazz, String fieldName) {
        return setMockConfig(clazz, fieldName).getDataConfig(clazz, fieldName);
    }

    public abstract <T> MockConfig setMockConfig(Class<T> clazz, String fieldName);
}
