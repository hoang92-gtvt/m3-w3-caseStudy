<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/3/2021
  Time: 5:57 PM
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
</center>
<div align="center">
    <form method="post">
        <table border="1" cellpadding="5">
            <caption>
                <h2>Add New User</h2>
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
                <th>Image:</th>
                <td>
                    <input type="text" name="img" id="img" size="15" value="<c:out value='${user.urlOfImg}' />"/>
                </td>
                <td></td>
            </tr>
            <tr>
                <th>UserName:</th>
                <td>
                    <input type="text" name="userName" id="userName" size="45" value="<c:out value='${user.userName}' />"/>
                </td>
                <td>
                    <c:if test='${requestScope["userNamemessage"] != null}'>
                        <span class="userNamemessage">${requestScope["userNamemessage"]}</span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <th>Password:</th>
                <td>
                    <input type="text" name="Pass" id="Pass" size="45" value="<c:out value='${user.pass}' />"/>
                </td>
                <td>
                    <c:if test='${requestScope["passmessage"] != null}'>
                        <span class="passmessage">${requestScope["passmessage"]}</span>
                    </c:if>
                </td>
            </tr>
            <tr>
                <th><label for="role">Role:</label></th>
                <td>
                    <select name="role" id="role">
                        <c:forEach items="${roles}" var="role">
                            <option value="${role.id}">${role.name}</option>
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
</div>
</body>
</html>
