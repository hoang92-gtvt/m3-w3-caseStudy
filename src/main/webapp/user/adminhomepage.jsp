<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/3/2021
  Time: 2:59 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>User Management Application</title>
</head>
<body>
<center>
    <h1>User Management</h1>
    <h2>
        <a href="/Libraries?action=create">Add New User</a>
    </h2>
</center>
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
                    <a href="/Libraries?action=edit&id=${user.id}">Edit</a>
                    <a href="/Libraries?action=delete&id=${user.id}">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
