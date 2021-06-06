<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/6/2021
  Time: 6:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post">
    <table border="1" cellpadding="5">
        <caption>
            <h2>
                Edit Ticket Status
            </h2>
        </caption>
        <tr>
            <th>Name:</th>
            <td>
                <c:out value='${ticket.customer.user.name}'/>
            </td>
        </tr>
        <tr>
            <th>Identity:</th>
            <td>
                <p><c:out value='${ticket.customer.identity}' /></p>
            </td>
        </tr>
        <tr>
            <th>Books:</th>
            <td>
                <c:forEach var="book" items="${ticket.bookList}">
                    <c:out value="${book.name}"/>
                </c:forEach>
            </td>
        </tr>
        <tr>
            <th>Borrow Date:</th>
            <td>
                <c:out value="${ticket.borrowdate}"/>
            </td>
        </tr>
        <tr>
            <th>Due Date:</th>
            <td>
                <c:out value="${ticket.duedate}"/>
            </td>
        </tr>
        <tr>
            <th>Status:</th>
            <td>
                <select name="statuslist" id="statuslist">
                    <option value="${ticket.transactionStatus.id}">${ticket.transactionStatus.name}</option>
                    <c:forEach items="${statuses}" var="status">
                        <c:if test='${ticket.transactionStatus.id != status.id}'>
                            <option value="${status.id}">${status.name}</option>
                        </c:if>
                    </c:forEach>
                </select>
            </td>
        </tr>
        <tr>
            <td colspan="2" align="center">
                <input type="submit" value="Save"/>
            </td>
        </tr>
    </table>
</form>
</body>
</html>
