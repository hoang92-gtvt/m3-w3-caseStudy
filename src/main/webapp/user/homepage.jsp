<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/2/2021
  Time: 5:37 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %><html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <a href="/Libraries?action=updatein4&id=${user.id}">Update Information</a>
    <a href="/Libraries?action=signin">Sign Out</a>
    <a href="/Libraries?action=passwordchange&id=${user.id}">Change Password</a>
</div>
</body>
</html>
