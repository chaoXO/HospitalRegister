package cn.edu.zuel.demo4.servlet;

import cn.edu.zuel.demo4.dao.DeptDao;
import cn.edu.zuel.demo4.dao.DoctorDao;
import cn.edu.zuel.demo4.dao.PatientDao;
import cn.edu.zuel.demo4.model.Dept;
import cn.edu.zuel.demo4.model.Doctor;
import cn.edu.zuel.demo4.model.Patient;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/RegisterServlet")
public class RegisterServlet extends HttpServlet {


    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String dept = req.getParameter("dept");
        String sex = req.getParameter("sex");
        String age = req.getParameter("age");
        //sql 数据库设计中 1-男 2-女 0-未知
        int sex_sql = 0;
        if (sex.equals("男")) {
            sex_sql = 1;
        } else if (sex.equals("女")) {
            sex_sql = 2;
        }
        //dept转换成dept_id
        int dept_id = 0;
        List<Dept> deptList = DeptDao.select(null, null, null);
        for (Dept d : deptList) {
            if (d.getName().equals(dept)) {
                dept_id = d.getId();
            }
        }
        //生成id,先只考虑增加，不考虑删除的情况
        List<Doctor> doctors = DoctorDao.select(null, null, null, null, null);
        int id_d = doctors.size() + 1;
        List<Patient> patientList = PatientDao.select(null, null, null, null, null);
        int id_p = patientList.size() + 1;
        // 注册信息医生没有age，病人没有dept，所以根据dept是否为null来分别处理医生和病人的注册
        if (dept != null) {
            Doctor d = new Doctor(id_d, username, password, sex_sql, dept_id);
            if (DoctorDao.insert(d)) {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                req.setAttribute("error","注册失败");
                req.getRequestDispatcher("failAll.jsp").forward(req, resp);
            }
        } else {
            Patient p = new Patient(id_p, username, sex_sql,new Integer(age), password);
            if (PatientDao.insert(p)) {
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            } else {
                req.setAttribute("error","注册失败");
                req.getRequestDispatcher("failAll.jsp").forward(req, resp);
            }
        }
    }
}
