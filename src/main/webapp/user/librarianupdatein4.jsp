<%--
  Created by IntelliJ IDEA.
  User: admin
  Date: 6/5/2021
  Time: 2:53 PM
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
    <table border="1" cellpadding="5">
        <caption>
            <h2>
                Edit Customer Information
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
                <input type="text" name="email" size="15" value="<c:out value='${user.email}' />" disabled/>
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
