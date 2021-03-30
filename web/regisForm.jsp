<%@ page import="java.util.List" %>
<%@ page import="cn.edu.zuel.demo4.model.RegRecord" %>
<%@ page import="cn.edu.zuel.demo4.dao.RegRecordDao" %><%--
  Created by IntelliJ IDEA.
  User: Win10
  Date: 2021/1/7
  Time: 15:53
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <style type="text/css">
        body{
            margin:0px;
            height:100%;
            width:100%;
            overflow: hidden;
            background-size:cover;
            background: url("images/backgroundin.jpg")  fixed center center;
        }
        table{border-collapse:collapse;width: 1000px} /*合并间距*/
        table,table th,table td{border:1px solid #ddd} /*表格带线框,6像素的间距*/
        table th,table td{padding:6px} /*单元格6像素的间距*/
        table th{background-color:#eee} /*表头带背景色*/
        table td{background-color: lightskyblue}

        div{
            font-size: 100px;
        }
    </style>
</head>
<body style="text-align:center;">
<div style="font-size: 100px;">
<%
    out.print("祝"+request.getSession().getAttribute("currentUser")+"早日康复");
%>
</div>
<table border="1" width="1000">
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
        if (request.getAttribute("regRecords")!=null){
        List<RegRecord> regRecords = (List<RegRecord>) request.getAttribute("regRecords");
        for (RegRecord r : regRecords){ %>
    <tr>
        <td class="td"><%out.print(r.getId());  %></td>
        <td class="td"><%out.print(r.getPatientId());  %></td>
        <td class="td"><%out.print(r.getDeptId());  %></td>
        <td class="td"><%out.print(r.getRegTime());  %></td>
        <td class="td"><%out.print(r.getPrice());  %></td>
        <td class="td"><%out.print(r.getPatientName());  %></td>
        <td class="td"><% out.print(r.getDeptName()); %></td>
    </tr>
    <%}}%>
</table>

<form name="regisForm" method="post" action="RegRcordServlet">
    选择挂号科室：
    <select name="dept">
        <option value="10">妇产科</option>
        <option value="11">口腔科</option>
        <option value="12">呼吸科</option>
        <option value="13">儿科</option>
        <option value="14">消化科</option>
    </select>
    <input type="submit" value="挂号">
</form>
</body>
</html>
