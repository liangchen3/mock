package com.mock.mocker;


import com.mock.Mocker;
import com.mock.config.DataConfig;

import java.math.BigInteger;

/**
 * BigInteger对象模拟器
 */
public class BigIntegerMocker implements Mocker<BigInteger> {
    @Override
    public BigInteger mock(DataConfig mockConfig) {
        return BigInteger.valueOf(mockConfig.globalConfig().getMocker(Long.class).mock(mockConfig));
    }

}
