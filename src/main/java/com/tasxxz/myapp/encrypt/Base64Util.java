package com.tasxxz.myapp.encrypt;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;

/**
 * Created by linshudeng on 2016/9/25.
 */
public class Base64Util {

    /**
     * 用java8之前的DatatypeConverter
     * @param input
     * @return
     */
    public static String encode6(byte[] input) {
        return DatatypeConverter.printBase64Binary(input);
    }

    /**
     * 用java8之前的DatatypeConverter
     * @param input
     * @return
     */
    public static byte[] decode6(String input) {
        return DatatypeConverter.parseBase64Binary(input);
    }

    /**
     * 用java8新加的工具类
     * @param input
     * @return
     */
    public static String encode8(byte[] input) {
        return Base64.getEncoder().encodeToString(input);
    }

    /**
     * 用java8新加的工具类
     * @param input
     * @return
     */
    public static byte[] decode8(String input) {
        return Base64.getDecoder().decode(input);
    }
}
