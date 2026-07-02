package com.example.dao;

import com.example.entity.BookCategory;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookCategoryDAO {
    public List<BookCategory> getAllCategories() {
        List<BookCategory> categories = new ArrayList<>();
        String sql = "SELECT id, name, description, created_at, updated_at FROM book_category ORDER BY id ASC";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            while (rs.next()) {
                BookCategory category = new BookCategory();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setCreatedAt(rs.getTimestamp("created_at"));
                category.setUpdatedAt(rs.getTimestamp("updated_at"));
                categories.add(category);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return categories;
    }

    public BookCategory getCategoryById(Integer id) {
        String sql = "SELECT id, name, description, created_at, updated_at FROM book_category WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                BookCategory category = new BookCategory();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                category.setCreatedAt(rs.getTimestamp("created_at"));
                category.setUpdatedAt(rs.getTimestamp("updated_at"));
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return null;
    }

    public BookCategory getCategoryByName(String name) {
        String sql = "SELECT id, name, description, created_at, updated_at FROM book_category WHERE name = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, name);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                BookCategory category = new BookCategory();
                category.setId(rs.getInt("id"));
                category.setName(rs.getString("name"));
                category.setDescription(rs.getString("description"));
                return category;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return null;
    }

    public int addCategory(BookCategory category) {
        String sql = "INSERT INTO book_category (name, description) VALUES (?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    public int updateCategory(BookCategory category) {
        String sql = "UPDATE book_category SET name = ?, description = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, category.getName());
            pstmt.setString(2, category.getDescription());
            pstmt.setInt(3, category.getId());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    public int deleteCategory(Integer id) {
        String sql = "DELETE FROM book_category WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }
}