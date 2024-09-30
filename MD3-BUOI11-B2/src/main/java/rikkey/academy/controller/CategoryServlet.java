package rikkey.academy.controller;

import rikkey.academy.model.Category;
import rikkey.academy.service.IGenericDesign;
import rikkey.academy.service.unit.CategoryImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@WebServlet(name = "categoryServlet", value = "/categoryServlet")
public class CategoryServlet extends HttpServlet {
    private static final IGenericDesign<Category, Integer> categoryImpl = new CategoryImpl();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            switch (action) {
                case "search":
                    String search = req.getParameter("search");
                    if (search != null && !search.isBlank()) {
                        search = search.toLowerCase().trim();
                        String finalSearch = search;
                        List<Category> categoryList = categoryImpl.findAll().stream()
                                .filter(c -> c.getName().toLowerCase().contains(finalSearch))
                                .collect(Collectors.toList());
                        req.setAttribute("listCate", categoryList);
                    } else {
                        req.setAttribute("listCate", categoryImpl.findAll());
                    }
                    req.getRequestDispatcher("/Category/listCategory.jsp").forward(req, resp);
                    break;

                case "listCate":
                    req.setAttribute("listCate", categoryImpl.findAll());
                    req.getRequestDispatcher("/Category/listCategory.jsp").forward(req, resp);
                    break;

                case "delete":
                    Integer id = Integer.parseInt(req.getParameter("id"));
                    categoryImpl.remove(id);
                    resp.sendRedirect(req.getContextPath() + "/categoryServlet?action=listCate");
                    break;

                case "add":
                    req.getRequestDispatcher("/Category/addAndUpdate.jsp").forward(req, resp);
                    break;

                case "edit":
                    Integer editId = Integer.parseInt(req.getParameter("id"));
                    Category category = categoryImpl.findAll().stream()
                            .filter(c -> c.getId().equals(editId))
                            .findFirst()
                            .orElse(null);
                    if (category != null) {
                        req.setAttribute("category", category);
                    }
                    req.getRequestDispatcher("/Category/addAndUpdate.jsp").forward(req, resp);
                    break;

                default:
                    resp.sendRedirect(req.getContextPath() + "/categoryServlet?action=listCate");
                    break;
            }
        } else {
            resp.sendRedirect(req.getContextPath() + "/categoryServlet?action=listCate");
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
                Boolean status = Boolean.parseBoolean(req.getParameter("status"));

                Category category = new Category(null, name, status);
                categoryImpl.addAndUpdate(category);
                resp.sendRedirect(req.getContextPath() + "/categoryServlet?action=listCate");
                break;

            case "edit":
                Integer id = Integer.parseInt(req.getParameter("id"));
                String editName = req.getParameter("name");
                Boolean editStatus = Boolean.parseBoolean(req.getParameter("status"));

                Category editCategory = new Category(id, editName, editStatus);
                categoryImpl.addAndUpdate(editCategory);
                resp.sendRedirect(req.getContextPath() + "/categoryServlet?action=listCate");
                break;

            default:
                resp.sendRedirect(req.getContextPath() + "/categoryServlet?action=listCate");
                break;
        }
    }
}
