package com.mock.mocker;


import com.mock.Mocker;
import com.mock.config.DataConfig;
import com.mock.util.RandomUtils;
import com.mock.util.StringUtils;

import java.math.BigDecimal;

/**
 * Double对象模拟器
 */
public class DoubleMocker implements Mocker<Double> {

    @Override
    public Double mock(DataConfig mockConfig) {
        /**
         * 若根据正则模拟
         */
        if (StringUtils.isNotEmpty(mockConfig.numberRegex())) {
            return RandomUtils.nextNumberFromRegex(mockConfig.numberRegex()).setScale(mockConfig.decimalScale(), BigDecimal.ROUND_FLOOR).doubleValue();
        }
        return new BigDecimal(RandomUtils.nextDouble(mockConfig.doubleRange()[0], mockConfig.doubleRange()[1])).setScale(mockConfig.decimalScale(), BigDecimal.ROUND_FLOOR).doubleValue();
    }
}
