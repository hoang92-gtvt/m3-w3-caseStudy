<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/6/2021
  Time: 12:03 AM
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
        <h2>
            <a href="/Libraries?action=createcustomer">Add New Customer</a>
        </h2>
        <h2>
            <a href="/Libraries?action=userlist">View User List</a>
        </h2>
        <tr>
            <th>ID</th>
            <th>Identity</th>
            <th><a href="/users?">Name</a></th>
            <th>Birthday</th>
            <th>Email</th>
            <th>Phone</th>
            <th>Image</th>
            <th>Actions</th>
        </tr>
        <c:forEach var="customer" items="${listCustomer}">
            <tr>
                <td><c:out value="${customer.id}"/></td>
                <td><c:out value="${customer.identity}"/></td>
                <td><c:out value="${customer.user.name}"/></td>
                <td><c:out value="${customer.user.birthday}"/></td>
                <td><c:out value="${customer.user.email}"/></td>
                <td><c:out value="${customer.user.phone}"/></td>
                <td><c:out value="${customer.user.urlOfImg}"/></td>
                <td>
                    <a href="/Libraries?action=createticket&id=${customer.id}">Create borrow ticket</a>
                    <a href="/Libraries?action=updatein4customer&id=${customer.user.id}">Edit</a>
                    <a href="/Libraries?action=&id=${customer.user.id}">Delete</a>
                    <a href="/Libraries?action=viewtickets&id=${customer.id}">View Tickets</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>
</body>
</html>
