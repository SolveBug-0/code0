package data;

import org.junit.Test;

import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.util.ArrayList;
import java.util.List;

/**
 * @author oolong
 */
public class JdbcCrud {
    /**
     * @Description 此方法是数据库的通用增删改方法，
     * @return 返回值为修改成功条目数，如果为0则修改失败
     */

    public static void update(String sql,Object ...args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            // 1.连接数据库
            connection = JdbcUtil.getConnection();
            // 2.预编译sql语句，返回PreparedStatement的实例
            ps = connection.prepareStatement(sql);
            // 3.填充占位符
            for (int i = 0; i < args.length; i++) {
                //小心参数声明错误！第一个是数据的参数从1开始，第二个是Java参数从0开始
                ps.setObject( i+1, args[i]);
            }
            // 4.执行
            /**
             * ps.executeUpdate()
             * 此方法返回值是修改成功的条数;
             * 修改成功多少就会返回多少，没有修改则返回0
             */
            // return ps.executeUpdate();
            ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.关闭资源
            JdbcUtil.closeResource(connection,ps);
        }

        // return 0;
    }

    public static void updateDel(String sql,Object ...args) {
        Connection connection = null;
        PreparedStatement ps = null;
        try {
            // 1.连接数据库
            connection = JdbcUtil.getConnection();
            // 2.预编译sql语句，返回PreparedStatement的实例
            ps = connection.prepareStatement(sql);
            // 3.填充占位符
            for (int i = 0; i < args.length; i++) {
                //小心参数声明错误！第一个是数据的参数从1开始，第二个是Java参数从0开始
                ps.setObject( i+1, args[i]);
            }
             //4.执行

             ps.executeUpdate();
             //此方法返回值是修改成功的条数;
             //修改成功多少就会返回多少，没有修改则返回0
            //return ps.executeUpdate();

            //ps.execute();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // 5.关闭资源
            JdbcUtil.closeResource(connection,ps);
        }

        // return 0;
    }
    
    /**
     * @Description 针对于不同表的通用的查询操作，返回表中的一组记录
     * @return 不同表的集合
     * 连接数据库
     * 预编译sql
     */
    

    public static  <T> List<T> getForList(Class<T> clazz,String sql, Object ...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 连接数据库
            conn = JdbcUtil.getConnection();
            // 预编译sql
            ps = conn.prepareStatement(sql);
            // 填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            // 得到结果集
            rs = ps.executeQuery();
            // 获得元数据
            ResultSetMetaData rsdm = rs.getMetaData();

            // 获取字段数目
            int columnCount = rsdm.getColumnCount();

            //创建集合对象
            ArrayList<T> list = new ArrayList<>();

            while(rs.next()){
                T t = clazz.newInstance();
                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i+1);
    
                    // 获取每个列名
                    String columnName = rsdm.getColumnName(i + 1);
    
                    Field field = clazz.getDeclaredField(columnName);
    
                    field.setAccessible(true);
    
                    field.set(t,columnValue);
    
                }
                list.add(t);
            }

            return list;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn,ps,rs);
        }

        return  null;
    }

    /**
     * @Description 针对于不同表的通用的查询操作，返回表中的一条记录
     * @return 查询表的类型
     */

    public static  <T> T getInstance(Class<T> clazz,String sql, Object ...args) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;
        try {
            // 连接数据库
            conn = JdbcUtil.getConnection();
            // 预编译sql
            ps = conn.prepareStatement(sql);
            // 填充占位符
            for (int i = 0; i < args.length; i++) {
                ps.setObject(i+1,args[i]);
            }
            // 得到结果集
            rs = ps.executeQuery();
            // 获得元数据
            ResultSetMetaData rsdm = rs.getMetaData();

            // 获取字段数目
            int columnCount = rsdm.getColumnCount();
            //创建数据对象
             T t = clazz.newInstance();
            if(rs.next()){

                for (int i = 0; i < columnCount; i++) {
                    Object columnValue = rs.getObject(i+1);

                    // 获取每个列名
                    String columnName = rsdm.getColumnName(i + 1);

                    Field field = clazz.getDeclaredField(columnName);

                    field.setAccessible(true);

                    field.set(t,columnValue);

                }

            }

            return t;

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            JdbcUtil.closeResource(conn,ps,rs);
        }

        return  null;
    }
}
