package com.base;

/**
 * TODO: 
 *
 * @author djq
 * @date 2021/7/9 14:09
 */
public class 字符串大小 {
    public static void main(String[] args) {
        ABC();
    }

    private static void ABC() {
        String a ="A";
        String b ="B";
        String c ="D";
        int d = a.compareTo(a);
        int e = a.compareTo(b);
        int f = c.compareTo(b);
        System.out.println(d);
        System.out.println(e);
        System.out.println(f);
    }
}

