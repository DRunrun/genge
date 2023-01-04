package com.base;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * TODO
 *
 * @author djq
 * @date 2022-08-03 14:38
 **/
public class 字符串替换 {
    public static void main(String[] args) {
        SimpleDateFormat df = new SimpleDateFormat("yyyymmddHHmmss");
        String hiveScript = "CREATE TABLE IF NOT EXISTS djq测试勿动_v1_20223102113141 (名字 string,新闻来源 string,新闻ID string) ROW FORMAT DELIMITED FIELDS TERMINATED BY ',' COLLECTION ITEMS TERMINATED BY '|' ";
        String hiveName = "djq测试勿动_v1_20223102113141";
        String newHiveName = "djq测试勿动" + "_v" + 2 + "_" + df.format(new Date());
        String result = hiveScript.replace(hiveName,newHiveName);
        System.out.println(result);
    }
}
