<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<h1><%= "Hello World!" %>
</h1>
<br/>
<a href="categoryServlet?action=listCate">Quản lý danh mục</a>
<a href="productServlet?action=listProduct">Quản lý sản phẩm</a>
</body>
</html>