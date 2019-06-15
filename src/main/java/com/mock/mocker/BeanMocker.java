package com.mock.mocker;

import com.mock.MockConfig;
import com.mock.MockException;
import com.mock.Mocker;
import com.mock.annotation.MockIgnore;
import com.mock.util.ReflectionUtils;
import org.hibernate.validator.HibernateValidator;
import org.hibernate.validator.constraints.Length;

import javax.validation.groups.Default;
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
                    if (field.isAnnotationPresent(MockIgnore.class)) {
                        continue;
                    }
                    setHibernate(entry, mockConfig);
                    ReflectionUtils
                            .setRefValue(result, entry.getValue(), new BaseMocker(field.getGenericType()).mock(mockConfig));
                }
            }
            return result;
        } catch (Exception e) {
            throw new MockException(e);
        }
    }

    /**
     * 根据hibernate注解定制化的给属性设置值
     *
     * @param entry
     * @param mockConfig
     */
    private void setHibernate(Entry<Field, Method> entry, MockConfig mockConfig) {
        //判断是否开启 hibernate注解赋值
        if (!mockConfig.isEnableHibernateAnnonation()) {
            return;
        }

        Field field = entry.getKey();
        Class targetGroup = mockConfig.getHibernateGroup();
        if (field.isAnnotationPresent(Length.class)) {
            Length length = entry.getKey().getAnnotation(Length.class);
            if (0 == length.groups().length) {
                int[] beanFieldSizeRange = {length.min(), length.max()};
                mockConfig.registerFieldSize(beanFieldSizeRange);
                return;
            }
            for (Class group : length.groups()) {
                if (targetGroup.equals(group)) {
                    int[] beanFieldSizeRange = {length.min(), length.max()};
                    mockConfig.registerFieldSize(beanFieldSizeRange);
                } else {
                    int[] beanFieldSizeRange = {mockConfig.getSizeRange()[0], mockConfig.getSizeRange()[1]};
                    mockConfig.registerFieldSize(beanFieldSizeRange);
                }
            }
        } else {
            int[] beanFieldSizeRange = {mockConfig.getSizeRange()[0], mockConfig.getSizeRange()[1]};
            mockConfig.registerFieldSize(beanFieldSizeRange);
        }

    }

}
