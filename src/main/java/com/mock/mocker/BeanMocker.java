package com.mock.mocker;


import com.mock.MockException;
import com.mock.Mocker;
import com.mock.annotation.MockIgnore;
import com.mock.config.DataConfig;
import com.mock.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Map.Entry;

public class BeanMocker implements Mocker<Object> {

    private final Class clazz;

    BeanMocker(Class clazz) {
        this.clazz = clazz;
    }

    @Override
    public Object mock(DataConfig mockConfig) {

        try {
            // fixme 解决方案不够优雅
            if (mockConfig.globalConfig().isEnabledCircle()) {
                Object cacheBean = mockConfig.globalConfig().getcacheBean(clazz.getName());
                if (cacheBean != null) {
                    return cacheBean;
                }
            }
            Object result = clazz.newInstance();
            mockConfig.globalConfig().cacheBean(clazz.getName(), result);
            /**
             * 是否配置排除整个类
             */

            if (mockConfig.globalConfig().isConfigExcludeMock(clazz)) {
                return result;
            }
            for (Class<?> currentClass = clazz; currentClass != Object.class; currentClass = currentClass.getSuperclass()) {
                // 模拟有setter方法的字段
                for (Entry<Field, Method> entry : ReflectionUtils.fieldAndSetterMethod(currentClass).entrySet()) {
                    Field field = entry.getKey();
                    if (field.isAnnotationPresent(MockIgnore.class)) {
                        continue;
                    }
                    /**
                     * 是否配置排除这个属性
                     */
                    if (mockConfig.globalConfig().isConfigExcludeMock(clazz, field.getName())) {
                        continue;
                    }
                    ReflectionUtils
                            .setRefValue(result, entry.getValue(), new BaseMocker(field.getGenericType()).mock(mockConfig.globalConfig().getDataConfig(currentClass, field.getName())));
                }
            }
            return result;
        } catch (Exception e) {
            throw new MockException(e);
        }
    }

}
