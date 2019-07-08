package com.mock;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.mock.bean.*;
import com.mock.bean.circular.AXB;
import com.mock.bean.enums.DayEnum;
import com.mock.bean.enums.ErrorEnum;
import com.mock.config.MockConfig;
import org.junit.Test;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;

import static org.junit.Assert.*;

public class MockDataTest {

    @Test
    public void testBasic() {
        //基本类型模拟
        int intNum = Mock.mock(int.class);
        assertNotNull(intNum);
        int[] intArray = Mock.mock(int[].class);
        assertNotNull(intArray);
        Integer integer = Mock.mock(Integer.class);
        assertNotNull(integer);
        Integer[] integerArray = Mock.mock(Integer[].class);
        assertNotNull(integerArray);
        //常用类型模拟
        BigDecimal bigDecimal = Mock.mock(BigDecimal.class);
        assertNotNull(bigDecimal);
        BigInteger bigInteger = Mock.mock(BigInteger.class);
        assertNotNull(bigInteger);
        Date date = Mock.mock(Date.class);
        assertNotNull(date);
        String str = Mock.mock(String.class);
        assertNotNull(str);
        DayEnum dayEnum = Mock.mock(DayEnum.class);
        assertNotNull(dayEnum);

        try {
            Mock.mock(ErrorEnum.class);
            fail();
        } catch (Exception e) {
            // Ignore
        }
    }

    @Test
    public void testBasicData() {
        BasicBean basicBean = Mock.mock(BasicBean.class);
        assertNotNull(basicBean);

        try {
            Mock.mock(ErrorBean.class);
            fail();
        } catch (Exception e) {
            // Ignore
        }
    }

    @Test
    public void testCircular() {
        MockConfig mockConfig = new MockConfig().setEnabledCircle(true);
        AXB axb = Mock.mock(AXB.class, mockConfig);
        AXB circularAxb = axb.getBXA().getAXB();
        assertSame(axb, circularAxb);
    }

    @Test
    public void testSelf() {
        MockConfig mockConfig = new MockConfig().setEnabledCircle(true);
        SelfRefData selfRefData = Mock.mock(SelfRefData.class, mockConfig);
        assertSame(selfRefData.getParent(), selfRefData);
    }

    @Test
    //******注意TypeKit要加{}才能模拟******
    public void testTypeRefrence() {
        //模拟基础类型，不建议使用这种方式，参考基础类型章节直接模拟。
        Integer integerNum = Mock.mock(new TypeKit<Integer>() {
        });
        assertNotNull(integerNum);
        Integer[] integerArray = Mock.mock(new TypeKit<Integer[]>() {
        });
        assertNotNull(integerArray);
        //模拟集合
        List<Integer> integerList = Mock.mock(new TypeKit<List<Integer>>() {
        });
        assertNotNull(integerList);
        //模拟数组集合
        List<Integer[]> integerArrayList = Mock.mock(new TypeKit<List<Integer[]>>() {
        });
        assertNotNull(integerArrayList);
        //模拟集合数组
        List<Integer>[] integerListArray = Mock.mock(new TypeKit<List<Integer>[]>() {
        });
        assertNotNull(integerListArray);
        //模拟集合实体
        List<BasicBean> basicBeanList = Mock.mock(new TypeKit<List<BasicBean>>() {
        });
        assertNotNull(basicBeanList);
        //各种组合忽略。。。。map同理。下面模拟一个不知道什么类型的map
        Map<List<Map<Integer, String[][]>>, Map<Set<String>, Double[]>> some = Mock
                .mock(new TypeKit<Map<List<Map<Integer, String[][]>>, Map<Set<String>, Double[]>>>() {
                });
        assertNotNull(some);
    }

