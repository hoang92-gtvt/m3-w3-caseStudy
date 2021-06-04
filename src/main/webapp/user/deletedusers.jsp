<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/4/2021
  Time: 12:47 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div align="center">
    <table border="1" cellpadding="5">
        <caption><h2>List of Users</h2></caption>
        <tr>
            <th>ID</th>
            <th><a href="/users?">Name</a></th>
            <th>Birthday</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Image</th>
            <th>Role</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="user" items="${listUser}">
            <tr>
                <td><c:out value="${user.id}"/></td>
                <td><c:out value="${user.name}"/></td>
                <td><c:out value="${user.birthday}"/></td>
                <td><c:out value="${user.email}"/></td>
                <td><c:out value="${user.phone}"/></td>
                <td><c:out value="${user.urlOfImg}"/></td>
                <td><c:out value="${user.role.name}"/></td>
                <td>
                    <a href="/Libraries?action=restore&id=${user.id}">Restore User</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
