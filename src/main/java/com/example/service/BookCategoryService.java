package com.example.service;

import com.example.dao.BookCategoryDAO;
import com.example.entity.BookCategory;

import java.util.List;

public class BookCategoryService {
    private BookCategoryDAO categoryDAO = new BookCategoryDAO();

    public List<BookCategory> getAllCategories() {
        return categoryDAO.getAllCategories();
    }

    public BookCategory getCategoryById(Integer id) {
        return categoryDAO.getCategoryById(id);
    }

    public BookCategory getCategoryByName(String name) {
        return categoryDAO.getCategoryByName(name);
    }

    public boolean addCategory(BookCategory category) {
        if (category == null || category.getName() == null || category.getName().trim().isEmpty()) {
            return false;
        }
        if (getCategoryByName(category.getName()) != null) {
            return false;
        }
        return categoryDAO.addCategory(category) > 0;
    }

    public boolean updateCategory(BookCategory category) {
        if (category == null || category.getId() == null || category.getName() == null || category.getName().trim().isEmpty()) {
            return false;
        }
        BookCategory existing = getCategoryByName(category.getName());
        if (existing != null && !existing.getId().equals(category.getId())) {
            return false;
        }
        return categoryDAO.updateCategory(category) > 0;
    }

    public boolean deleteCategory(Integer id) {
        if (id == null || id <= 0) {
            return false;
        }
        return categoryDAO.deleteCategory(id) > 0;
    }
}