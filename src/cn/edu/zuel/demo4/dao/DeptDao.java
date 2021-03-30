package cn.edu.zuel.demo4.dao;

import cn.edu.zuel.demo4.Util.DbUtil;
import cn.edu.zuel.demo4.model.Dept;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class DeptDao {
    //增加科室
    public static void insert(Dept dept) {

        try {
            Connection conn = DbUtil.getConnection();
            String sql = "insert into dept(id, name, type, remark)";
            sql += "value(?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, dept.getId());
            pstmt.setString(2, dept.getName());
            pstmt.setInt(3, dept.getType());
            pstmt.setString(4, dept.getRemark());
            pstmt.executeUpdate();
            DbUtil.release(pstmt, conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //修改科室
    public static void update(Dept dept) {
        try {
            Connection conn = DbUtil.getConnection();
            String sql = "update dept set name = ?, type =?, remark = ? ";
            sql += "where id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, dept.getName());
            pstmt.setInt(2, dept.getType());
            pstmt.setString(3, dept.getRemark());
            pstmt.setInt(4, dept.getId());
            pstmt.executeUpdate();
            DbUtil.release(pstmt, conn);

        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    //删除科室
    public static void delete(Integer id){
        try {
            Connection conn = DbUtil.getConnection();
            String sql = "delete from dept where id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            DbUtil.release(pstmt, conn);

        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    //查找科室
    public static List<Dept> select(Integer id, String name, Integer type){
        try {
            //Connection conn = DbUtil.getConnection();
            String sql = "select id, name, type, remark from dept ";
            sql += "where 1=1 ";
            if (id != null){
                sql += "and id = '" + id + "' ";
            }
            if (name != null){
                sql += "and name = '" + name + "' ";
            }
            if (type != null){
                sql += "and type = '" + type + "' ";
            }
            sql += ";";
            return fillResult(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }


    private static List<Dept> fillResult(String sql) throws SQLException,ClassNotFoundException{
        List<Dept> deptList = new LinkedList<>();
        Connection conn = DbUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            Dept dept = new Dept(rs.getInt("id"), rs.getString("name"),
                    rs.getInt("type"), rs.getString("remark"));
            deptList.add(dept);
        }

        DbUtil.release(pstmt, conn);
        return deptList;
    }

}
