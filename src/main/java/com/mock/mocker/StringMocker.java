package com.mock.mocker;


import com.mock.Mocker;
import com.mock.config.DataConfig;
import com.mock.util.RandomUtils;
import com.mock.util.StringUtils;

import java.util.List;

/**
 * 模拟String对象
 */
public class StringMocker implements Mocker<String> {

    @Override
    public String mock(DataConfig mockConfig) {
        /**
         * 若根据正则模拟
         */
        if (StringUtils.isNotEmpty(mockConfig.stringRegex())) {
            return RandomUtils.nextStringFromRegex(mockConfig.stringRegex());
        }

        int size = RandomUtils.nextSize(mockConfig.sizeRange()[0], mockConfig.sizeRange()[1]);
        //查看stringSeed集合里有否有特殊的设置，若是没有则采用默认的配置，若是有则采用特殊的配置
        if (mockConfig.stringSeed().isEmpty()) {
            String[] stringSeed = mockConfig.stringSeedInit();
            StringBuilder sb = new StringBuilder(size);
            for (int i = 0; i < size; i++) {
                sb.append(stringSeed[RandomUtils.nextInt(0, stringSeed.length)]);
            }
            return sb.toString();
        } else {
            List<String[]> stringSeeds = mockConfig.stringSeed();
            StringBuilder sb = new StringBuilder(stringSeeds.size());
            for (int j = 0; j < size; j++) {
                for (int i = 0; i < stringSeeds.size(); i++) {
                    String[] stringSeed = stringSeeds.get(i);
                    sb.append(stringSeed[RandomUtils.nextInt(0, stringSeed.length)]);
                }
            }
            return sb.toString();
        }

    }

}
