package com.base;
/**
 * 字符串截取
 *
 * @author djq
 * @date 2021/8/30 16:40
 */
public class 字符串截取 {
    public static void main(String[] args) {
        String couponCode = "1";
        String value = "**" + couponCode.substring((couponCode.length() - 4),couponCode.length());
        System.out.println(value);
    }
}
