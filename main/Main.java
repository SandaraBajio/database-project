package main;

import controllers.*;
import dao.UserDAO;
import services.*;
import utils.DatabaseConnection;

import java.sql.Connection;

public class Main {
    public static void main(String[] args) {
        try {
            Connection connection = DatabaseConnection.getConnection();

            UserDAO userDAO = new UserDAO(connection);
            UserService userService = new UserService(userDAO);
            AdminService adminService = new AdminService(userDAO);
            DonationService donationService = new DonationService(connection);
            MissionService missionService = new MissionService(connection);
            VolunteerService volunteerService = new VolunteerService(connection);
            ConsoleDesignImpl consoleDesign = new ConsoleDesignImpl(); // Create an instance of ConsoleDesign

            // Pass the ConsoleDesign instance to the Menu constructor
            Menu menu = new Menu(userService, adminService, donationService, missionService, volunteerService, consoleDesign);
            menu.showWelcomeMenu();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            DatabaseConnection.closeConnection();
        }
    }
}
