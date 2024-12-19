package services;

import dao.UserDAO;
import models.User;

import java.sql.SQLException;
import java.util.List;

public class UserService {
    private final UserDAO userDAO;

    public UserService(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public void registerUser(User user) throws SQLException {
        userDAO.registerUser(user);
    }

    public User login(String email, String password) throws SQLException {
        return userDAO.login(email, password);
    }

    public List<User> listUsers() throws SQLException {
        return userDAO.getAllUsers();
    }

    // Method to show user info excluding email and password
    public User showUserInfo(int userId) throws SQLException {
        return userDAO.showUserInfo(userId);
    }
}
