<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/2/2021
  Time: 9:17 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
    <input type="password" name="oldpassword" id="oldpassword"/>
    <input type="password" name="newpassword" id="newpassword"/>
    <input type="submit" value="Change Password">
</form>
</body>
</html>
