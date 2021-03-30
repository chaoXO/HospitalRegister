package cn.edu.zuel.demo4.servlet;

import cn.edu.zuel.demo4.dao.DeptDao;
import cn.edu.zuel.demo4.dao.DoctorDao;
import cn.edu.zuel.demo4.dao.PatientDao;
import cn.edu.zuel.demo4.dao.RegRecordDao;
import cn.edu.zuel.demo4.model.Dept;
import cn.edu.zuel.demo4.model.Doctor;
import cn.edu.zuel.demo4.model.Patient;
import cn.edu.zuel.demo4.model.RegRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.LinkedList;
import java.util.List;

@WebServlet("/DoctorManServlet")
public class DoctorManServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        //判断是哪个医生在查询
        String remark = req.getParameter("remark");
        String username = req.getSession().getAttribute("currentUser").toString();
        String query = req.getParameter("query");
        //先找出医生的科室，再从科室里找出dept_id,再从挂号记录里找出所有dept_id符合的病人
        // 再去patient找出所有该dept_id下的病人
        List<Patient> patientList = PatientDao.select(null,null,null,null,null);
        int dept_id = 0;
        if (query!=null&&query.equals("query")) {
            List<Doctor> doctorList = DoctorDao.select(null, username, null, null, null);
            for (Doctor d : doctorList) {
                if (d.getName().equals(username)) {
                    dept_id = d.getDeptId();
                }
            }
            List<RegRecord> regRecords = RegRecordDao.select(null, dept_id,null);
            for (RegRecord r : regRecords) {
                if (r.getDeptId() != dept_id) {
                    regRecords.remove(r);
                }
            }
            List<Patient> patients = new LinkedList<>();
            for (Patient p : patientList)
                for (RegRecord r : regRecords) {
                    if (r.getPatientId().equals(p.getId())&&!patients.contains(p)) {
                        patients.add(p);
                    }
                }
            req.setAttribute("patients",patients);
            req.getRequestDispatcher("manageDept.jsp").forward(req, resp);
        }else if(query!=null&&query.equals("queryall")){
            req.setAttribute("patients",patientList);
            req.getRequestDispatcher("manageDept.jsp").forward(req, resp);
        }else {
            //修改科室remark
            List<Doctor> doctorList = DoctorDao.select(null, username, null, null, null);
            dept_id = doctorList.get(0).getDeptId();
            List<Dept> deptList = DeptDao.select(dept_id,null,null);
            Dept x = new Dept(deptList.get(0).getId(),deptList.get(0).getName(),deptList.get(0).getType(),remark);
            DeptDao.update(x);
            out.print("修改成功");
        }
    }
}
