package com.base.utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * 链接mysql
 *
 * @author djq
 * @date 2022/5/9 15:40
 */
public class ConnectMysql {
    static String url = "jdbc:mysql://172.18.20.34:23308/db_pierce_dev?useUnicode=true&characterEncoding=utf-8&useSSL=false";
    static String user = "root";
    static String password = "pierce_123";

    static Connection connection;
    final static String driver = "com.mysql.cj.jdbc.Driver";
    // 提供ps成员变量以便于关闭
    PreparedStatement ps;

    ResultSet rs;

    public ConnectMysql(){
        try {
            Class.forName(driver);
            connection = DriverManager.getConnection(url,user,password);
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    /**
     * 获取数据库链接
     */
    public Connection getConnection() {
        return connection;
    }

    /**
     * 获取具有执行sql语句的功能对象
     */
    public Statement getStatement() throws SQLException {
        Statement statement = getConnection().createStatement();
        return statement;
    }

    /**
     * 使用此方法或者间接使用此方法后 需调用closePreparedStatement() 或者 closeAll() 方法
     */
    public PreparedStatement getPreparedStatement(String sql) throws SQLException {
        return ps = getConnection().prepareStatement(sql);
    }

    /**
     * 获取查询结果ResultSet对象
     */
    public ResultSet getResultSet(String sql) throws SQLException {
        return getPreparedStatement(sql).executeQuery();
    }

    public ResultSetMetaData getResultSetMeta(String sql) throws SQLException{
        return getResultSet(sql).getMetaData();
    }

    /**
     * 获取查询返回结果的列数(本方法不易反复调用)
     */
    public int getColumnSize(String sql) throws SQLException{
        ResultSetMetaData resultSetMetaData = getResultSetMeta(sql);
        return resultSetMetaData.getColumnCount();
    }

    /**
     * 获取查询结果列名
     */
    public List<String> getColumnNames(String sql) throws SQLException{
        ResultSetMetaData resultSetMetaData = getResultSetMeta(sql);
        int columnSize = resultSetMetaData.getColumnCount();
        List<String> result = new ArrayList<>();
        for (int i =1;i<columnSize;i++){
            result.add(resultSetMetaData.getColumnName(i));
        }
        return result;
    }

    public void printSearchResult(String sql) throws SQLException{
        ResultSet resultSet = getResultSet(sql);
        int columnSize = getColumnSize(sql);
        while (resultSet.next()){
            for (int i = 1; i<=columnSize;i++){
                System.out.printf("%-20s",resultSet.getObject(i));
            }
            System.out.println("---");
        }
        System.out.println("\n结束查询");
    }

    /**
     * 关闭链接
     */
    public void closeConnection(){
        try {
            if (connection != null){
                connection.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closePreparedStatement(){
        try {
            if (ps != null){
                ps.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeResultSet(){
        try {
            if (rs != null){
                rs.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }

    public void closeAll(){
        closeConnection();
        closePreparedStatement();
        closeResultSet();
    }

}
