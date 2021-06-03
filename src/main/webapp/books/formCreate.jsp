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
<h1>FORM CREAT NEW BOOK</h1>
<form class="row g-3" method="post">
    <div class="col-auto">
        <label  class="visually-hidden">Tên Sách</label>
        <input type="text" class="form-control-plaintext" name="name" value="Tên cuốn sách">
    </div>
    <br>

    <div class="col-auto">
        <label  class="visually-hidden">Mô tả chung</label>
        <input type="text" class="form-control-plaintext" name="description" value="Giới thiệu chung về sách">
    </div>
    <br>

    <div class="col-auto">
        <label  class="visually-hidden">Tình trạng sách</label>
        <input type="text" class="form-control-plaintext" name="status_id" value="Lựa chọn tình trạng sách">
    </div>
    <br>

    <div class="col-auto">
        <label  class="visually-hidden">Nhà xuất bản</label>
        <input type="text" class="form-control-plaintext" name="status_id" value="Nhập tình trạn sách theo id">
    </div>
    <br>


    <div class="col-auto">
        <label  class="visually-hidden">Tên Sách</label>
        <input type="text" class="form-control-plaintext" name="name" value="email@example.com">
    </div>
    <br>

    <div class="col-auto">
        <label class="visually-hidden">Password</label>
        <input type="password" class="form-control" name="discription" placeholder="Password">
    </div>
    <br>
    <div class="col-auto">
        <button type="submit" class="btn btn-primary mb-3">Confirm identity</button>
    </div>
</form>


</body>
</html>
