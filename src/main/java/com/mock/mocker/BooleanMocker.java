package com.mock.mocker;


import com.mock.Mocker;
import com.mock.config.DataConfig;
import com.mock.util.RandomUtils;

/**
 * Boolean对象模拟器
 */
public class BooleanMocker implements Mocker<Boolean> {

    @Override
    public Boolean mock(DataConfig mockConfig) {
        boolean[] booleanSeed = mockConfig.booleanSeed();
        return booleanSeed[RandomUtils.nextInt(0, booleanSeed.length)];
    }

}
