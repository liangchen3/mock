package com.mock.mocker;


import com.mock.Mocker;
import com.mock.config.DataConfig;

import java.math.BigDecimal;

/**
 * BigDecimal对象模拟器
 */
public class BigDecimalMocker implements Mocker<BigDecimal> {

    @Override
    public BigDecimal mock(DataConfig mockConfig) {
        return BigDecimal.valueOf(mockConfig.globalConfig().getMocker(Double.class).mock(mockConfig));
    }

}
