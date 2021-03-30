package cn.edu.zuel.demo4.servlet;

import cn.edu.zuel.demo4.dao.DoctorDao;
import cn.edu.zuel.demo4.dao.PatientDao;
import cn.edu.zuel.demo4.model.Doctor;
import cn.edu.zuel.demo4.model.Patient;

import javax.servlet.Filter;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet{

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getParameter("username");
        String password = req.getParameter("password");
        String type = req.getParameter("type");
        if (username==null||password==null){
            req.setAttribute("error","登录失败");
            req.getRequestDispatcher("failAll.jsp").forward(req, resp);
        }
        if (type.equals("doctor")){
            List<Doctor> doctors = DoctorDao.select(null,username,password,null,null);
            if (doctors.size()>0){
                req.getSession().setAttribute("logintype","doctor");
                req.getSession().setAttribute("currentUser",doctors.get(0).getName());
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }else {
                req.setAttribute("error","登录失败");
                req.getRequestDispatcher("failAll.jsp").forward(req, resp);
            }

        } else {
            List<Patient> patients = PatientDao.select(null,username,null,null,password);
            if (patients.size()>0){
                req.getSession().setAttribute("logintype","patient");
                req.getSession().setAttribute("currentUser",patients.get(0).getName());
                req.getRequestDispatcher("index.jsp").forward(req, resp);
            }else {
                req.setAttribute("error","登录失败");
                req.getRequestDispatcher("failAll.jsp").forward(req, resp);
            }
        }
    }
}
