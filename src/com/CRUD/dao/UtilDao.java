package com.CRUD.dao;

import java.sql.*;
import java.util.List;

public class UtilDao {
    private static final String DRIVER_CLASS="com.mysql.cj.jdbc.Driver";
    private static final String URL="jdbc:mysql://localhost:3306/t1905?characterEncoding=utf8&useSSL=false&serverTimezone=CST";
    private static final String USERNAME="root";
    private static final String PASSWORD="123456";

    public Connection getConnection(){

        try {
            Class.forName(DRIVER_CLASS);
            return DriverManager.getConnection(URL,USERNAME,PASSWORD);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }


    public void closeAll(Connection conn, PreparedStatement prs, ResultSet rs){

        try {
            if(rs!=null) rs.close();
            if(prs!=null) prs.close();
            if(conn!=null) conn.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int executeUpdate(String sql, List<Object> parList){
        Connection conn = null;
        PreparedStatement prs = null;

        try {
            conn = this.getConnection();
            prs = conn.prepareStatement(sql);

            if (parList!=null && parList.size()>0){
                for (int i = 0; i < parList.size(); i++) {
                    prs.setObject(i+1,parList.get(i));
                }
            }
            //执行sql并获取受影响行数
            int rows =  prs.executeUpdate();
            return rows;
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.closeAll(conn,prs,null);
        }
        return 0;
    }

}
