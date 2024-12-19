package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import utils.DatabaseConnection;

public class AdminDAO {

    // Method to check if the admin credentials are valid
    public boolean checkAdminCredentials(String email, String password) {
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
            // Get the database connection from the utility class
            connection = DatabaseConnection.getConnection();

            // SQL query to check if admin exists with the given email and password
            String query = "SELECT * FROM admin WHERE email = ? AND password = ?";
            preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, email);
            preparedStatement.setString(2, password);

            // Execute the query
            resultSet = preparedStatement.executeQuery();

            // If the admin exists, return true
            return resultSet.next(); // Returns true if credentials match

        } catch (SQLException e) {
            e.printStackTrace();
            return false; // Return false if any exception occurs
        } finally {
            // Close resources
            try {
                if (resultSet != null) resultSet.close();
                if (preparedStatement != null) preparedStatement.close();
                // Connection will be automatically closed after the program finishes
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
