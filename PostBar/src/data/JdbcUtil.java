package data;

import org.junit.Test;

import java.io.InputStream;
import java.sql.*;

import java.util.Properties;

/**
 * @Description 操作数据库的工具类
 * @Param
 * @Return
 */


public class JdbcUtil {
    /***
     * @Description 获取数据库的连接
     * @Param
     * @Return java.sql.Connection
     * @Since version-1.0
     */
    @Test
    public void connectionTest(){
        try {
            Connection conn = JdbcUtil.getConnection();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection() throws Exception {
        //获取四个基本信息
        String driverName="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String url="jdbc:sqlserver://localhost:1433;DatabaseName=TEST";
        String user="solveBug0";
        String password="qwxs123456";
        /*InputStream is = ClassLoader.getSystemClassLoader().getResourceAsStream("utils/jdbc.properties");
        Properties properties = new Properties();
        properties.load(is);
        String user = properties.getProperty("user");
        String driverClass = properties.getProperty("driverClass");
        String password = properties.getProperty("password");
        String url = properties.getProperty("url");*/

        //加载驱动
        Class.forName(driverName);

        //获取连接
        Connection connection = DriverManager.getConnection(url, user, password);
        return connection;

    }

    /***
     * @Description 关闭连接和Statement的操作
     * @Param
     * @Return void
     */

    public static void closeResource(Connection cone, Statement ps){
        // 7.资源的关闭
        try {
            if(ps != null) {
                ps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(cone != null){
                cone.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

    public static void closeResource(Connection cone, Statement ps, ResultSet rs){
        // 7.资源的关闭
        try {
            if(ps != null) {
                ps.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(cone != null){
                cone.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        try {
            if(rs != null) {
                rs.close();
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
    }

}
