package com.example.servlet;

import com.example.entity.BookCategory;
import com.example.service.BookCategoryService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

@WebServlet("/category")
public class CategoryServlet extends HttpServlet {
    private BookCategoryService categoryService = new BookCategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                handleCategoryList(request, response);
                break;
            case "add":
                handleAddCategoryForm(request, response);
                break;
            case "edit":
                handleEditCategoryForm(request, response);
                break;
            case "delete":
                handleDeleteCategory(request, response);
                break;
            default:
                handleCategoryList(request, response);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "add":
                handleAddCategory(request, response);
                break;
            case "edit":
                handleEditCategory(request, response);
                break;
            default:
                handleCategoryList(request, response);
        }
    }

    private void handleCategoryList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookCategory> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/categoryList.jsp").forward(request, response);
    }

    private void handleAddCategoryForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.getRequestDispatcher("/categoryForm.jsp").forward(request, response);
    }

    private void handleAddCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String name = request.getParameter("name");
        String description = request.getParameter("description");

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "分类名称不能为空");
            handleAddCategoryForm(request, response);
            return;
        }

        BookCategory category = new BookCategory(name.trim(), description);
        boolean success = categoryService.addCategory(category);

        HttpSession session = request.getSession();
        if (success) {
            session.setAttribute("message", "分类添加成功");
        } else {
            session.setAttribute("message", "分类添加失败，名称可能已存在");
        }
        response.sendRedirect(request.getContextPath() + "/category?action=list");
    }

    private void handleEditCategoryForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/category?action=list");
            return;
        }

        BookCategory category = categoryService.getCategoryById(id);
        if (category == null) {
            response.sendRedirect(request.getContextPath() + "/category?action=list");
            return;
        }

        request.setAttribute("category", category);
        request.getRequestDispatcher("/categoryForm.jsp").forward(request, response);
    }

    private void handleEditCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/category?action=list");
            return;
        }

        String name = request.getParameter("name");
        String description = request.getParameter("description");

        if (name == null || name.trim().isEmpty()) {
            request.setAttribute("error", "分类名称不能为空");
            handleEditCategoryForm(request, response);
            return;
        }

        BookCategory category = new BookCategory(name.trim(), description);
        category.setId(id);

        boolean success = categoryService.updateCategory(category);

        HttpSession session = request.getSession();
        if (success) {
            session.setAttribute("message", "分类更新成功");
        } else {
            session.setAttribute("message", "分类更新失败，名称可能已存在");
        }
        response.sendRedirect(request.getContextPath() + "/category?action=list");
    }

    private void handleDeleteCategory(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/category?action=list");
            return;
        }

        boolean success = categoryService.deleteCategory(id);

        HttpSession session = request.getSession();
        if (success) {
            session.setAttribute("message", "分类删除成功");
        } else {
            session.setAttribute("message", "分类删除失败，可能存在关联图书");
        }
        response.sendRedirect(request.getContextPath() + "/category?action=list");
    }
}