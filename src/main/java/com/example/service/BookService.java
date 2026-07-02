package com.example.service;

import com.example.dao.BookDAO;
import com.example.entity.Book;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BookService {
    private BookDAO bookDAO = new BookDAO();
    private static final int PAGE_SIZE = 5;

    public int getTotalCount(String keyword, Integer categoryId) {
        return bookDAO.getTotalCount(keyword, categoryId);
    }

    public List<Book> getBookList(int page, String keyword, Integer categoryId) {
        return bookDAO.getBookList(page, PAGE_SIZE, keyword, categoryId);
    }

    public int getPageSize() {
        return PAGE_SIZE;
    }

    public int getTotalPages(String keyword, Integer categoryId) {
        int totalCount = getTotalCount(keyword, categoryId);
        return (int) Math.ceil((double) totalCount / PAGE_SIZE);
    }

    public Book getBookById(Integer id) {
        return bookDAO.getBookById(id);
    }

    public Book getBookByIsbn(String isbn) {
        return bookDAO.getBookByIsbn(isbn);
    }

    public boolean addBook(Book book) {
        if (!validateBook(book)) {
            return false;
        }
        if (getBookByIsbn(book.getIsbn()) != null) {
            return false;
        }
        return bookDAO.addBook(book) > 0;
    }

    public boolean updateBook(Book book) {
        if (!validateBook(book) || book.getId() == null) {
            return false;
        }
        Book existing = getBookByIsbn(book.getIsbn());
        if (existing != null && !existing.getId().equals(book.getId())) {
            return false;
        }
        return bookDAO.updateBook(book) > 0;
    }

    public boolean deleteBook(Integer id) {
        if (id == null || id <= 0) {
            return false;
        }
        return bookDAO.deleteBook(id) > 0;
    }

    public boolean updateStock(Integer id, Integer stock) {
        if (id == null || id <= 0 || stock == null || stock < 0) {
            return false;
        }
        return bookDAO.updateStock(id, stock) > 0;
    }

    private boolean validateBook(Book book) {
        return book != null 
            && book.getIsbn() != null && !book.getIsbn().trim().isEmpty()
            && book.getTitle() != null && !book.getTitle().trim().isEmpty()
            && book.getAuthor() != null && !book.getAuthor().trim().isEmpty()
            && book.getCategoryId() != null && book.getCategoryId() > 0
            && book.getPrice() != null && book.getPrice().compareTo(BigDecimal.ZERO) >= 0;
    }

    public Map<String, Object> getStatistics() {
        Map<String, Object> stats = new HashMap<>();
        stats.put("totalBooks", bookDAO.getTotalBookCount());
        stats.put("totalStock", bookDAO.getTotalStock());
        return stats;
    }
}