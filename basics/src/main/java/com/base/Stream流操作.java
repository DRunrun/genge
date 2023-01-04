package com.base;

import com.alibaba.fastjson.JSONObject;
import com.base.entity.Student;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * TODO
 *
 * @author djq
 * @date 2022-11-30 16:27
 **/
public class Stream流操作 {
    public static void main(String[] args) {
        //过滤
        filterList();
    }

    /**
     * list过滤
     */
    private static void filterList() {
        List<Student> students = new ArrayList<>();
        Student student = new Student();
        student.setName("张三");
        student.setAge(33);
        student.setSex((byte) 0);
        students.add(student);
        List<Student> result = students.stream().filter(o->!o.getName().equals("张三")).collect(Collectors.toList());
        System.out.println(JSONObject.toJSONString(result));

    }
}
