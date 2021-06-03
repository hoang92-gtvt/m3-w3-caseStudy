<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/2/2021
  Time: 3:57 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<p>
    <c:if test='${requestScope["message"] != null}'>
        <span class="message">${requestScope["message"]}</span>
    </c:if>
</p>
<form method="post">
    <input type="text" name="username" id="username"/>
    <input type="password" name="pass" id="pass"/>
    <input type="submit" value="Sign In">
</form>
</body>
</html>
