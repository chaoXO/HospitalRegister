package cn.edu.zuel.demo4.service;

import cn.edu.zuel.demo4.dao.DoctorDao;
import cn.edu.zuel.demo4.dao.PatientDao;
import cn.edu.zuel.demo4.dao.RegRecordDao;
import cn.edu.zuel.demo4.model.Dept;
import cn.edu.zuel.demo4.model.Doctor;
import cn.edu.zuel.demo4.model.Patient;
import cn.edu.zuel.demo4.model.RegRecord;

import java.math.BigDecimal;
import java.sql.Date;
import java.util.List;

public class PatientService {

    //病人注册
    public static void accountRegister(Integer id, String name, Integer sex, Integer age, String password){
        PatientDao.insert(new Patient(id,name,sex,age,password));
    }
    //病人登录
    public static Patient login(Integer id, String password){
        Patient model = null;
        List<Patient> models = PatientDao.select(id, null, null, null,password);
        if (models.size() > 0){
            model = models.get(0);
        }
        return model;
    }
    //病人挂号
    public static void Register(Patient patient, Dept dept, Date date, BigDecimal price){

        RegRecord regRecord = new RegRecord(null, patient.getId(), dept.getId(), date
                ,price, patient.getName(), dept.getName());
        RegRecordDao.insert(regRecord);
    }


}
