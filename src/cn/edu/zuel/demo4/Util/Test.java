package cn.edu.zuel.demo4.Util;

import cn.edu.zuel.demo4.service.DoctorService;
import cn.edu.zuel.demo4.model.*;
import cn.edu.zuel.demo4.dao.*;

import java.io.*;
import java.math.BigDecimal;

import java.text.SimpleDateFormat;
import java.sql.Date;
import java.util.*;


public class Test {
    public static void main(String[] args) throws ClassNotFoundException, IOException {
        //部门测试
//        Dept dept1 = new Dept(10,"口腔科",0,"拔牙,根管，洗牙");
//        Dept dept2 = new Dept(11,"妇产科",0,"拔牙,根管，洗牙");
//        Dept dept3 = new Dept(12,"呼吸科",0,"肺炎、COPD、肺结核");
//        Dept dept4 = new Dept(13,"儿科",0,"肺炎、COPD、肺结核");
//        Dept dept5 = new Dept(14,"消化科",0,"肺炎、COPD、肺结核");
//        Dept dept6 = new Dept(15,"骨科",0,"肺炎、COPD、肺结核");
//               DeptDao.insert(dept1);
//        DeptDao.insert(dept2);
//        DeptDao.insert(dept3);
//        DeptDao.insert(dept4);
//        DeptDao.insert(dept5);
//        DeptDao.insert(dept6);
//        DeptDao.insert(dept1);
//        DeptDao.update(dept2);
//          DeptDao.delete(15);
//        List<Dept> deptList = DeptDao.select(null,null,null);
//        for(Dept d : deptList){
//            System.out.println(d.getName());
//        }

//        System.out.println("-----------------------");
//        List<Dept> deptList2 = DeptDao.select(null,"呼吸科",null);
//        deptList2.forEach(dept -> System.out.println(dept));
//        System.out.println("-----------------------");
//
//        List<Dept> deptList3 = DeptDao.select(null,null,0);
//        deptList3.forEach(dept -> System.out.println(dept));
//        DeptDao.delete();
        //病人测试
//        Patient patient1 = new Patient(1,"zhangsan",1,18,"123456");
//        Patient patient2 = new Patient(2,"lidan",2,19,"123456");

//        PatientDao.delete(1);
//        PatientDao.update(patient2);
//        PatientDao.insert(patient1);
//        PatientDao.insert(patient2);
//        List<Patient> patientList = PatientDao.select(null,"zhangsan",null,null,"123456");
//        System.out.println(patientList.get(0));
//        patientList.forEach(patient -> System.out.println(patient) );
        //医生测试
//        Doctor doctor1 = new Doctor(1,"zhangsan","123456",1,10);
//        Doctor doctor2 = new Doctor(2,"lisi","123456",1,10);
//        Doctor doctor3 = new Doctor(33,"wangxiaoer","123456",1,10);
//
//        DoctorDao.insert(doctor3);
//        DoctorDao.insert(doctor2);
//        DoctorDao.insert(doctor3);
       // DoctorDao.delete(1);
        //DoctorDao.update(doctor2);
        //List<Doctor>
//        List<Doctor> doctors = DoctorDao.select(null,null,null,null,null);
//        System.out.println(doctors.size());
//        doctors.forEach(doctor -> System.out.println(doctor));
        //病例录入测试
//        RegRecord regRecord1 = new RegRecord(1,1,10,new Date(2020-11-18)
//                ,new BigDecimal(250),"lidan","呼吸科");
//        RegRecord regRecord2 = new RegRecord(2,2,14,new Date(2020-11-19)
//                ,new BigDecimal(360),"lidan","呼吸科");
//        RegRecordDao.insert(regRecord1);
//        RegRecordDao.insert(regRecord2);
        //RegRecordDao.update(regRecord2);
        //RegRecordDao.delete(1);

//        Date d = new Date(2021-01-06);



//
//        List<RegRecord> regRecords = RegRecordDao.select(null,null,null);
//        regRecords.forEach(regRecord -> System.out.println(regRecord) );

//        DoctorService.queryPatient();
//        System.out.println("---------------------------------");
//        DoctorService.queryPatient(new Date(2020-1900,11,18));

//        SimpleDateFormat t1 = new SimpleDateFormat("yyyy-MM-dd");
//        SimpleDateFormat t2 = new SimpleDateFormat("HH:mm:ss");
//        System.out.println(t2.format(new Date()));
//        String t = t2.format(new Date()).substring(0,2);
//        System.out.println(t);
//        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
//        RegRecord regRecord1 = new RegRecord(5,1,10,currentDate
//                ,new BigDecimal(250),"lidan","呼吸科");
//        System.out.println(currentDate);
//        int timeT = Integer.valueOf(t).intValue();
//        System.out.println(timeT);






    }
}
