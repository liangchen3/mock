package com.mock.bean;

import com.mock.annotation.MockRule;

/**
 * @ClassName TestBean
 * @Description TODO
 * @Author 梁臣
 * @Date 2019/6/15 17:42
 * @Version 1.0
 **/
public class AnnotationBean {

    @MockRule(order = 4, defaultSize = {19, 19})
    private String testStr;

    public String getTestStr() {
        return testStr;
    }

    public void setTestStr(String testStr) {
        this.testStr = testStr;
    }

    @Override
    public String toString() {
        return "TestBean{" +
                "testStr='" + testStr + '\'' +
                '}';
    }
}
