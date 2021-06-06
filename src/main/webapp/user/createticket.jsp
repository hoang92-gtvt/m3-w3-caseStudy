<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/5/2021
  Time: 9:51 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<H1>Create Borrow Ticket</H1>
<form method="post">
    <input type="text" name="brrwdate" placeholder="brrwdate" value="<c:out value='${ticket.borrowdate}' />">
    <c:if test='${requestScope["brrwdatemessage"] != null}'>
    <span class="brrwdatemessage">${requestScope["brrwdatemessage"]}</span>
    </c:if>
    <br/>
    <input type="text" name="duedate" placeholder="duedate" value="<c:out value='${ticket.duedate}' />">
    <c:if test='${requestScope["duedatemessage"] != null}'>
    <span class="duedatemessage">${requestScope["duedatemessage"]}</span>
    </c:if>
    <br/>
    <select name="books" id="books" multiple>
        <c:forEach items="${books}" var="book">
            <option value="${book.id}">${book.name}</option>
        </c:forEach>
    </select>
    <br/>
    <input value="Create Ticket" type="submit">
</body>
</html>
