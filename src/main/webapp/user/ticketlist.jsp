<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/6/2021
  Time: 2:13 AM
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
    <form method="post">
    <table border="1" cellpadding="5">
        <caption><h2>Ticket Page</h2></caption>
        <tr>
            <th>ID</th>
            <th>Identity</th>
            <th><a href="/users?">Name</a></th>
            <th>Books</th>
            <th>Borrow Date</th>
            <th>Due Date</th>
            <th>Status</th>
            <th>Action</th>
        </tr>
        <c:forEach var="ticket" items="${tickets}">
            <tr>
                <td><c:out value="${ticket.id}"/></td>
                <td><c:out value="${ticket.customer.identity}"/></td>
                <td><c:out value="${ticket.customer.user.name}"/></td>
                <td>
                <c:forEach var="book" items="${ticket.bookList}">
                    <c:out value="${book.name}"/>
                </c:forEach>
                </td>
                <td><c:out value="${ticket.borrowdate}"/></td>
                <td><c:out value="${ticket.duedate}"/></td>
                <td><c:out value="${ticket.transactionStatus.name}"/></td>
                <td>
                    <c:if test='${ticket.transactionStatus.id != 3}'>
                        <a href="/Libraries?action=updatestatus&id=${ticket.id}">Update Status</a>
                    </c:if>
                </td>
            </tr>
        </c:forEach>
    </table>
    </form>
</div>
</body>
</html>
