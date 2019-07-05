package com.mock.config;

import com.mock.util.RandomUtils;

/**
 * @ClassName BeanConfig
 * @Description TODO
 * @Author 梁臣
 * @Date 2019/7/5 14:39
 * @Version 1.0
 **/
public class BeanConfig extends AbstractBeanConfig {

    @Override
    public Object getField(String fieldName) {
        return this.fields.get(fieldName);
    }

    @Override
    public BeanConfig setField(String key, Object value) {
        this.fields.put(key, value);
        return this;
    }


    public BeanConfig setStrField(String key, String[][] stringSeed) {
        String value = "";
        for (String[] stringSee : stringSeed) {
            int index = RandomUtils.nextInt(0, stringSee.length);
            value = value + stringSee[index];
        }
        this.fields.put(key, value);
        return this;
    }
}
