package rikkey.academy.controller;

import rikkey.academy.model.Category;
import rikkey.academy.model.Product;
import rikkey.academy.service.IGenericDesign;
import rikkey.academy.service.unit.CategoryImpl;
import rikkey.academy.service.unit.ProductImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "productServlet", value = "/productServlet")
public class ProductServlet extends HttpServlet {
    private static IGenericDesign  productImpl =new ProductImpl();
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "listProduct":
                    req.setAttribute("listProduct", ProductImpl.productList);

                    req.getRequestDispatcher("/Product/listProduct.jsp").forward(req, resp);
                    break;
               case "delete":
                   Integer id = Integer.parseInt(req.getParameter("id"));
                   productImpl.remove(id);
                   resp.sendRedirect(req.getContextPath() + "/productServlet?action=listProduct");
                   break;
               case "add":
//                   req.setAttribute("listCate", CategoryImpl.categoryList);
                  req.getRequestDispatcher("/Product/addAndUpdate.jsp").forward(req, resp);
                  break;
                case "edit":
                    Integer productId = Integer.parseInt(req.getParameter("id"));
                    Product productToEdit =ProductImpl.productList.get(productImpl.findIndexByID(productId));
                    // Giả sử bạn đã có phương thức getById
                    req.setAttribute("product", productToEdit);
//                    req.setAttribute("listCate", CategoryImpl.categoryList);
                    req.getRequestDispatcher("/Product/addAndUpdate.jsp").forward(req, resp);
                    break;



            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "add":
                    String name = req.getParameter("name");
                    Double price = Double.parseDouble(req.getParameter("price"));
                    Integer quantity = Integer.parseInt(req.getParameter("quantity"));
//                    Integer categoryId = Integer.parseInt(req.getParameter("categoryId"));
                    // Lấy categoryId từ request
                    String categoryIdParam = req.getParameter("categoryId");
                    Integer categoryId = null;

// Kiểm tra categoryId có null hoặc rỗng không
                    if (categoryIdParam == null || categoryIdParam.trim().isEmpty()) {
                        req.setAttribute("errorMessage", "Danh mục không được để trống.");
                        req.getRequestDispatcher("/Product/addAndUpdate.jsp").forward(req, resp);
                        return;
                    }

                    try {
                        categoryId = Integer.parseInt(categoryIdParam);
                    } catch (NumberFormatException e) {
                        req.setAttribute("errorMessage", "ID danh mục không hợp lệ. Vui lòng nhập một số nguyên.");
                        req.getRequestDispatcher("/Product/addAndUpdate.jsp").forward(req, resp);
                        return;
                    }

// Tìm danh mục tương ứng
//                    Integer finalCategoryId = categoryId;
//                    Category selectedCategory = CategoryImpl.categoryList.stream()
//                            .filter(category -> category.getId().equals(finalCategoryId))
//                            .findFirst()
//                            .orElse(null);

// Kiểm tra xem selectedCategory có null không
//                    if (selectedCategory == null) {
//                        req.setAttribute("errorMessage", "Danh mục không hợp lệ. Vui lòng chọn danh mục hợp lệ.");
//                        req.getRequestDispatcher("/Product/addAndUpdate.jsp").forward(req, resp);
//                        return;
//                    }

//// Nếu danh mục hợp lệ, tiếp tục xử lý
//                    Product product = new Product(
//                            null, // Tạo ID mới cho sản phẩm
//                            name,
//                            price,
//                            quantity,
//                            selectedCategory // Truyền đối tượng Category vào
//                    );
//                    productImpl.addAndUpdate(product);
//                    resp.sendRedirect(req.getContextPath() + "/productServlet?action=listProduct");
//
//
////                    resp.sendRedirect(req.getContextPath() + "/productServlet?action=listProduct");
//
//                    break;


//                case "edit":
//                    Integer id = Integer.parseInt(req.getParameter("id")); // Lấy ID sản phẩm từ request
//                    String editName = req.getParameter("name");
//                    Double editPrice = Double.parseDouble(req.getParameter("price"));
//                    Integer editQuantity = Integer.parseInt(req.getParameter("quantity"));
//                    Integer editCategoryId = Integer.parseInt(req.getParameter("categoryId"));
//
//                    Category editSelectedCategory = CategoryImpl.categoryList.stream()
//                            .filter(category -> category.getId().equals(editCategoryId))
//                            .findFirst()
//                            .orElse(null); // Nếu không tìm thấy, trả về null
//
//                    // Nếu danh mục hợp lệ, cập nhật thông tin sản phẩm
//                    if (editSelectedCategory != null) {
//                        Product editedProduct = new Product(
//                                id, // Sử dụng ID đã có
//                                editName,
//                                editPrice,
//                                editQuantity,
//                                editSelectedCategory // Truyền đối tượng Category vào
//                        );
//                        productImpl.addAndUpdate(editedProduct);
//                    } else {
//                        System.out.println("Không thấy ID");
//                    }
//                    resp.sendRedirect(req.getContextPath() + "/productServlet?action=listProduct");
//                    break;

            }

        }
    }
}
