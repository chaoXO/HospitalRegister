<%--
  Created by IntelliJ IDEA.
  User: Win10
  Date: 2021/1/7
  Time: 14:59
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
            background: url("images/background.jpg")  fixed center center;
        }
    </style>
</head>
<body style="font-size:100px;">
   <%
       out.print(request.getAttribute("error"));
   %>
</body>
</html>
