<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${product != null ? "Cập nhật sản phẩm" : "Thêm mới sản phẩm"}</title>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css">
    <style>
        body {
            background-color: #f8f9fa;
        }
        h1 {
            margin-bottom: 20px;
            color: #343a40;
        }
        .form-control {
            border-radius: 0.25rem;
        }
        .table {
            border: 1px solid #dee2e6;
            border-radius: 0.25rem;
        }
        .table th {
            background-color: #007bff;
            color: white;
        }
        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }
        .btn-primary:hover {
            background-color: #0056b3;
            border-color: #0056b3;
        }
        .error-message {
            color: red;
            font-weight: bold;
        }
    </style>
</head>
<body>

<div class="container mt-5">
    <form action="<%=request.getContextPath()%>/productServlet" method="post">
        <h1>${product != null ? "Cập nhật sản phẩm" : "Thêm mới sản phẩm"}</h1>

        <table class="table table-bordered">
            <tr>
                <th>Tên sản phẩm</th>
                <td><input type="text" name="name" class="form-control" value="${product != null ? product.name : ''}" required></td>
            </tr>
            <tr>
                <th>Giá sản phẩm</th>
                <td><input type="number" name="price" class="form-control" value="${product != null ? product.price : ''}" required></td>
            </tr>
            <tr>
                <th>Số lượng sản phẩm</th>
                <td><input type="number" name="quantity" class="form-control" value="${product != null ? product.quantity : ''}" required></td>
            </tr>
            <tr>
                <th>Danh mục sản phẩm</th>
                <td>
                    <select name="categoryId" class="form-control" required>
                        <option value="" disabled selected>Chọn danh mục</option>
                        <c:forEach items="${listCate}" var="cate">
                            <option value="${cate.id}"
                                    <c:if test="${product != null && product.category.id != null && product.category.id == cate.id}">selected</c:if>>
                                    ${cate.name}
                            </option>
                        </c:forEach>
                    </select>
                </td>
            </tr>
            <c:if test="${empty listCate}">
                <p class="error-message">Danh sách danh mục không có dữ liệu!</p>
            </c:if>


            <c:if test="${product != null}">
                <input type="hidden" name="id" value="${product.id}">
            </c:if>

            <!-- Submit Button for Add/Edit -->
            <tr>
                <td colspan="2">
                    <input type="submit" name="action" value="${product != null ? 'edit' : 'add'}" class="btn btn-primary">
                </td>
            </tr>
        </table>

        <!-- Hiển thị thông báo lỗi nếu có -->
        <c:if test="${errorMessage != null}">
            <p class="error-message">${errorMessage}</p>
        </c:if>

    </form>
</div>

</body>
</html>

