package com.mock;

import com.mock.bean.StringBean;
import com.mock.config.AbstractBeanConfig;
import com.mock.config.BeanConfig;
import com.mock.config.MockConfig;
import com.mock.util.JsonUtil;
import org.junit.Test;

import java.util.List;

/**
 * @ClassName BeanMockTest
 * @Description TODO
 * @Author 梁臣
 * @Date 2019/7/5 10:43
 * @Version 1.0
 **/
public class BeanMockTest {

    @Test
    public void testBean() {
        String[][] arry = {{"One1", "One2"}, {"two"}, {"three1", "three2", "three3"}};

        MockConfig mockConfig = new MockConfig();
        mockConfig.setCustomConfig(new BeanConfig().setField("a1", "aa").setField("a2", "aaa").
                setStrField("a3", arry));
        //模拟集合实体
        List<StringBean> basicBeanList = Mock.mock(new TypeKit<List<StringBean>>() {
        }, mockConfig);
        System.out.println("实体:" + JsonUtil.toStr(basicBeanList));
    }


    @Test
    public void testBean1() {
        MockConfig mockConfig = new MockConfig();
        mockConfig.setCustomConfig(new AbstractBeanConfig() {
            @Override
            public Object getField(String fieldName) {
                return this.fields.get(fieldName);
            }

            @Override
            public AbstractBeanConfig setField(String key, Object value) {
                this.fields.put(key, "mmmmmm");
                return this;
            }
        }.setField("a1", "aaa"));
        //模拟集合实体
        List<StringBean> basicBeanList = Mock.mock(new TypeKit<List<StringBean>>() {
        }, mockConfig);
        System.out.println("实体:" + JsonUtil.toStr(basicBeanList));
    }

}
