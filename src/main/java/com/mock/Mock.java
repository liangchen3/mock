package com.mock;

import com.mock.config.DataConfig;
import com.mock.config.MockConfig;
import com.mock.mocker.BaseMocker;

/**
 * 模拟对象门面类
 *
 * @author jsonzou, kanyuxia, TaoYu
 */
public class Mock {

    /**
     * 模拟数据
     *
     * @param clazz 模拟数据类型
     * @return 模拟数据对象
     */
    public static <T> T mock(Class<T> clazz) {
        return mock(clazz, new MockConfig());
    }

    /**
     * 模拟数据
     *
     * @param clazz      模拟数据类型
     * @param mockConfig 模拟数据配置
     * @return 模拟数据对象
     */
    public static <T> T mock(Class<T> clazz, MockConfig mockConfig) {
        DataConfig config = mockConfig.getDataConfig(clazz, null);
        return new BaseMocker<T>(clazz).mock(config);
    }

    /**
     * 模拟数据
     * <pre>
     * 注意typeReference必须以{}结尾
     * </pre>
     *
     * @param typeReference 模拟数据包装类型
     * @return 模拟数据对象
     */
    public static <T> T mock(TypeKit<T> typeReference) {
        return mock(typeReference, new MockConfig());
    }

    /**
     * 模拟数据
     * <pre>
     * 注意typeReference必须以{}结尾
     * </pre>
     *
     * @param typeReference 模拟数据类型
     * @param mockConfig    模拟数据配置
     * @return 模拟数据对象
     */
    public static <T> T mock(TypeKit<T> typeReference, MockConfig mockConfig) {
        mockConfig.init(typeReference.getType());
        DataConfig config = mockConfig.globalDataConfig();
        return new BaseMocker<T>(typeReference.getType()).mock(config);
    }
}
