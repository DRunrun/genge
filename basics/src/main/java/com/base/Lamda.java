package com.base;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 
 *
 * @author djq
 * @date 2021/6/25 14:15
 */
public class Lamda {
    public static void main(String[] args) {
        ListForeach();
    }

    private static void ListForeach() {
        List<String> a = new ArrayList<>();
        a.add("1");
        a.add("2");
        a.add("3");
        a.add("4");
        a.add("5");
        int b = 0 ;
        for (String i : a) {
            System.out.println("for:"+i);
        }
        a.forEach(i->{
            System.out.println("forEach:"+i);
        });
    }
}
