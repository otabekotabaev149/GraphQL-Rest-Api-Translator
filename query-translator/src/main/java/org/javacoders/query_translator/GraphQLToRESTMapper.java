package org.javacoders.query_translator;

import java.sql.*;
import java.util.HashMap;
import java.util.Map;

public class GraphQLToRESTMapper {

    private static final String DB_URL = "jdbc:sqlite:mappings.db";

    public GraphQLToRESTMapper() {
        createTable();
    }

    private void createTable() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            String sql = "CREATE TABLE IF NOT EXISTS mappings (key TEXT PRIMARY KEY, value TEXT)";
            stmt.execute(sql);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertMapping(String key, String value) {
        String sql = "INSERT OR REPLACE INTO mappings(key, value) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, key);
            pstmt.setString(2, value);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String getValue(String key) {
        String sql = "SELECT value FROM mappings WHERE key = ?";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, key);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getString("value");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;  // Return null if key not found
    }

    public static void main(String[] args) {
        GraphQLToRESTMapper mapper = new GraphQLToRESTMapper();
        mapper.insertMapping("listUsers", "/users");
        mapper.insertMapping("getUserDetails", "/users/details/{id}");
        mapper.insertMapping("listProducts", "/products");
        mapper.insertMapping("getProductDetails", "/products/details/{id}");
        mapper.insertMapping("listOrders", "/orders");
        mapper.insertMapping("getOrderDetails", "/orders/details/{id}");
        mapper.insertMapping("listCustomers", "/customers");
        mapper.insertMapping("getCustomerDetails", "/customers/details/{id}");
        mapper.insertMapping("listCategories", "/categories");
        mapper.insertMapping("getCategoryDetails", "/categories/details/{id}");


        System.out.println(mapper.getValue("category"));
    }
}