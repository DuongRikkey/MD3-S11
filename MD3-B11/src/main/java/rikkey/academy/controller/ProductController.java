package rikkey.academy.controller;

import rikkey.academy.model.Product;
import rikkey.academy.service.IgenericService;
import rikkey.academy.service.ProductImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name ="productServlet",value = "/productServlet")
public class ProductController extends HttpServlet {
   private static IgenericService productImpl = new ProductImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");

        if (action != null) {
            switch (action) {
                case "search":
                    String search = req.getParameter("search");

                    // Check if search is not null and not blank
                    if (search != null && !search.isBlank()) {
                        // Convert the search term to lowercase for case-insensitive search
                        search = search.toLowerCase().trim();

                        // Filter the product list based on the search term
                        String finalSearch = search;
                        List<Product> searchList = ProductImpl.productList.stream()
                                .filter(p -> p.getName().toLowerCase().contains(finalSearch))
                                .toList();

                        // Set the filtered list to the request attribute
                        req.setAttribute("list", searchList);
                    } else {
                        // If the search is blank, pass the full product list
                        req.setAttribute("list", ProductImpl.productList);
                    }

                    // Forward the request to the list.jsp page
                    req.getRequestDispatcher("/WEB-INF/product/list.jsp").forward(req, resp);
                    break;
                case "list":
                    req.setAttribute("list", ProductImpl.productList);
                    req.getRequestDispatcher("/WEB-INF/product/list.jsp").forward(req, resp);
                    break;
                case "delete":
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    productImpl.delete(id);
                    resp.sendRedirect(req.getContextPath() + "/productServlet?action=list");
                    break;
                case "add":
                    req.getRequestDispatcher("ProductAuth/add.jsp").forward(req, resp);
                    break;
                case "edit":
                    Integer editId = Integer.parseInt(req.getParameter("id"));
                    Product product=ProductImpl.productList.get(productImpl.findIndexByID(editId));
                    req.setAttribute("product", product);
                    req.getRequestDispatcher("ProductAuth/add.jsp").forward(req, resp);
                    break;
            }
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.setCharacterEncoding("UTF-8");
        resp.setCharacterEncoding("UTF-8");
        String action = req.getParameter("action");
        switch (action) {
            case "add":
                String name = req.getParameter("name");
                String description = req.getParameter("description");
                Double price = Double.parseDouble(req.getParameter("price"));
                String producer = req.getParameter("producer");
                boolean status = Boolean.parseBoolean(req.getParameter("status"));

                // Tạo sản phẩm mới với ID null
                Product product = new Product(null, name, price, description, producer, status);
                productImpl.addAndUpdate(product);
                // Chuyển hướng đến danh sách sản phẩm
                resp.sendRedirect(req.getContextPath() + "/productServlet?action=list");
                break;
            case "edit":
                Integer Id = Integer.parseInt(req.getParameter("id"));
                String editName = req.getParameter("name");
                String editDescription = req.getParameter("description");
                Double editPrice = Double.parseDouble(req.getParameter("price"));
                String editProducer = req.getParameter("producer");
                boolean editStatus = Boolean.parseBoolean(req.getParameter("status"));
                Product editProduct=new Product(Id, editName, editPrice, editDescription, editProducer, editStatus);
                productImpl.addAndUpdate(editProduct);
                resp.sendRedirect(req.getContextPath() + "/productServlet?action=list");
                break;
        }
    }
}




