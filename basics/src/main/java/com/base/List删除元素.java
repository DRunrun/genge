package com.base;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * TODO: 
 *
 * @author djq
 * @date 2021/8/3 8:40
 */
public class List删除元素 {
    public static void main(String[] args) {
        List<Integer> list = new ArrayList<>();
        for (int i = 0;i<10;i++){
            list.add(i);
        }
        System.out.println(list);
        //错误
        //for (Integer i : list){
        //    list.remove(i);
        //}
        Iterator<Integer> iterator = list.listIterator();
        while (iterator.hasNext()){
            Integer i = iterator.next();
            iterator.remove();
        }
        System.out.println(list);
    }
}