    @Test
    public void testGenericData() {
        GenericData<Integer, String, BasicBean> genericData = Mock.mock(new TypeKit<GenericData<Integer, String, BasicBean>>() {
        });
        assertNotNull(genericData);
    }

//    @Test
//    public void testMockConfig() {
//        MockConfig mockConfig = new MockConfig()
//                .byteRange((byte) 0, Byte.MAX_VALUE)
//                .shortRange((short) 0, Short.MAX_VALUE)
//                .intRange(0, Integer.MAX_VALUE)
//                .floatRange(0.0f, Float.MAX_EXPONENT)
//                .doubleRange(0.0, Double.MAX_VALUE)
//                .longRange(0, Long.MAX_VALUE)
//                .dateRange("2010-01-01", "2020-12-30")
//                .sizeRange(5, 10)
//                .stringSeed("a", "b", "c")
//                .charSeed((char) 97, (char) 98);
//        BasicBean basicBean = Mock.mock(BasicBean.class, mockConfig);
//        assertNotNull(basicBean);
//
//        try {
//            Mock.mock(BasicBean.class, new MockConfig().dateRange("20100101", "20301230"));
//            fail();
//        } catch (Exception e) {
//            // Ignore
//        }
//    }

    /**
     * 自定义配置测试
     * 排除字段测试
     */
    @Test
    public void testCustomDataConfigAndExclude() {
        MockConfig mockConfig = new MockConfig()
                // 全局配置
                .globalConfig()
                .sizeRange(1, 1)
                .charSeed((char) 97, (char) 98)
                .byteRange((byte) 0, Byte.MAX_VALUE)
                .shortRange((short) 0, Short.MAX_VALUE)

                // 某些字段（名等于integerNum的字段、包含float的字段、double开头的字段）配置
                .subConfig("integerNum", "*float*", "double*")
                .intRange(10, 19)
                .floatRange(1.22f, 1.50f)
                .doubleRange(1.50, 1.99)

                // 某个类的某些字段（long开头的字段、date结尾的字段、包含string的字段）配置。
                .subConfig(BasicBean.class, "long*", "*date", "*string*")
                .longRange(12, 13)
                .dateRange("2018-11-20", "2018-11-30")
                .stringSeed("SAVED", "REJECT", "APPROVED").stringSeed("****", "&&&", "AAA")
                .sizeRange(1, 1)

                // 全局配置
                .globalConfig()
                // 排除所有包含list/set/map字符的字段。表达式不区分大小写。
                .excludes("*List*", "*Set*", "*Map*")
                // 排除所有Array开头/Boxing结尾的字段。表达式不区分大小写。
                .excludes(BasicBean.class, "*Array", "Boxing*");
        BasicBean basicBean = Mock.mock(BasicBean.class, mockConfig);
        assertNotNull(basicBean);
        List<BasicBean> basicBeans = Mock.mock(new TypeKit<List<BasicBean>>() {
        }, mockConfig);
        assertNotNull(basicBeans);
        System.out.println(JSON.toJSONString(basicBean, true));
        System.out.println("==============================");
        System.out.println(JSON.toJSONString(basicBeans, true));
    }

    @Test
    public void testBooleanMock() {
        MockConfig mockConfig = new MockConfig()
                // .booleanSeed(true,true)
                .subConfig(Boolean.class)
                // .booleanSeed(false,false)
                .globalConfig();
        for (int i = 0; i < 100; i++) {
            System.out.print(Mock.mock(Boolean.class, mockConfig) + " ");
        }
    }

    /**
     * 根据正则模拟数据
     */
    @Test
    public void testXegerMock() {
        MockConfig mockConfig = new MockConfig()
                // 随机段落字符串
                .stringRegex("I'am a nice man\\.And I'll just scribble the characters, like：[a-z]{2}-[0-9]{2}-[abc123]{2}-\\w{2}-\\d{2}@\\s{1}-\\S{1}\\.?-.")
                // 邮箱
                .subConfig(RegexTestDataBean.class, "userEmail")
                .stringRegex("[a-z0-9]{5,15}\\@\\w{3,5}\\.[a-z]{2,3}")
                // 用户名规则
                .subConfig(RegexTestDataBean.class, "userName")
                .stringRegex("[a-zA-Z_]{1}[a-z0-9_]{5,15}")
                // 年龄
                .subConfig(RegexTestDataBean.class, "userAge")
                .numberRegex("[1-9]{1}\\d?")
                // 用户现金
                .subConfig(RegexTestDataBean.class, "userMoney")
                .numberRegex("[1-9]{2}\\.\\d?")
                // 用户的得分
                .subConfig(RegexTestDataBean.class, "userScore")
                .numberRegex("[1-9]{1}\\d{1}")
                // 用户身价
                .subConfig(RegexTestDataBean.class, "userValue")
                .numberRegex("[1-9]{1}\\d{3,8}")
                .globalConfig();

        System.out.println(JSONObject.toJSONString(Mock.mock(RegexTestDataBean.class, mockConfig), true));

    }

