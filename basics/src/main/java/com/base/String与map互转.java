package com.base;

import com.alibaba.fastjson.JSONObject;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * String 转 map
 * map 转 String
 *
 * @author djq
 * @date 2022/5/13 11:16
 */
public class String与map互转 {
    public static void main(String[] args) {
        LinkedHashMap<String, Object> map = new LinkedHashMap<>();
        map.put("id","973230817662869500");
        map.put("rule_name","条件");
        map.put("rule_code","202205091431487886");
        map.put("category_id","972543553286840300");
        map.put("rule_scripts","select twp_ab_datasource.* , twp_ab_rule.*  from twp_ab_datasource as twp_ab_datasource left join twp_ab_rule as twp_ab_rule on twp_ab_rule.id = twp_ab_rule.id where    twp_ab_datasource.ds_name= twp_ab_datasource.ds_name*1   twp_ab_rule.category_id= twp_ab_rule.category_id*1");
        map.put("rule_type",1);

        String s = JSONObject.toJSONString(map);

        Map m = (Map) JSONObject.parse(s);

        System.out.println(s);
        System.out.println(m);
        System.out.println();


    }
}
