package com.base;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 
 *
 * @author djq
 * @date 2021/7/9 14:09
 */
public class 字符串大小 {
    public static void main(String[] args) {
        StringBuilder a = new StringBuilder();
        a.append("A ").append("B");
        System.out.println(a.toString());

//        List<String> a = new ArrayList<>();
//        boolean b = a.contains(null);
//        System.out.println(b);
////        ABC();
    }

    private static void ABC() {
        StringBuilder cSql = new StringBuilder(" where ");
        StringBuilder sql = new StringBuilder(" where ");
        Boolean bool = cSql.toString().equals(sql.toString());
        Boolean bool1 = cSql.toString() == sql.toString();
        System.out.println(bool);
        System.out.println(bool1);
        //String a ="A";
        //String b ="B";
        //String c ="D";
        //int d = a.compareTo(a);
        //int e = a.compareTo(b);
        //int f = c.compareTo(b);
        //System.out.println(d);
        //System.out.println(e);
        //System.out.println(f);
    }
}

