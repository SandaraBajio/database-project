package services;

import dao.UserDAO;
import models.User;

import java.sql.SQLException;
import java.util.List;

public class AdminService {
    private final UserDAO userDAO;

    public AdminService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    // View all users
    public void viewAllUsers() throws SQLException {
        List<User> users = userDAO.getAllUsers();
        System.out.println("\n=== All Users ===");
        for (User user : users) {
            System.out.println("ID: " + user.getId());
            System.out.println("Name: " + user.getFirstname() + " " + user.getLastname());
            System.out.println("Email: " + user.getEmail());
            System.out.println("Role: " + user.getRole());
            System.out.println("Phone: " + user.getPhoneNumber());
            System.out.println("----------------------");
        }
    }

    // Update user details
    public void updateUser(int userId, User updatedUser) throws SQLException {
        userDAO.updateUser(userId, updatedUser);
        System.out.println("User details updated successfully.");
    }

    // Delete a user
    public void deleteUser(int userId) throws SQLException {
        userDAO.deleteUser(userId);
        System.out.println("User deleted successfully.");
    }
}
