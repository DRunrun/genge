package com.base;

import com.base.utils.ConnectMysql;

import java.util.List;

/**
 * JDBC链接数据库执行查询sql操作
 *
 * @author djq
 * @date 2022/5/9 17:08
 */
public class JDBC链接数据库执行sql查询操作 {
    public static void main(String[] args) throws Exception {
        String sql = "select * from twp_graph_table";
        ConnectMysql connectMysql = new ConnectMysql();
        int count = connectMysql.getColumnSize(sql);
        List<String> columnNames = connectMysql.getColumnNames(sql);
        System.out.println(count);
        System.out.println(columnNames);
        connectMysql.closeAll();
    }
}
