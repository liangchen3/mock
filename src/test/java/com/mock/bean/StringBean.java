package com.mock.bean;

import lombok.Data;
import org.hibernate.validator.Length;

/**
 * @ClassName StringBean
 * @Description TODO
 * @Author 梁臣
 * @Date 2019/7/7 10:22
 * @Version 1.0
 **/
@Data
public class StringBean {
    @Length(min = 1, max = 1, message = "string1长度为1")
    private String string1;
    @Length(min = 2, max = 2, message = "string2长度为2")
    private String string2;
    @Length(min = 3, max = 3, message = "string3长度为3")
    private String string3;
    @Length(min = 4, max = 4, message = "string4长度为4")
    private String string4;
    private String string5;
    private String string6;
    private String string7;
    private String string8;
    private String string9;

}
