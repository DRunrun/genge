package com.base;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 判断是否为数字
 *
 * @author djq
 * @date 2022/5/13 16:50
 */
@SuppressWarnings("AlibabaAvoidPatternCompileInMethod")
public class 判断是否为数字 {
    public static void main(String[] args) {
        String a = "abc123";
        String b = "123";
        String c = "123.7";
        System.out.println(isInteger(b));
        System.out.println(isInteger(c));

    }

    private static Boolean isNumeric(String str) {
        for (int i = str.length(); --i>=0;){
            if (Character.isDigit(str.charAt(i))){
                return false;
            }
        }
        return true;
    }

    /**
     * 使用类型转换判断是否为数字
     */
    private static Boolean isDigit(String str) {
        try {
            Integer.valueOf(str);
            return true;
        }catch (Exception e){
            return false;
        }
    }
    private static boolean isInteger(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[\\d]*$");
        return pattern.matcher(str).matches();
    }


    private static boolean isDouble(String str) {
        if (null == str || "".equals(str)) {
            return false;
        }
        Pattern pattern = Pattern.compile("^[-\\+]?[.\\d]*$");
        return pattern.matcher(str).matches();
    }
}
