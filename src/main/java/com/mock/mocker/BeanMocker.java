package com.mock.mocker;

import com.mock.config.MockConfig;
import com.mock.MockException;
import com.mock.Mocker;
import com.mock.annotation.MockIgnore;
import com.mock.annotation.MockRule;
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
    public Object mock(MockConfig mockConfig) {
        try {
            // fixme 解决方案不够优雅
            if (mockConfig.isEnabledCircle()) {
                Object cacheBean = mockConfig.getcacheBean(clazz.getName());
                if (cacheBean != null) {
                    return cacheBean;
                }
            }
            Object result = clazz.newInstance();
            //todo 这里可能会造成大量无用数据，因为假如创建很多对象，而又没有开启缓存，这里将会保存很多对象
            mockConfig.cacheBean(clazz.getName(), result);
            for (Class<?> currentClass = clazz; currentClass != Object.class; currentClass = currentClass.getSuperclass()) {
                // 模拟有setter方法的字段
                for (Entry<Field, Method> entry : ReflectionUtils.fieldAndSetterMethod(currentClass).entrySet()) {
                    Field field = entry.getKey();
                    if (mockConfig.isEnableAnnonation()) {
                        if (field.isAnnotationPresent(MockIgnore.class)) {
                            continue;
                        }
                    }

                    //判断有没有MockRule注解
                    setAnnotation(result, entry, mockConfig);
                    //判断有没有需要特殊处理的字段
                    setCustomedField(result, entry, mockConfig);

                }
            }
            return result;
        } catch (Exception e) {
            throw new MockException(e);
        }
    }

    /**
     * 根据注解定制化的给属性设置值
     *
     * @param entry
     * @param mockConfig
     */
    private void setAnnotation(Object result, Entry<Field, Method> entry, MockConfig mockConfig) throws Exception {
        //判断是否开启 注解赋值
//        if (!mockConfig.isEnableAnnonation()) {
//            return;
//        }

        Field field = entry.getKey();
        if (field.isAnnotationPresent(MockRule.class)) {
            MockRule mockRule = entry.getKey().getAnnotation(MockRule.class);
            int order = mockRule.order();
            int[] sizeRange;
            if (order != Integer.MIN_VALUE) {
                sizeRange = mockConfig.getCustomSizeRange(order);
                if (null == sizeRange) {
                    sizeRange = mockRule.defaultSize();
                }
            } else {
                sizeRange = mockConfig.getSizeRange();
            }
            mockConfig.putTempSizeRange(sizeRange);
        } else {
            mockConfig.putTempSizeRange(mockConfig.getSizeRange());
        }

        ReflectionUtils
                .setRefValue(result, entry.getValue(), new BaseMocker(field.getGenericType()).mock(mockConfig));
    }

    /**
     * 判断有没有需要特殊处理的字段
     *
     * @param entry
     * @param mockConfig
     */
    private void setCustomedField(Object result, Entry<Field, Method> entry, MockConfig mockConfig) throws Exception {
        Field field = entry.getKey();
        String fieldName = field.getName();
        if (null == mockConfig.getCustomConfig()) {
            return;
        }
        Object fieldValue = mockConfig.getCustomConfig().getField(fieldName);
        if (null != fieldValue) {
            ReflectionUtils
                    .setRefValue(result, entry.getValue(), fieldValue);
        }
    }

}
