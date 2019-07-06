package com.mock.mocker;


import com.mock.Mocker;
import com.mock.config.DataConfig;
import com.mock.util.RandomUtils;
import com.mock.util.StringUtils;

/**
 * 模拟Short对象
 */
public class ShortMocker implements Mocker<Short> {

    @Override
    public Short mock(DataConfig mockConfig) {
        /**
         * 若根据正则模拟
         */
        if (StringUtils.isNotEmpty(mockConfig.numberRegex())) {
            return RandomUtils.nextNumberFromRegex(mockConfig.numberRegex()).shortValue();
        }
        return (short) RandomUtils.nextInt(mockConfig.shortRange()[0], mockConfig.shortRange()[1]);
    }

}
