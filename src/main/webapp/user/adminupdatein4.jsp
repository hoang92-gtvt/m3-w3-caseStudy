<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/3/2021
  Time: 7:49 PM
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
                Edit User Information
            </h2>
        </caption>
        <tr>
            <th>Name:</th>
            <td>
                <input type="text" name="name" size="45" value="<c:out value='${user.name}' />"/>
            </td>
            <td>
                <c:if test='${requestScope["namemessage"] != null}'>
                    <span class="namemessage">${requestScope["namemessage"]}</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <th>Birth:</th>
            <td>
                <input type="text" name="birthday" size="45" value="<c:out value='${user.birthday}' />"/>
            </td>
            <td>
                <c:if test='${requestScope["birthdaymessage"] != null}'>
                    <span class="birthdaymessage">${requestScope["birthdaymessage"]}</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <th>Email:</th>
            <td>
                <input type="text" name="email" size="15" value="<c:out value='${user.email}' />"/>
            </td>
            <td>
                <c:if test='${requestScope["emailmessage"] != null}'>
                    <span class="emailmessage">${requestScope["emailmessage"]}</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <th>Phone:</th>
            <td>
                <input type="text" name="phone" size="15" value="<c:out value='${user.phone}' />"/>
            </td>
            <td>
                <c:if test='${requestScope["phonemessage"] != null}'>
                    <span class="phonemessage">${requestScope["phonemessage"]}</span>
                </c:if>
            </td>
        </tr>
        <tr>
            <th>urlOfImage:</th>
            <td>
                <input type="text" name="urlOfImg" size="15" value="<c:out value='${user.urlOfImg}' />"/>
            </td>
            <td></td>
        </tr>
        <tr>
            <th><label for="role">Role:</label></th>
            <td>
                <select name="role" id="role">
                    <option value="${user.role.id}">${user.role.name}</option>
                    <c:forEach items="${roles}" var="role">
                        <option value="${role.id}">${role.name}</option>
                    </c:forEach>
                </select>
            </td>
            <td></td>
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
