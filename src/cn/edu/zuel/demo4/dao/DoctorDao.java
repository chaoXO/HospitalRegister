package cn.edu.zuel.demo4.dao;

import cn.edu.zuel.demo4.Util.DbUtil;
import cn.edu.zuel.demo4.model.Doctor;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;


public class DoctorDao {
    //增加数据
    public static boolean insert(Doctor model) {
        try {
            Connection conn = DbUtil.getConnection();
            String sql = "insert into doctor(id, name, sex, dept_id, password)";
            sql += "value(?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, model.getId());
            pstmt.setString(2, model.getName());
            pstmt.setInt(3, model.getSex());
            pstmt.setInt(4, model.getDeptId());
            pstmt.setString(5, model.getPassword());

            pstmt.executeUpdate();
            DbUtil.release(pstmt, conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
//修改数据 通过id值索引去修改其他值
    public static void update(Doctor model) {
        try {
            Connection conn = DbUtil.getConnection();
            String sql = "update doctor set name = ?, sex =?, dept_id = ?, password =?";
            sql += "where id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, model.getName());
            pstmt.setInt(2, model.getSex());
            pstmt.setInt(3, model.getDeptId());
            pstmt.setString(4, model.getPassword());
            pstmt.setInt(5, model.getId());
            pstmt.executeUpdate();
            DbUtil.release(pstmt, conn);

        } catch (Exception e) {
            e.printStackTrace();
        };
    }

//删除数据
    public static void delete(Integer id){
        try {
            Connection conn = DbUtil.getConnection();
            String sql = "delete from doctor where id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            DbUtil.release(pstmt, conn);

        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    //查找数据
    public static List<Doctor> select(Integer id, String name, String password, Integer sex, Integer deptId){
        try {
            Connection conn = DbUtil.getConnection();
            String sql = "select t.id as id, t.name as name, t.sex as sex, ";
            sql += "t.password as password, t.dept_id as dept_id, d.name as dept_name ";
            //as后面是别名,当sql语句涉及两个表的查询时，元素用”表名.变量“ 的表示方法
            sql += "from doctor t ";
            sql += "left join dept d ";
            sql += "on t.dept_id = d.id ";
            sql += "where 1=1 ";
            if (id != null){
                sql += "and t.id = '" + id + "' ";
            }
            if (name != null){
                sql += "and t.name = '" + name + "' ";
            }
            if (password != null){
                sql += "and t.password = '" + password + "' ";
            }
            if (sex != null){
                sql += "and t.sex = '" + sex + "' ";
            }
            if (deptId != null){
                sql += "and t.dept_id = '" + deptId + "' ";
            }
            sql += ";";
            return fillResult(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }


    private static List<Doctor> fillResult(String sql) throws SQLException,ClassNotFoundException{
        List<Doctor> doctors = new LinkedList();
        Connection conn = DbUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            Doctor doctor = new Doctor(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getString("password"),
                    rs.getInt("sex"),
                    rs.getInt("dept_id"),
                    rs.getString("dept_name"));
            doctors.add(doctor);
        }

        DbUtil.release(pstmt, conn);
        return doctors;
    }
}
