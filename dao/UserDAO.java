package dao;

import models.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {
    private final Connection connection;

    public UserDAO(Connection connection) {
        this.connection = connection;
    }

    // Register a new user
    public void registerUser(User user) throws SQLException {
        String query = "INSERT INTO User (email, password, firstname, lastname, middlename, age, role, phone_number) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, user.getEmail());
            pstmt.setString(2, user.getPassword());
            pstmt.setString(3, user.getFirstname());
            pstmt.setString(4, user.getLastname());
            pstmt.setString(5, user.getMiddlename());
            pstmt.setInt(6, user.getAge());
            pstmt.setString(7, user.getRole());
            pstmt.setString(8, user.getPhoneNumber());
            pstmt.executeUpdate();
        }
    }

    // Log in a user
    public User login(String email, String password) throws SQLException {
        String query = "SELECT * FROM User WHERE email = ? AND password = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                return new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("middlename"),
                        rs.getInt("age"),
                        rs.getString("role"),
                        rs.getString("phone_number")
                );
            }
        }
        return null; // User not found
    }

    // Fetch all users
    public List<User> getAllUsers() throws SQLException {
        List<User> users = new ArrayList<>();
        String query = "SELECT * FROM User";

        try (Statement stmt = connection.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {

            while (rs.next()) {
                users.add(new User(
                        rs.getInt("id"),
                        rs.getString("email"),
                        rs.getString("password"),
                        rs.getString("firstname"),
                        rs.getString("lastname"),
                        rs.getString("middlename"),
                        rs.getInt("age"),
                        rs.getString("role"),
                        rs.getString("phone_number")
                ));
            }
        }
        return users;
    }

    // Update user details
    public void updateUser(int userId, User updatedUser) throws SQLException {
        String query = "UPDATE User SET email = ?, password = ?, firstname = ?, lastname = ?, middlename = ?, age = ?, role = ?, phone_number = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setString(1, updatedUser.getEmail());
            pstmt.setString(2, updatedUser.getPassword());
            pstmt.setString(3, updatedUser.getFirstname());
            pstmt.setString(4, updatedUser.getLastname());
            pstmt.setString(5, updatedUser.getMiddlename());
            pstmt.setInt(6, updatedUser.getAge());
            pstmt.setString(7, updatedUser.getRole());
            pstmt.setString(8, updatedUser.getPhoneNumber());
            pstmt.setInt(9, userId);
            pstmt.executeUpdate();
        }
    }

    // Delete a user
    public void deleteUser(int userId) throws SQLException {
        String query = "DELETE FROM User WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, userId);
            pstmt.executeUpdate();
        }
    }
}
