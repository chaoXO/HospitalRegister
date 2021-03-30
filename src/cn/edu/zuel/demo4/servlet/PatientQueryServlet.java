package cn.edu.zuel.demo4.servlet;

import cn.edu.zuel.demo4.dao.RegRecordDao;
import cn.edu.zuel.demo4.model.RegRecord;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet("/PatientQueryServlet")
public class PatientQueryServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        //病人查询
        out.print(req.getSession().getAttribute("logintype"));
        int patient_id = 0;

        String username = req.getSession().getAttribute("currentUser")+"";
        List<RegRecord> regRecords = RegRecordDao.select(null,null,null);
        for (RegRecord r:regRecords){
            if (r.getPatientName().equals(username)){
                patient_id = r.getPatientId();
             }
        }
        List<RegRecord> regRecords2 = RegRecordDao.select(patient_id,null,null);

        req.setAttribute("regRecords",regRecords2);

        req.getRequestDispatcher("regisForm.jsp").forward(req, resp);
    }
}
