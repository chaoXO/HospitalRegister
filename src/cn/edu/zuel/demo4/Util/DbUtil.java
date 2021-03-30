package cn.edu.zuel.demo4.Util;

import java.sql.*;

public class DbUtil {
    // MySQL 8.0 以下版本 - JDBC 驱动名及数据库 URL
//    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
//    static final String DB_URL = "jdbc:mysql://localhost:3306/test666";
    static final String USER = "root";
    static final String PASS = "123456";
    // MySQL 8.0 以上版本 - JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.cj.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/test666?useSSL=false&serverTimezone=GMT%2B8&useUnicode=true&characterEncoding=UTF-8";
    // 数据库的用户名与密码，需要根据自己的设置
    public static Connection getConnection() throws ClassNotFoundException, SQLException{
        Class.forName(JDBC_DRIVER);
        Connection conn = DriverManager.getConnection(DB_URL,USER,PASS);
        return conn;
    }

    public static void release(PreparedStatement pstmt, Connection conn){
        release(null, pstmt, conn);
    }

    public static void release(ResultSet rs, PreparedStatement pstmt,Connection conn){
        try{
            if (rs != null){
                rs.close();
            }
            if (pstmt != null){
                pstmt.close();
            }
            if (conn != null){
                conn.close();
            }
        }catch (SQLException e){
            e.printStackTrace();
        }
    }
}
