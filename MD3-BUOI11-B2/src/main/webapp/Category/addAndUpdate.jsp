<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>${category != null ? "Cập nhật sản phẩm" : "Thêm mới sản phẩm"}</title>
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
    <form action="<%=request.getContextPath()%>/categoryServlet" method="post">
        <h1>${category != null ? "Cập nhật sản phẩm" : "Thêm mới sản phẩm"}</h1>

        <table class="table table-bordered">
            <tr>
                <th>Tên sản phẩm</th>
                <td><input type="text" name="name" class="form-control" value="${category != null ? category.name : ''}" required></td>
            </tr>
            <tr>
                <th>Trạng thái danh  mục</th>
                <td colspan="2">
                    <div class="form-check">
                        <input type="radio" name="status" value="true" class="form-check-input" <c:if test="${category != null && category.status}">checked</c:if> required>
                        <label class="form-check-label">Hoạt động</label>
                    </div>
                    <div class="form-check">
                        <input type="radio" name="status" value="false" class="form-check-input" <c:if test="${category != null && !category.status}">checked</c:if> required>
                        <label class="form-check-label">Không hoạt động</label>
                    </div>
                </td>
            </tr>

            <c:if test="${category != null}">
                <input type="hidden" name="id" value="${category.id}">
            </c:if>
            <!-- Submit Button for Add/Edit -->

            <tr>
                <td colspan="2">
                    <input type="submit" name="action" value="${category != null ? 'edit' : 'add'}" class="btn btn-primary">
                </td>
            </tr>
        </table>

        <!-- Hiển thị thông báo lỗi nếu có -->

    </form>
</div>

</body>
</html>

