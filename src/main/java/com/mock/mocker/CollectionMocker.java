package com.mock.mocker;


import com.mock.Mocker;
import com.mock.config.DataConfig;
import com.mock.util.RandomUtils;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

/**
 * 模拟Collection
 */
public class CollectionMocker implements Mocker<Object> {

    private Class clazz;

    private Type genericType;

    CollectionMocker(Class clazz, Type genericType) {
        this.clazz = clazz;
        this.genericType = genericType;
    }

    @Override
    public Object mock(DataConfig mockConfig) {
        int size = RandomUtils.nextSize(mockConfig.sizeRange()[0], mockConfig.sizeRange()[1]);
        Collection<Object> result;
        if (List.class.isAssignableFrom(clazz)) {
            result = new ArrayList<>(size);
        } else {
            result = new HashSet<>(size);
        }
        BaseMocker baseMocker = new BaseMocker(genericType);
        for (int index = 0; index < size; index++) {
            result.add(baseMocker.mock(mockConfig));
        }
        return result;
    }

}