    /**
     * 测试小数位数
     */
    @Test
    public void testDecimalScaleMock() {
        MockConfig mockConfig = new MockConfig()
                .doubleRange(-1.1d, 9999.99999d)
                .floatRange(-1.11111f, 9999.99999f)
                .decimalScale(3) // 设置小数位数为3，默认是2
                .globalConfig();
        for (int i = 0; i < 100; i++) {
            System.out.print(Mock.mock(BigDecimal.class, mockConfig) + " ");
            System.out.print(Mock.mock(Double.class, mockConfig) + " ");
            System.out.println(Mock.mock(Float.class, mockConfig) + " ");
        }
    }

    /**
     * 测试无域对象
     */
    @Test
    public void testNoneFieldBeanMock() {
        NoneFieldObject noneFieldObject = Mock.mock(NoneFieldObject.class);
        System.out.println(noneFieldObject);
    }

    /**
     * 测试无域对象
     */
    @Test
    public void testInnerBeanMock() {
        InnerBeanObject innerBeanObject = Mock.mock(InnerBeanObject.class);
        InnerBeanObject.InnerBean innerBean = Mock.mock(InnerBeanObject.InnerBean.class);
        System.out.println(innerBeanObject);
        System.out.println(innerBean);
    }

    /**
     * 测试Lombok对象
     */
    @Test
    public void testLombokBeanMock() {
        LombokBean lombokBean = Mock.mock(LombokBean.class);
        System.out.println(JSON.toJSONString(lombokBean, true));
    }

    /**
     * 测试字段全是String类型的对象
     */
    @Test
    public void tesStringBeanMock() {
        MockConfig mockConfig = new MockConfig()
                // 全局配置
                .globalConfig()
                .sizeRange(1, 1)
                .charSeed((char) 97, (char) 98)
                .byteRange((byte) 0, Byte.MAX_VALUE)
                .shortRange((short) 0, Short.MAX_VALUE)

                // 某个类的某些字段（long开头的字段、date结尾的字段、包含string的字段）配置。
                .subConfig(StringBean.class, "long*", "string*", "*4*")
                .longRange(12, 13)
                .dateRange("2018-11-20", "2018-11-30")
                .stringSeed("SAVED", "REJECT", "APPROVED").stringSeed("****", "&&&", "AAA")
                .sizeRange(1, 1)

                // 某个类的某些字段（long开头的字段、date结尾的字段、包含string的字段）配置。
                .subConfig(StringBean.class, "long*", "string*", "*5")
                .longRange(12, 13)
                .dateRange("2018-11-20", "2018-11-30")
                .stringSeed("SAVED", "REJECT", "APPROVED").stringSeed("****", "&&&", "AAA")
                .sizeRange(2, 2)

                // 某个类的某些字段（long开头的字段、date结尾的字段、包含string的字段）配置。
                .subConfig(StringBean.class, "long*", "string5", "*6")
                .longRange(12, 13)
                .dateRange("2018-11-20", "2018-11-30")
                .stringSeed("@", "#", "$").stringSeed("1", "2", "3")
                .sizeRange(1, 1)

                // 某个类的某些字段（long开头的字段、date结尾的字段、包含string的字段）配置。
                .subConfig(StringBean.class, "*3", "string4", "*6")
                .longRange(12, 13)
                .dateRange("2018-11-20", "2018-11-30")
                .stringSeed("{", "(", "|").stringSeed("777", "888", "999")
                .sizeRange(1, 1)

                .globalConfig();

        StringBean stringBean = Mock.mock(StringBean.class, mockConfig);
        assertNotNull(stringBean);
        System.out.println(JSON.toJSONString(stringBean, true));
    }


}
