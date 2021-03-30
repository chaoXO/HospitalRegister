package cn.edu.zuel.demo4.servlet;

import cn.edu.zuel.demo4.dao.PatientDao;
import cn.edu.zuel.demo4.dao.RegRecordDao;
import cn.edu.zuel.demo4.model.Patient;
import cn.edu.zuel.demo4.model.RegRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/RegRcordServlet")
public class RegRcordServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String username = req.getSession().getAttribute("currentUser").toString();
        String dept_id = req.getParameter("dept");
//      out.print(username);
//      out.print(dept_id);
        //获取当前用户的住院id
        List<Patient> patientList = PatientDao.select(null,username,null,null,null);
        Patient p = patientList.get(0);
        String patient_id = p.getId()+"";
        //获取挂号id
        List<RegRecord> regs = RegRecordDao.select(null,null,null);
        int id = regs.size()+1;
        //生成当前时间，以及不同时间的挂号费用，早上8点到下午5点为30元，其他时间60元
        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        SimpleDateFormat t2 = new SimpleDateFormat("HH:mm:ss");
        String t3 = t2.format(new Date()).substring(0,2);
        int timeT = Integer.valueOf(t3).intValue();
        BigDecimal price = new BigDecimal(60);
        if (8<timeT&&timeT<17){
            price = new BigDecimal(30);
        }
        //把病例保存
        RegRecord r = new RegRecord(id,Integer.valueOf(patient_id).intValue(),
               Integer.valueOf(dept_id).intValue(),currentDate,price);
        if (RegRecordDao.insert(r)){
            req.getRequestDispatcher("index.jsp").forward(req, resp);
        }else {
            req.setAttribute("error","挂号失败");
            req.getRequestDispatcher("fail.jsp").forward(req, resp);
        }

    }
}
