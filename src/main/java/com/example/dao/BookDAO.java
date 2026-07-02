package com.example.dao;

import com.example.entity.Book;
import com.example.util.DBUtil;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class BookDAO {
    public int getTotalCount(String keyword, Integer categoryId) {
        String sql = "SELECT COUNT(*) FROM book b LEFT JOIN book_category c ON b.category_id = c.id WHERE 1=1";
        List<Object> params = new ArrayList<>();
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql += " AND (b.title LIKE ? OR b.author LIKE ? OR b.isbn LIKE ? OR b.publisher LIKE ?)";
            String likeKeyword = "%" + keyword.trim() + "%";
            params.add(likeKeyword);
            params.add(likeKeyword);
            params.add(likeKeyword);
            params.add(likeKeyword);
        }
        
        if (categoryId != null && categoryId > 0) {
            sql += " AND b.category_id = ?";
            params.add(categoryId);
        }
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return 0;
    }

    public List<Book> getBookList(int page, int pageSize, String keyword, Integer categoryId) {
        List<Book> books = new ArrayList<>();
        String sql = "SELECT b.id, b.isbn, b.title, b.author, b.publisher, b.publish_date, b.category_id, " +
                     "c.name AS category_name, b.price, b.stock, b.description, b.created_at, b.updated_at " +
                     "FROM book b LEFT JOIN book_category c ON b.category_id = c.id WHERE 1=1";
        List<Object> params = new ArrayList<>();
        
        if (keyword != null && !keyword.trim().isEmpty()) {
            sql += " AND (b.title LIKE ? OR b.author LIKE ? OR b.isbn LIKE ? OR b.publisher LIKE ?)";
            String likeKeyword = "%" + keyword.trim() + "%";
            params.add(likeKeyword);
            params.add(likeKeyword);
            params.add(likeKeyword);
            params.add(likeKeyword);
        }
        
        if (categoryId != null && categoryId > 0) {
            sql += " AND b.category_id = ?";
            params.add(categoryId);
        }
        
        sql += " ORDER BY b.id DESC LIMIT ?, ?";
        params.add((page - 1) * pageSize);
        params.add(pageSize);
        
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            for (int i = 0; i < params.size(); i++) {
                pstmt.setObject(i + 1, params.get(i));
            }
            rs = pstmt.executeQuery();
            while (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublishDate(rs.getDate("publish_date"));
                book.setCategoryId(rs.getInt("category_id"));
                book.setCategoryName(rs.getString("category_name"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setStock(rs.getInt("stock"));
                book.setDescription(rs.getString("description"));
                book.setCreatedAt(rs.getTimestamp("created_at"));
                book.setUpdatedAt(rs.getTimestamp("updated_at"));
                books.add(book);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return books;
    }

    public Book getBookById(Integer id) {
        String sql = "SELECT b.id, b.isbn, b.title, b.author, b.publisher, b.publish_date, b.category_id, " +
                     "c.name AS category_name, b.price, b.stock, b.description, b.created_at, b.updated_at " +
                     "FROM book b LEFT JOIN book_category c ON b.category_id = c.id WHERE b.id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, id);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublishDate(rs.getDate("publish_date"));
                book.setCategoryId(rs.getInt("category_id"));
                book.setCategoryName(rs.getString("category_name"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setStock(rs.getInt("stock"));
                book.setDescription(rs.getString("description"));
                book.setCreatedAt(rs.getTimestamp("created_at"));
                book.setUpdatedAt(rs.getTimestamp("updated_at"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return null;
    }

    public Book getBookByIsbn(String isbn) {
        String sql = "SELECT b.id, b.isbn, b.title, b.author, b.publisher, b.publish_date, b.category_id, " +
                     "c.name AS category_name, b.price, b.stock, b.description, b.created_at, b.updated_at " +
                     "FROM book b LEFT JOIN book_category c ON b.category_id = c.id WHERE b.isbn = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, isbn);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                Book book = new Book();
                book.setId(rs.getInt("id"));
                book.setIsbn(rs.getString("isbn"));
                book.setTitle(rs.getString("title"));
                book.setAuthor(rs.getString("author"));
                book.setPublisher(rs.getString("publisher"));
                book.setPublishDate(rs.getDate("publish_date"));
                book.setCategoryId(rs.getInt("category_id"));
                book.setCategoryName(rs.getString("category_name"));
                book.setPrice(rs.getBigDecimal("price"));
                book.setStock(rs.getInt("stock"));
                book.setDescription(rs.getString("description"));
                return book;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return null;
    }

    public int addBook(Book book) {
        String sql = "INSERT INTO book (isbn, title, author, publisher, publish_date, category_id, price, stock, description) " +
                     "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getIsbn());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getPublisher());
            pstmt.setDate(5, book.getPublishDate() != null ? new Date(book.getPublishDate().getTime()) : null);
            pstmt.setInt(6, book.getCategoryId());
            pstmt.setBigDecimal(7, book.getPrice());
            pstmt.setInt(8, book.getStock() != null ? book.getStock() : 0);
            pstmt.setString(9, book.getDescription());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    public int updateBook(Book book) {
        String sql = "UPDATE book SET isbn = ?, title = ?, author = ?, publisher = ?, publish_date = ?, " +
                     "category_id = ?, price = ?, stock = ?, description = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setString(1, book.getIsbn());
            pstmt.setString(2, book.getTitle());
            pstmt.setString(3, book.getAuthor());
            pstmt.setString(4, book.getPublisher());
            pstmt.setDate(5, book.getPublishDate() != null ? new Date(book.getPublishDate().getTime()) : null);
            pstmt.setInt(6, book.getCategoryId());
            pstmt.setBigDecimal(7, book.getPrice());
            pstmt.setInt(8, book.getStock() != null ? book.getStock() : 0);
            pstmt.setString(9, book.getDescription());
            pstmt.setInt(10, book.getId());
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    public int deleteBook(Integer id) {
        String sql = "DELETE FROM book WHERE id = ?";
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

    public int updateStock(Integer id, Integer stock) {
        String sql = "UPDATE book SET stock = ? WHERE id = ?";
        Connection conn = null;
        PreparedStatement pstmt = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            pstmt.setInt(1, stock);
            pstmt.setInt(2, id);
            return pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            return 0;
        } finally {
            DBUtil.close(conn, pstmt);
        }
    }

    public int getTotalStock() {
        String sql = "SELECT COALESCE(SUM(stock), 0) FROM book";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return 0;
    }

    public int getTotalBookCount() {
        String sql = "SELECT COUNT(*) FROM book";
        Connection conn = null;
        PreparedStatement pstmt = null;
        ResultSet rs = null;
        try {
            conn = DBUtil.getConnection();
            pstmt = conn.prepareStatement(sql);
            rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.close(conn, pstmt, rs);
        }
        return 0;
    }
}