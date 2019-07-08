package com.mock;

import com.alibaba.fastjson.JSON;
import com.mock.bean.StringBean;
import com.mock.config.MockConfig;
import com.mock.customconfig.MyMockCustomConfig;
import org.junit.Test;

import static org.junit.Assert.assertNotNull;

/**
 * @ClassName MockCustomConfigTest
 * @Description 用户自定义配置
 * @Author 梁臣
 * @Date 2019/7/8 23:14
 * @Version 1.0
 **/
public class MyMockCustomConfigTest {

    /**
     * 用户自定义配置
     */
    @Test
    public void tesStringBeanMock1() {
        MockConfig mockConfig = new MockConfig().
                addCustomConfig(new MyMockCustomConfig());
        StringBean stringBean = Mock.mock(StringBean.class, mockConfig);
        assertNotNull(stringBean);
        System.out.println(JSON.toJSONString(stringBean, true));
    }
}
