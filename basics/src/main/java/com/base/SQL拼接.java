package com.base;

import com.base.utils.DynamicSQL;

/**
 * TODO:
 *
 * @author djq
 * @date 2022/4/20 14:26
 */
public class SQL拼接 {
    public static void main(String[] args) {
        DynamicSQL dsql = new DynamicSQL();
        dsql.append("select * ");
        dsql.append(" from twp_ab_datasource");
        dsql.append(" where ds_name = 'pierce_dev'");
        //dsql.clear();
        System.out.println(dsql.getSql());
        System.out.println(dsql);
    }
}
