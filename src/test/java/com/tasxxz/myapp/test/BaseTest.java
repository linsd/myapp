package com.tasxxz.myapp.test;

import com.tasxxz.myapp.encrypt.Base64Util;
import org.junit.Test;

import javax.xml.bind.DatatypeConverter;
import java.util.Base64;

/**
 * Created by linshudeng on 2016/9/25.
 */
public class BaseTest {

    @Test
    public void testDatatypeConverter1() {
        int i = DatatypeConverter.parseInt("12");
        System.out.println(i);
        String input = "sdfsqwedfsuydg";
        String out = Base64.getEncoder().encodeToString(input.getBytes());
        System.out.println(out);

        System.out.println(DatatypeConverter.printBase64Binary(input.getBytes()));
    }

    @Test
    public void testBase641() {
        String input = "21ha按时大大ywk";
        String out6 = Base64Util.encode6(input.getBytes());
        System.out.println(out6);

        String out8 = Base64Util.encode8(input.getBytes());
        System.out.println(out8);

        System.out.println(new String(Base64Util.decode6(out8)));
        System.out.println(new String(Base64Util.decode8(out6)));
    }
}
