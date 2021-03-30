<%@ page import="cn.edu.zuel.demo4.model.RegRecord" %>
<%@ page import="java.util.List" %>
<%@ page import="cn.edu.zuel.demo4.model.Patient" %><%--
  Created by IntelliJ IDEA.
  User: Win10
  Date: 2021/1/7
  Time: 15:51
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>科室管理操作</title>
    <style type="text/css">
        body {
            margin: 0px;
            height: 100%;
            width: 100%;
            overflow: hidden;
            background-size: cover;
            background: url("images/backgroundin.jpg") fixed center center;
        }

        table {
            border-collapse: collapse;
            width: 1000px
        }

        /*合并间距*/
        table, table th, table td {
            border: 1px solid #ddd
        }

        /*表格带线框,6像素的间距*/
        table th, table td {
            padding: 6px
        }

        /*单元格6像素的间距*/
        table th {
            background-color: #eee
        }

        /*表头带背景色*/
        table td {
            background-color: lightskyblue
        }

        div {
            font-size: 20px;
        }

        .box{
            width:1400px;
            height:100%;
            min-height:550px;
            border: 1px solid #c0c0c0;
        }

        /**box上*/
        .box-top{
            margin: 0 auto;
            width: auto;
            height:50%;
            min-height:80px;
            border: 1px solid #c0c0c0;
            margin-top: 5px;
            margin-left: 10px;
            margin-right: 10px;
            text-align:center;
            padding:12px;
        }
        /**box中*/
        .box-center{
            margin: 0 auto;
            width: auto;
            height: 50%;
            min-height:400px;
            border: 1px solid #c0c0c0;
            margin-top: 10px;
            margin-left: 10px;
            margin-right: 10px;
            margin-bottom:5px;
            text-align:center;
            padding:12px;
            /* white-space:nowrap;*字体不允许换行 */
        }


        /**box上左*/
        .box-top-left{
            width:300px;
            height:50%;
            min-height:60px;
            float: left;
            text-align:center;
            padding:10px;
            border: 1px solid #c0c0c0;
            /* border-right: 1px solid #c0c0c0; */
        }
        /**box上右*/
        .box-top-right{
            width:1000px;
            height:50%;
            min-height:60px;
            float: left;
            text-align:center;
            margin-left:5px;
            padding:10px;
            border: 1px solid #c0c0c0;
        }



        /**box上左*/
        .box-center-left{
            width:300px;
            height:50%;
            min-height:380px;
            float: left;
            text-align:center;
            padding:10px;
            border: 1px solid #c0c0c0;
            /* border-right: 1px solid #c0c0c0; */
        }
        /**box上右*/
        .box-center-right{
            width:1000px;
            height:50%;
            min-height:380px;
            float: left;
            text-align:center;
            margin-left:5px;
            padding:10px;
            border: 1px solid #c0c0c0;
            overflow:scroll;
        }

    </style>

</head>
<body style="text-align:center;">
<div style="font-size: 100px">
<%
    if(session.getAttribute("logintype")=="doctor"){
        out.print(request.getSession().getAttribute("currentUser")+"医生你好");
%>
</div>
<div class="box">
    <div class="box-top">
        <div style="float: left;" class="box-top-left"> 科室管理操作 </br>
            <form action="DoctorManServlet" method="post">
                修改科室备注<input type="text" name="remark">
                <input type="submit" value="修改/查询"><br/>
                查询病人(本科室)<input type="submit" name="query" value="query"><br/>
                查询所有病人<input type="submit" name="query" value="queryall"><br/>
            </form>
        </div>
        <div style="float: right;" class="box-top-right">

            <table>
                <tr>
                    <th>id</th>
                    <th>姓名</th>
                    <th>性别</th>
                    <th>年龄</th>
                </tr>
                <% if (request.getAttribute("patients") != null) {
                    List<Patient> patients = (List<Patient>) request.getAttribute("patients");
                    for (Patient p : patients) { %>
                <tr>
                    <td class="td"><%out.print(p.getId().toString()); %></td>
                    <td class="td"><%out.print(p.getName()); %></td>
                    <td class="td"><% int a = p.getSex();
                        if (a == 1) {
                            out.print("男");
                        } else {
                            out.print("女");
                        }
                    %></td>
                    <td class="td"><% out.print(p.getAge().toString()); %></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
        </div>
    </div>
<div class="box-hr"></div>
    <div class="box-center">
        <div style="float: left;" class="box-center-left">
            <form action="QueryServlet" method="post">
                查询挂号记录</br>
                挂号id&nbsp<input type="text" name="reg_id"></br>
                科室id&nbsp<input type="text" name="dept_id"></br>
                病人id&nbsp<input type="text" name="patient_id"></br>
                挂号日期<input type="date" name="date"></br>
                挂号价格<select name="price">
                <option value="">所有</option>
                <option value="30">30</option>
                <option value="60">60(急诊)</option>
            </select></br>
                <input type="submit" value="查询"></br>
                注：挂号id和病人id冲突时，优先挂号id
            </form>
        </div>

        <div style="float: right;" class="box-center-right">
            <table>
                <tr>
                    <th>病例id</th>
                    <th>病人id</th>
                    <th>科室id</th>
                    <th>挂号日期</th>
                    <th>挂号费用</th>
                    <th>病人名称</th>
                    <th>科室名称</th>
                </tr>
                <%
                    if (request.getAttribute("regRecords") != null) {
                        List<RegRecord> regRecords = (List<RegRecord>) request.getAttribute("regRecords");
                        for (RegRecord r : regRecords) {
                %>
                <tr>
                    <td class="td"><%out.print(r.getId()); %></td>
                    <td class="td"><%out.print(r.getPatientId()); %></td>
                    <td class="td"><%out.print(r.getDeptId()); %></td>
                    <td class="td"><%out.print(r.getRegTime()); %></td>
                    <td class="td"><%out.print(r.getPrice()); %></td>
                    <td class="td"><%out.print(r.getPatientName()); %></td>
                    <td class="td"><% out.print(r.getDeptName()); %></td>
                </tr>
                <%
                        }
                    }
                %>
            </table>
        </div>
    </div>
</div>
<%}else {
    out.print("祝" + request.getSession().getAttribute("currentUser") + "早日康复");
}
%>
</body>
</html>
