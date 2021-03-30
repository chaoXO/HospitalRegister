package cn.edu.zuel.demo4.dao;

import cn.edu.zuel.demo4.Util.DbUtil;
import cn.edu.zuel.demo4.model.*;

import java.math.BigDecimal;
import java.util.*;
import java.sql.*;
import java.sql.Date;


public class RegRecordDao {

    //增加
    public static boolean insert(RegRecord model) {

        try {
            Connection conn = DbUtil.getConnection();
            String sql = "insert into reg_record(id, patient_id, dept_id, reg_time, price)";
            sql += "value(?,?,?,?,?);";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, model.getId());
            pstmt.setInt(2, model.getPatientId());
            pstmt.setInt(3, model.getDeptId());
            pstmt.setDate(4, model.getRegTime());
            pstmt.setBigDecimal(5, model.getPrice());
            pstmt.executeUpdate();
            DbUtil.release(pstmt, conn);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return true;
    }
    //删除
    public static void delete(Integer id){
        try {
            Connection conn = DbUtil.getConnection();
            String sql = "delete from reg_record where id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            pstmt.executeUpdate();
            DbUtil.release(pstmt, conn);

        } catch (Exception e) {
            e.printStackTrace();
        };
    }

    //修改
    public static void update(RegRecord regRecord) {
        try {
            Connection conn = DbUtil.getConnection();
            String sql = "update reg_record set patient_id = ?, dept_id = ?, reg_time = ?, price = ? ";
            sql += "where id = ?;";
            PreparedStatement pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1,regRecord.getPatientId());
            pstmt.setInt(2, regRecord.getDeptId());
            pstmt.setDate(3, regRecord.getRegTime());
            pstmt.setBigDecimal(4, regRecord.getPrice());
            pstmt.setInt(5, regRecord.getId());
            pstmt.executeUpdate();
            DbUtil.release(pstmt, conn);

        } catch (Exception e) {
            e.printStackTrace();
        };
    }
    //查询
    public static List<RegRecord> select(Integer patientId, Integer deptId, BigDecimal price){
        StringBuffer sql=new StringBuffer();
        sql.append("select r.id as id, r.patient_id as patient_id, r.dept_id as dept_id, ")
                .append("p.name as patient_name, d.name as dept_name, ")
                .append("r.reg_time as reg_time, r.price as price ")
                .append("from reg_record r ")
                .append("left join patient p on r.patient_id = p.id ")
                .append("left join dept d on r.dept_id = d.id ")
                .append("where 1=1 ");
        if(patientId!=null){
            sql.append("and p.id= ").append(patientId);
        }
        if(deptId!=null){
            sql.append("and d.id= ").append(deptId);
        }
        if (price!=null){
            sql.append("and r.price= ").append(price);
        }
        try{
            return fillRegRecord(sql.toString());
        }catch(Exception e){
            e.printStackTrace();
            return new LinkedList<RegRecord>();
        }
    }
    private static List<RegRecord> fillRegRecord(String sql) throws SQLException, ClassNotFoundException {
        List<RegRecord> regRecords=new LinkedList<RegRecord>();
        Connection conn= DbUtil.getConnection();
        PreparedStatement pstmt=conn.prepareStatement(sql);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            RegRecord regRecord=new RegRecord(rs.getInt("id"),
                    rs.getInt("patient_id"),
                    rs.getInt("dept_id"),
                    rs.getDate("reg_time"),
                    rs.getBigDecimal("price"),
                    rs.getString("patient_name"),
                    rs.getString("dept_name"));
            regRecords.add(regRecord);

        }
        DbUtil.release(pstmt,conn);
        return regRecords;
    }





}

