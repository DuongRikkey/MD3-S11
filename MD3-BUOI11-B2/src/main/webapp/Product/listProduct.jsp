<%--
  Created by IntelliJ IDEA.
  User: Admin
  Date: 9/29/2024
  Time: 10:26 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>Danh sách sản phẩm</title>
  <style>
    table {
      width: 100%;
      border-collapse: collapse;
    }
    th, td {
      border: 1px solid #ddd;
      padding: 8px;
      text-align: left;
    }
    img {
      width: 100px;
      height: 100px;
    }
    .bt1 {
      background-color: red;
    }
    .bt2 {
      background-color: aqua;
    }
    .search-container {
      margin-bottom: 20px;
    }
    .search-container input[type="text"] {
      padding: 5px;
      width: 300px;
    }
    .search-container button {
      padding: 5px 10px;
    }
  </style>
</head>
<body>

<!-- Search Form -->
<!-- Search Form -->
<div class="search-container">
  <form action="<%=request.getContextPath()%>/productServlet" method="get">
    <input type="text" name="search" placeholder="Tìm kiếm sản phẩm..." value="${param.search}">
    <input type="hidden" name="action" value="search">
    <!-- Input ẩn cho hành động -->

    <input type="hidden" name="action" value="search">
    <button type="submit">Tìm kiếm</button>
  </form>
</div>


<%--<p><a href="<%=request.getContextPath()%>/Product/addAndUpdate.jsp?action=add">Thêm mới danh mục</a></p>--%>
<p><a href="productServlet?action=add">Thêm mới sản phẩm</a></p>

<h1>Danh sách sản phẩm</h1>
<table>

  <tr>
    <th>STT</th>
    <th>Tên sản phẩm</th>
    <th>Giá sản phẩm</th>
    <th>Số lượng</th>
    <th> Danh mục </th>
    <th colspan="2">Action</th>
  </tr>
  <c:forEach items="${listProduct}" var= "pro">
    <tr>
      <td>${pro.id}</td>
      <td>${pro.name}</td>
      <td>${pro.price}</td>
      <td>${pro.quantity}</td>
      <td>${pro.category.name}</td>

      <td><a href="<%=request.getContextPath()%>/productServlet?action=edit&id=${pro.id}" class="bt1">Edit</a></td>
      <td><a href="<%=request.getContextPath()%>/productServlet?action=delete&id=${pro.id}" class="bt2" onclick="return confirm('Bạn có chắc xóa không?')">Delete</a></td>
    </tr>
  </c:forEach>
</table>

</body>
</html>

