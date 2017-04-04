package DAO;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Created by linGo on 2017/3/29.
 */
public class DBcommonUtil {
    private static String driver="com.mysql.jdbc.Driver";

    private static DBcommonUtil dBcommonUtil;
    DBcommonUtil()
    {
        try {
            Class.forName(driver);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }
    public static DBcommonUtil getCommonDataBaseUtil()
    {
        if( dBcommonUtil ==null)
            dBcommonUtil=new DBcommonUtil();
        return dBcommonUtil;
    }
    public static Connection getConnection() throws ClassNotFoundException, SQLException {
        Connection conn = DriverManager.getConnection("jdbc:mysql://127.0.0.1:3306/hotelmanage?useUnicode=true&characterEncoding=utf-8","lin","123lin");
        return conn;
    }

    public static void main(String[]argv) throws SQLException, ClassNotFoundException {
       /* DBcommonUtil util = DBcommonUtil.getCommonDataBaseUtil();
        Connection connection =  util.getConnection();
        System.out.print(connection);*/
    }

}

