package main.java.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import main.java.db.DBConnector;
import main.java.model.Admin;

public class AdminDAO {
    private Connection connection;

    public AdminDAO() throws SQLException {
        connection = DBConnector.getConnection();
    }

    public List<Admin> getAllAdmins() throws SQLException {
        List<Admin> admins = new ArrayList<>();
        String query = "SELECT * FROM admin";
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Admin admin = new Admin();
                admin.setUser_id(resultSet.getString("admin_id"));
                admin.setName(resultSet.getString("name"));
                admin.setEmail(resultSet.getString("email"));
                admin.setPhone(resultSet.getString("phone"));
                admin.setPassword(resultSet.getString("password"));
                admins.add(admin);
            }
        }
        return admins;
    }

    public Admin getAdminById(String adminId) throws SQLException {
        String query = "SELECT * FROM admin WHERE admin_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, adminId);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Admin admin = new Admin();
                    admin.setUser_id(resultSet.getString("admin_id"));
                    admin.setName(resultSet.getString("name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPhone(resultSet.getString("phone"));
                    admin.setPassword(resultSet.getString("password"));
                    return admin;
                }
            }
        }
        return null; // Admin with the given ID not found
    }

    public void insertAdmin(Admin admin) throws SQLException {
        String query = "INSERT INTO admin (admin_id, name, email, phone, password) VALUES (?, ?, ?, ?, ?)";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getUser_id());
            statement.setString(2, admin.getName());
            statement.setString(3, admin.getEmail());
            statement.setString(4, admin.getPhone());
            statement.setString(5, admin.getPassword());
            statement.executeUpdate();
        }
    }

    public void updateAdmin(Admin admin) throws SQLException {
        String query = "UPDATE admin SET name = ?, email = ?, phone = ?, password = ? WHERE admin_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, admin.getName());
            statement.setString(2, admin.getEmail());
            statement.setString(3, admin.getPhone());
            statement.setString(4, admin.getPassword());
            statement.setString(5, admin.getUser_id());
            statement.executeUpdate();
        }
    }

    public void deleteAdmin(String adminId) throws SQLException {
        String query = "DELETE FROM admin WHERE admin_id = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, adminId);
            statement.executeUpdate();
        }
    }
    public Admin getAdminByUsernameAndPassword(String username, String password) throws SQLException {
        String query = "SELECT * FROM admin WHERE admin_id = ? AND password = ?";
        try (PreparedStatement statement = connection.prepareStatement(query)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    Admin admin = new Admin();
                    admin.setUser_id(resultSet.getString("admin_id"));
                    // admin.setName(resultSet.getString("name"));
                    admin.setPassword(resultSet.getString("password"));
                    admin.setName(resultSet.getString("name"));
                    admin.setEmail(resultSet.getString("email"));
                    admin.setPhone(resultSet.getString("phone"));
                    return admin;
                }
            }
        }
        return null; // Admin with the given username and password not found
    }
}
