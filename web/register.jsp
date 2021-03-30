
<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>


<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <title>注册表单</title>
    <style type="text/css">
        body{
            margin:0px;
            height:100%;
            width:100%;
            overflow: hidden;
            background-size:cover;
            background: url("images/backgroundin.jpg")  fixed center center;
        }
        .container{
            background-color: rgba(215,223,226,0.7);
            position: absolute;
            top: 50%;
            left: 50%;
            transform: translate(-50%,-50%);
            text-align: center;
            height: 250px;
            width: 350px;
            border: 2px;
            border-radius: 20px;
            font-size:80%;
        }
       input{
           height: 30px;
           width: 200px;
       }
    </style>
</head>

<body style="text-align:center;">
<div class="container" >
<%
    String temp_id = request.getParameter("type");

%>
<% if (temp_id.equals("doctor")){
    out.println("医生注册");
%>
<form class="formin" action="RegisterServlet" method="post" accept-charset="utf-8" _charset="utf-8">
    姓名：<input type="text" name="username"><br/>
    密码：<input type="password" name="password"><br/>
    性别：<input type="text" name="sex" placeholder="请输入男或者女"><br/>
    科室：<input type="text" name="dept"><br/>
    <input type="submit" value="注册">
</form>
<%}else{
    out.println("病人注册");
%>
<form class="formin" action="RegisterServlet" method="post" accept-charset="utf-8" _charset="utf-8">
    姓名：<input type="text" name="username"><br/>
    密码：<input type="password" name="password"><br/>
    性别：<input type="text" name="sex" placeholder="请输入男或者女"><br/>
    年龄：<input type="text" name="age"><br/>
    <input type="submit" value="注册">
</form>
<%}%>
</div>
</body>
</html>