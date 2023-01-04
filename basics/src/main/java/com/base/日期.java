package com.base;

import com.alibaba.fastjson.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * TODO
 *
 * @author djq
 * @date 2022-10-18 17:20
 **/
public class 日期 {

    public static void main(String[] args) {
        // 获取近六年年份
//        List<Integer> list = getSixYear();
        // 当前时间
        Date大小比较();

    }

    private static void Date大小比较() {
        Date d1 = new Date();
        Date d2 = new Date(2);
        Boolean bool = d1.after(d2);
    }

    /**
     * 获取近六年年份
     */
    private static List<Integer> getSixYear(){
        Calendar calendar = Calendar.getInstance();
        int year = calendar.get(Calendar.YEAR);
        List<Integer> res=new ArrayList<>();
        for(int i=year-5;i<=year;i++){
            res.add(i);
        }
        return res;
    }
}
