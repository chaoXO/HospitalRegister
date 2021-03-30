<%--
  Created by IntelliJ IDEA.
  User: Win10
  Date: 2021/1/1
  Time: 14:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
  <title>主页</title>
  <style type="text/css">
    body{
      margin:0px;
      height:100%;
      width:100%;
      overflow: hidden;
      background-size:cover;
      background: url("images/backgroundin.jpg")  fixed center center;
    }
    div{
      font-size:100px ;
    }
  </style>
</head>
<body style="text-align:center;">
<%
  if(session.getAttribute("logintype")==null){
    request.getRequestDispatcher("login.jsp").forward(request, response);
  }
%>
<div>
<%
  if(session.getAttribute("logintype")=="doctor"){
    out.print(request.getSession().getAttribute("currentUser")+"医生你好");
%>
<form name="form" method="post" action="manageDept.jsp">

  <input type="submit" value="科室管理" style="width:100px;height:50px;">

</form>
</div>
<div>
  <%}else{
    out.print("祝"+request.getSession().getAttribute("currentUser")+"早日康复");
 %>
<form name="form" method="post" action="PatientQueryServlet">

  <input type="submit" value="挂号" style="width:100px;height:50px;">

</form>
<%}%>
</div>
</body>
</html>
