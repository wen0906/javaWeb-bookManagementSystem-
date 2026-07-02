package com.example.util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;
import java.util.stream.Collectors;

public class DBUtil {
    private static String driver;
    private static String url;
    private static String username;
    private static String password;

    static {
        try {
            Properties props = new Properties();
            props.load(DBUtil.class.getClassLoader().getResourceAsStream("db.properties"));
            driver = props.getProperty("jdbc.driver");
            url = props.getProperty("jdbc.url");
            username = props.getProperty("jdbc.username");
            password = props.getProperty("jdbc.password");
            Class.forName(driver);
            initDatabase();
        } catch (Exception e) {
            throw new RuntimeException("数据库连接初始化失败", e);
        }
    }

    private static void initDatabase() {
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;
        try {
            conn = DriverManager.getConnection(url, username, password);
            rs = conn.getMetaData().getTables(null, null, "BOOK", new String[]{"TABLE"});
            if (!rs.next()) {
                InputStream is = DBUtil.class.getClassLoader().getResourceAsStream("schema-h2.sql");
                if (is != null) {
                    String sql = new BufferedReader(new InputStreamReader(is, StandardCharsets.UTF_8)).lines().collect(Collectors.joining("\n"));
                    stmt = conn.createStatement();
                    for (String s : sql.split(";")) {
                        String trimmed = s.trim();
                        if (!trimmed.isEmpty()) {
                            stmt.execute(trimmed);
                        }
                    }
                    System.out.println("[DBUtil] 数据库表已初始化");
                }
            }
        } catch (SQLException e) {
            System.err.println("[DBUtil] 数据库初始化失败: " + e.getMessage());
        } finally {
            close(conn, stmt, rs);
        }
    }

    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(url, username, password);
    }

    public static void close(Connection conn, Statement stmt, ResultSet rs) {
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (stmt != null) {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        if (conn != null) {
            try {
                conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void close(Connection conn, Statement stmt) {
        close(conn, stmt, null);
    }

    public static void close(Connection conn) {
        close(conn, null, null);
    }
}