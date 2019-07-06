package com.mock.mocker;


import com.mock.Mocker;
import com.mock.config.DataConfig;
import com.mock.util.RandomUtils;
import com.mock.util.StringUtils;

import java.math.BigDecimal;

/**
 * Float对象模拟器
 */
public class FloatMocker implements Mocker<Float> {

    @Override
    public Float mock(DataConfig mockConfig) {
        /**
         * 若根据正则模拟
         */
        if (StringUtils.isNotEmpty(mockConfig.numberRegex())) {
            return RandomUtils.nextNumberFromRegex(mockConfig.numberRegex()).setScale(mockConfig.decimalScale(), BigDecimal.ROUND_FLOOR).floatValue();
        }
        return new BigDecimal(RandomUtils.nextFloat(mockConfig.floatRange()[0], mockConfig.floatRange()[1])).setScale(mockConfig.decimalScale(), BigDecimal.ROUND_FLOOR).floatValue();
    }
}
