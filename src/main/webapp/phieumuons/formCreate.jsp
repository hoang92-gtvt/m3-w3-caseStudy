<%--
  Created by IntelliJ IDEA.
  User: YEN
  Date: 6/3/2021
  Time: 11:46 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Form Tao moi</title>

    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-+0n0xVW2eSR5OomGNYDnhzAbDsOXxcvSN1TPprVMTNDbiYZCxYbOOl7+AMvyTG2x" crossorigin="anonymous">

</head>
<body>
<h1>FORM CREAT NEW PHIEU MUON</h1>
<a href="/phieumuon?action=home">Quay về trang phiếu mượn</a>
<br>
<form class="mb-3" method="post">
    <div class="mb-3">
        <label  class="form-label">Mã Phiếu</label>
        <input type="text" class="form-control" name="identity" placeholder="PM000">
    </div>


    <div class="mb-3">
        <label  class="form-label">Ngày Mượn</label>
        <input type="text" class="form-control" name="date" placeholder="2021-06-01">
    </div>

    <div class="mb-3">
        <label  class="form-label">Ngày Đáo Hạn</label>
        <input type="text" class="form-control" name="duedate" placeholder="2021-09-01">
    </div>

   <div class="mb-3">
    <label  class="form-label">Người mượn</label>
       <br>
       <select name="user_id">
       <c:forEach items="${userList}" var="user">
           <option value="${user.id}"> ${user.userName} </option>
       </c:forEach>
        </select>

   </div>


    <div class="mb-3">
        <label  class="form-label">Tình trạng phiếu mượn </label>
        <br>

<%--        <c:forEach items="${statusPMList}" var="status">--%>
            <input  type="radio" name="statusPM_id" value="1">
            <label>Mới</label>

<%--        </c:forEach>--%>



    </div>

    <div class="mb-3">
        <label  class="form-label">Lựa chọn sách mượn </label>
        <br>
        <select  name="books" multiple>
        <c:forEach items="${bookList}" var="book">
            <option  value="${book.id}">${book.name}</option>
        </c:forEach>
        </select>


    </div>



<%--    <div class="mb-3">--%>
<%--        <label for="exampleFormControlTextarea1" class="form-label">Example textarea</label>--%>
<%--        <textarea class="form-control" id="exampleFormControlTextarea1" rows="3"></textarea>--%>
<%--    </div>--%>
    <button type="submit">Create New Phieu Muon </button>
</form>



</body>
</html>
<style>
    .form-label {
        font-weight: bolder;
        font-size: 110% ;
    }
</style>
