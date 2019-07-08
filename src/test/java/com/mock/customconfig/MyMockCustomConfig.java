package com.mock.customconfig;

import com.mock.config.MockConfig;
import com.mock.config.MockCustomConfig;
import org.hibernate.validator.Length;

import java.lang.reflect.Field;

/**
 * @ClassName MockCustomConfig
 * @Description 自定义配置
 * @Author 梁臣
 * @Date 2019/7/8 23:10
 * @Version 1.0
 **/
public class MyMockCustomConfig extends MockCustomConfig {
    @Override
    public <T> MockConfig setMockConfig(Class<T> clazz, String fieldName) {

        try {
            Field field = clazz.getDeclaredField(fieldName);
            Length length = field.getAnnotation(Length.class);
            if (null != length) {
                int max = length.max();
                int min = length.min();
                MockConfig mockConfig = new MockConfig().
                        subConfig(clazz, fieldName)
                        .stringSeed("&", "%", "*")
                        .sizeRange(min, max)
                        .globalConfig();
                return mockConfig;
            } else {
                return new MockConfig();
            }

        } catch (NoSuchFieldException e) {
            e.printStackTrace();
            return new MockConfig();
        }
    }
}
