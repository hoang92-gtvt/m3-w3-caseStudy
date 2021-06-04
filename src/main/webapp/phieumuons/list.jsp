<%--
  Created by IntelliJ IDEA.
  User: YEN
  Date: 6/2/2021
  Time: 9:57 AM
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Danh sách Phiếu mượn</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">
</head>
<body>
<h1> Danh sách Phiếu mượn cho khách</h1>
<table class="table table-striped table-hover">
    <tr >
        <td >Mã phiếu</td>
        <td >Ngày mượn</td>

        <td >Hạn trả sách</td>
        <td >Tên người mượn</td>
        <td >Tình trạng phiếu mượn</td>


        <td>Edit</td>
        <td>Delete</td>
    </tr>
<%--    <c:forEach items="${bookList}" var="book">--%>
<%--        <tr class="table-primary">--%>
<%--            <td>${book.name}</td>--%>
<%--            <td>${book.description}</td>--%>
<%--            <td>${book.statusBook.name}</td>--%>
<%--            <td>${book.nxb.name}</td>--%>
<%--            <td><c:forEach items="${book.categories}" var="category">--%>
<%--                <span>${category.name}</span>--%>
<%--            </c:forEach>--%>

<%--            </td>--%>
<%--            <td>${book.urlOfImage}</td>--%>

<%--            <td class="table-primary"><a href="/book?action=edit&id=${book.id}">edit</a></td>--%>
<%--            <td class="table-primary"><a href="/book?action=delete&id=${book.id}">delete</a></td>--%>

<%--        </tr>--%>

<%--    </c:forEach>--%>

</table>

<a href="/phieumuon?action=create">Thêm mới sách</a>



</body>
</html>