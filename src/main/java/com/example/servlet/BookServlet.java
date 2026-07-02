package com.example.servlet;

import com.example.entity.Book;
import com.example.entity.BookCategory;
import com.example.service.BookCategoryService;
import com.example.service.BookService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

@WebServlet("/book")
public class BookServlet extends HttpServlet {
    private BookService bookService = new BookService();
    private BookCategoryService categoryService = new BookCategoryService();

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        if (action == null) {
            action = "list";
        }

        switch (action) {
            case "list":
                handleBookList(request, response);
                break;
            case "add":
                handleAddBookForm(request, response);
                break;
            case "edit":
                handleEditBookForm(request, response);
                break;
            case "delete":
                handleDeleteBook(request, response);
                break;
            case "detail":
                handleBookDetail(request, response);
                break;
            case "stats":
                handleStatistics(request, response);
                break;
            default:
                handleBookList(request, response);
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
                handleAddBook(request, response);
                break;
            case "edit":
                handleEditBook(request, response);
                break;
            case "search":
                handleSearchBook(request, response);
                break;
            default:
                handleBookList(request, response);
        }
    }

    private void handleBookList(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        int page = 1;
        try {
            page = Integer.parseInt(request.getParameter("page"));
            if (page < 1) page = 1;
        } catch (NumberFormatException e) {
            page = 1;
        }

        String keyword = request.getParameter("keyword");
        Integer categoryId = null;
        try {
            String categoryIdStr = request.getParameter("categoryId");
            if (categoryIdStr != null && !categoryIdStr.isEmpty() && !"0".equals(categoryIdStr)) {
                categoryId = Integer.parseInt(categoryIdStr);
            }
        } catch (NumberFormatException e) {
            categoryId = null;
        }

        List<Book> bookList = bookService.getBookList(page, keyword, categoryId);
        int totalCount = bookService.getTotalCount(keyword, categoryId);
        int totalPages = bookService.getTotalPages(keyword, categoryId);
        List<BookCategory> categoryList = categoryService.getAllCategories();
        Map<String, Object> stats = bookService.getStatistics();

        request.setAttribute("bookList", bookList);
        request.setAttribute("currentPage", page);
        request.setAttribute("totalCount", totalCount);
        request.setAttribute("totalPages", totalPages);
        request.setAttribute("keyword", keyword);
        request.setAttribute("selectedCategoryId", categoryId);
        request.setAttribute("categoryList", categoryList);
        request.setAttribute("stats", stats);

        request.getRequestDispatcher("/bookList.jsp").forward(request, response);
    }

    private void handleAddBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        List<BookCategory> categoryList = categoryService.getAllCategories();
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/bookForm.jsp").forward(request, response);
    }

    private void handleAddBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Book book = new Book();
        book.setIsbn(request.getParameter("isbn"));
        book.setTitle(request.getParameter("title"));
        book.setAuthor(request.getParameter("author"));
        book.setPublisher(request.getParameter("publisher"));
        book.setDescription(request.getParameter("description"));

        try {
            book.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
            book.setPrice(new BigDecimal(request.getParameter("price")));
            book.setStock(Integer.parseInt(request.getParameter("stock")));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "请输入有效的数字");
            handleAddBookForm(request, response);
            return;
        }

        String publishDateStr = request.getParameter("publishDate");
        if (publishDateStr != null && !publishDateStr.isEmpty()) {
            try {
                book.setPublishDate(new SimpleDateFormat("yyyy-MM-dd").parse(publishDateStr));
            } catch (ParseException e) {
                request.setAttribute("error", "请输入有效的日期格式(yyyy-MM-dd)");
                handleAddBookForm(request, response);
                return;
            }
        }

        boolean success = bookService.addBook(book);
        HttpSession session = request.getSession();
        if (success) {
            session.setAttribute("message", "图书添加成功");
        } else {
            session.setAttribute("message", "图书添加失败，ISBN可能已存在");
        }
        response.sendRedirect(request.getContextPath() + "/book?action=list");
    }

    private void handleEditBookForm(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/book?action=list");
            return;
        }

        Book book = bookService.getBookById(id);
        if (book == null) {
            response.sendRedirect(request.getContextPath() + "/book?action=list");
            return;
        }

        List<BookCategory> categoryList = categoryService.getAllCategories();
        request.setAttribute("book", book);
        request.setAttribute("categoryList", categoryList);
        request.getRequestDispatcher("/bookForm.jsp").forward(request, response);
    }

    private void handleEditBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/book?action=list");
            return;
        }

        Book book = new Book();
        book.setId(id);
        book.setIsbn(request.getParameter("isbn"));
        book.setTitle(request.getParameter("title"));
        book.setAuthor(request.getParameter("author"));
        book.setPublisher(request.getParameter("publisher"));
        book.setDescription(request.getParameter("description"));

        try {
            book.setCategoryId(Integer.parseInt(request.getParameter("categoryId")));
            book.setPrice(new BigDecimal(request.getParameter("price")));
            book.setStock(Integer.parseInt(request.getParameter("stock")));
        } catch (NumberFormatException e) {
            request.setAttribute("error", "请输入有效的数字");
            handleEditBookForm(request, response);
            return;
        }

        String publishDateStr = request.getParameter("publishDate");
        if (publishDateStr != null && !publishDateStr.isEmpty()) {
            try {
                book.setPublishDate(new SimpleDateFormat("yyyy-MM-dd").parse(publishDateStr));
            } catch (ParseException e) {
                request.setAttribute("error", "请输入有效的日期格式(yyyy-MM-dd)");
                handleEditBookForm(request, response);
                return;
            }
        }

        boolean success = bookService.updateBook(book);
        HttpSession session = request.getSession();
        if (success) {
            session.setAttribute("message", "图书更新成功");
        } else {
            session.setAttribute("message", "图书更新失败");
        }
        response.sendRedirect(request.getContextPath() + "/book?action=list");
    }

    private void handleDeleteBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/book?action=list");
            return;
        }

        boolean success = bookService.deleteBook(id);
        HttpSession session = request.getSession();
        if (success) {
            session.setAttribute("message", "图书删除成功");
        } else {
            session.setAttribute("message", "图书删除失败");
        }
        response.sendRedirect(request.getContextPath() + "/book?action=list");
    }

    private void handleBookDetail(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Integer id = null;
        try {
            id = Integer.parseInt(request.getParameter("id"));
        } catch (NumberFormatException e) {
            response.sendRedirect(request.getContextPath() + "/book?action=list");
            return;
        }

        Book book = bookService.getBookById(id);
        if (book == null) {
            response.sendRedirect(request.getContextPath() + "/book?action=list");
            return;
        }

        request.setAttribute("book", book);
        request.getRequestDispatcher("/bookDetail.jsp").forward(request, response);
    }

    private void handleSearchBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        handleBookList(request, response);
    }

    private void handleStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Map<String, Object> stats = bookService.getStatistics();
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        PrintWriter writer = response.getWriter();
        writer.println("{\"totalBooks\":" + stats.get("totalBooks") + ",\"totalStock\":" + stats.get("totalStock") + "}");
    }
}