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
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        Integer dept_id = null;
        Integer patient_id = null;
        Integer reg_id = null;
        BigDecimal price = null;
        String date = null;
        if (req.getParameter("dept_id")!=""){
            dept_id = Integer.valueOf(req.getParameter("dept_id")).intValue();
        }
        if (req.getParameter("patient_id")!=""){
            patient_id = Integer.valueOf(req.getParameter("patient_id")).intValue();
        }
        if (req.getParameter("reg_id")!=""){
            reg_id = Integer.valueOf(req.getParameter("reg_id")).intValue();
        }
        //RegRecordDao提供了dept_id和patient_id的查询，reg_id可以先转换成patient_id查询
        if (req.getParameter("date")!=""){
            date = req.getParameter("date");
        }
        if (req.getParameter("price")!=""){
            price = new BigDecimal(req.getParameter("price"));
        }
        //reg_id转patient_id,
        if (reg_id!=null){
            List<RegRecord> regRecords = RegRecordDao.select(null,null,null);
            for (RegRecord r:regRecords){
                if (r.getId().equals(reg_id)){
                    patient_id = r.getPatientId();
                }
            }
        }
        //执行查询
        List<RegRecord> regRecords = RegRecordDao.select(patient_id,dept_id,price);
        List<RegRecord> regRecords2 = new ArrayList<>();
        //对查询结果进行进一步的删选，把特定日期的结果保留
        if (date!=null){
            for (RegRecord r:regRecords){
                if (r.getRegTime().toString().equals(date)){
                    regRecords2.add(r);
                }

            }
        }else {
            regRecords2 = regRecords;
        }
        //        for (RegRecord r:regRecords){
//            out.print(r.toString());
//            out.print("\n");
//        }
//        out.print(patient_id+"\n");
//        out.print(dept_id+"\n");
//        out.print(price+"\n");
        req.setAttribute("regRecords",regRecords2);
        req.getRequestDispatcher("manageDept.jsp").forward(req, resp);











    }
}
