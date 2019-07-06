package com.mock.mocker;


import com.mock.Mocker;
import com.mock.config.DataConfig;

import java.lang.reflect.ParameterizedType;

/**
 * 模拟泛型
 */
public class GenericMocker implements Mocker<Object> {

    private ParameterizedType type;

    GenericMocker(ParameterizedType type) {
        this.type = type;
    }

    @Override
    public Object mock(DataConfig mockConfig) {
        return new BaseMocker(type.getRawType(), type.getActualTypeArguments()).mock(mockConfig);
    }

}
