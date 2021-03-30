package cn.edu.zuel.demo4.service;

import cn.edu.zuel.demo4.dao.DoctorDao;
import cn.edu.zuel.demo4.dao.PatientDao;
import cn.edu.zuel.demo4.dao.RegRecordDao;
import cn.edu.zuel.demo4.model.Doctor;
import cn.edu.zuel.demo4.model.Patient;
import cn.edu.zuel.demo4.model.RegRecord;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

public class DoctorService {

    //医生登录
    public static Doctor login(Integer id, String password){
        Doctor model = null;
        List<Doctor> models = DoctorDao.select(id, password, null, null,null);
        if (models.size() > 0){
            model = models.get(0);
        }
        return model;
    }
    //医生查询(显示病人列表)
    public static void queryPatient(){
        List<Patient> patientList = PatientDao.select(null,null,null,null,null);
        patientList.forEach(patient1 -> System.out.println(patient1) );
    }
    //医生查询(指定时间进行查询统计)
    public static void queryPatient(Date date){
        List<RegRecord> regRecords = RegRecordDao.select(null,null,null);
        List<Date> Datefilter = new ArrayList<Date>();
        Datefilter.add(date);

        regRecords = regRecords.stream().filter((RegRecord s) -> Datefilter.contains(s.getRegTime()))
                .collect(Collectors.toList());

        regRecords.forEach(regRecord -> System.out.println(regRecord) );
    }

}
