package com.base;

import com.alibaba.fastjson.JSONObject;

import java.util.ArrayList;
import java.util.List;

/**
 * TODO: 
 *
 * @author djq
 * @date 2021/8/3 8:40
 */
public class List相关 {
    public static void main(String[] args) {
       String str = list转字符串();
        System.out.println(str);
//      int i = List取值("bbb");
//        System.out.println(i);
    }

    private static int List取值(String str) {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        list.add("aaa");
        return list.indexOf(str);

    }

    private static String list转字符串() {
        List<String> list = new ArrayList<>();
        list.add("aaa");
        list.add("bbb");
        list.add("ccc");
        list.add("ddd");
        String str = JSONObject.toJSONString(list).replace("\"","");
        String str1 = JSONObject.toJSONString(list);

        List<String> result = JSONObject.parseArray(str1,String.class);
        return str.substring(1,str.length()-1);
    }
}
