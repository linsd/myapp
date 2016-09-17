package com.tasxxz.myapp.util;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class MD5Utils {

    private final static String[] hexDigits = {
            "0", "1", "2", "3", "4", "5", "6", "7",
            "8", "9", "a", "b", "c", "d", "e", "f"};

    public static String byteArrayToHexString2(byte[] b) {
        StringBuffer resultSb = new StringBuffer();
        for (int i = 0; i < b.length; i++) {
            int n = b[i];
            if (n < 0)
                n = 256 + n;
            int d1 = n / 16;
            int d2 = n % 16;

            resultSb.append(hexDigits[d1]).append(hexDigits[d2]);
        }
        return resultSb.toString();
    }

    /**
     * 字节数组转16进制字符串
     * @param b
     * @return
     */
    public static String byteArrayToHexString(byte[] b) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < b.length; i++) {
            sb.append(Character.forDigit((b[i] >> 4) & 0xf, 16));
            sb.append(Character.forDigit(b[i] & 0xf, 16));
        }
        return sb.toString();
    }

    /**
     * 以字节数组形式返回MD5计算结果
     * @param input
     * @return
     */
    public static byte[] digest(String input) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return md.digest(input.getBytes());
    }

    /**
     * 以字符串(16进制)形式返回MD5计算结果
     * @param input
     * @return
     */
    public static String encode(String input) {
        return byteArrayToHexString(digest(input));
    }

}
