package cn.edu.zuel.demo4.dao;

import cn.edu.zuel.demo4.Util.DbUtil;
import cn.edu.zuel.demo4.model.Patient;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

//增加病人
public class PatientDao {
    public static boolean insert(Patient model) {
        try {
            Connection conn = DbUtil.getConnection();
            String sql = "insert into patient(id, name, sex, age, password)";
            sql += "value(?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, model.getId());
            pstmt.setString(2, model.getName());
            pstmt.setInt(3, model.getSex());
            pstmt.setInt(4, model.getAge());
            pstmt.setString(5, model.getPassword());

            pstmt.executeUpdate();
            DbUtil.release(pstmt, conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    //修改数据
    public static void update(Patient model) {
        try {
            Connection conn = DbUtil.getConnection();
            String sql = "update patient set name = ?, sex =?, age = ?, password =?";
            sql += "where id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, model.getName());
            pstmt.setInt(2, model.getSex());
            pstmt.setInt(3, model.getAge());
            pstmt.setString(4, model.getPassword());
            pstmt.setInt(5, model.getId());
            pstmt.executeUpdate();
            DbUtil.release(pstmt, conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //删除数据
    public static void delete(Integer id){
        try {
            Connection conn = DbUtil.getConnection();
            String sql = "delete from patient where id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);

            pstmt.executeUpdate();
            DbUtil.release(pstmt, conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    //查询数据
    public static List<Patient> select(Integer id, String name, Integer sex, Integer age, String password){
        try {
            Connection conn = DbUtil.getConnection();
            String sql = "select id, name, sex, age, password from patient ";
            sql += "where 1=1 ";
            if (id != null){
                sql += "and id = '" + id + "' ";
            }
            if (name != null){
                sql += "and name = '" + name + "' ";
            }
            if (sex != null){
                sql += "and sex = '" + sex + "' ";
            }
            if (age != null){
                sql += "and type = '" + age + "' ";
            }
            if (password != null){
                sql += "and password = '" + password + "' ";
            }
            sql += ";";
            return fillResult(sql);
        } catch (Exception e) {
            e.printStackTrace();
            return new LinkedList<>();
        }
    }


    private static List<Patient> fillResult(String sql) throws SQLException,ClassNotFoundException{
        List<Patient> patients = new LinkedList();
        Connection conn = DbUtil.getConnection();
        PreparedStatement pstmt = conn.prepareStatement(sql);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()){
            Patient patient = new Patient(rs.getInt("id"),
                    rs.getString("name"),
                    rs.getInt("sex"),
                    rs.getInt("age"),
                    rs.getString("password"));
            patients.add(patient);
        }

        DbUtil.release(pstmt, conn);
        return patients;
    }
}
