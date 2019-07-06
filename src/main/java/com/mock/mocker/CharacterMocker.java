package com.mock.mocker;


import com.mock.Mocker;
import com.mock.config.DataConfig;
import com.mock.util.RandomUtils;

/**
 * Character对象模拟器
 */
public class CharacterMocker implements Mocker<Character> {

    @Override
    public Character mock(DataConfig mockConfig) {
        char[] charSeed = mockConfig.charSeed();
        return charSeed[RandomUtils.nextInt(0, charSeed.length)];
    }

}
