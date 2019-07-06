package com.mock.mocker;


import com.mock.MockException;
import com.mock.Mocker;
import com.mock.config.DataConfig;
import com.mock.util.RandomUtils;

/**
 * Enum对象模拟器
 */
public class EnumMocker<T extends Enum> implements Mocker<Object> {

    private Class<?> clazz;

    public EnumMocker(Class<?> clazz) {
        this.clazz = clazz;
    }

    @Override
    public T mock(DataConfig mockConfig) {

        Enum[] enums = mockConfig.globalConfig().getcacheEnum(clazz.getName());
        if (enums == null) {
            //  Field field = clazz.getDeclaredField("$VALUES");
            // field.setAccessible(true);
            enums = (Enum[]) clazz.getEnumConstants();
            if (enums.length == 0) {
                throw new MockException("空的enum不能模拟");
            }
            mockConfig.globalConfig().cacheEnum(clazz.getName(), enums);
        }
        return (T) enums[RandomUtils.nextInt(0, enums.length)];
    }

}
