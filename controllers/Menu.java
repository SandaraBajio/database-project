package controllers;

import models.*;
import services.*;
import utils.DatabaseConnection;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import java.util.Scanner;

import dao.AdminDAO;

public class Menu {
    private final UserService userService;
    private final AdminService adminService;
    private final DonationService donationService;
    private final MissionService missionService;
    private final VolunteerService volunteerService;
    private final Scanner scanner;

    public Menu(UserService userService, AdminService adminService, DonationService donationService, MissionService missionService, VolunteerService volunteerService) {
        this.userService = userService;
        this.adminService = adminService;
        this.donationService = donationService;
        this.missionService = missionService;
        this.volunteerService = volunteerService;
        this.scanner = new Scanner(System.in);
    }

    // --- Welcome Menu ---
    public void showWelcomeMenu() {
        int choice;
        do {
            System.out.println("\n=== Welcome Menu ===");
            System.out.println("1. Register");
            System.out.println("2. Log In as User");
            System.out.println("3. Log In as Admin");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> register();
                case 2 -> login();
                case 3 -> adminLogin();
                case 4 -> System.out.println("Exiting... Goodbye!");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 3);
    }

    private void register() {
        System.out.println("\n=== Register ===");
        System.out.print("Choose role (donate/volunteer): ");
        String role = scanner.nextLine();

        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        System.out.print("Enter first name: ");
        String firstname = scanner.nextLine();

        System.out.print("Enter last name: ");
        String lastname = scanner.nextLine();

        System.out.print("Enter middle name: ");
        String middlename = scanner.nextLine();

        System.out.print("Enter age: ");
        int age = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter phone number: ");
        String phoneNumber = scanner.nextLine();

        User user = new User(0, email, password, firstname, lastname, middlename, age, role, phoneNumber);
        try {
            userService.registerUser(user);
            System.out.println("Registration successful!");
        } catch (SQLException e) {
            System.out.println("Error during registration: " + e.getMessage());
        }
    }

    private void login() {
        System.out.println("\n=== Log In ===");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();

        try {
            if (email.equals("admin@example.com") && password.equals("admin123")) {
                System.out.println("Admin login successful!");
                showAdminMenu();
            } else {
                User user = userService.login(email, password);
                if (user != null) {
                    System.out.println("Login successful! Welcome, " + user.getFirstname());
                    if (user.getRole().equalsIgnoreCase("donate")) {
                        showDonationMenu(user);
                    } else if (user.getRole().equalsIgnoreCase("volunteer")) {
                        showVolunteerMenu(user);
                    } else {
                        System.out.println("Unknown role. Please contact the administrator.");
                    }
                } else {
                    System.out.println("Invalid credentials. Please try again.");
                }
            }
        } catch (SQLException e) {
            System.out.println("Error during login: " + e.getMessage());
        }
    }

    public void adminLogin() {
        System.out.println("\n=== Log In ===");
        System.out.print("Enter email: ");
        String email = scanner.nextLine();

        System.out.print("Enter password: ");
        String password = scanner.nextLine();
        AdminDAO adminDAO = new AdminDAO();

        if (adminDAO.checkAdminCredentials(email, password)) {
            System.out.println("Admin login successful!");
            showAdminMenu(); // Call method to display admin menu
        } else {
            System.out.println("Invalid email or password. Please try again.");
            showWelcomeMenu();
        }
    }


    // --- Donation Menu ---
    private void showDonationMenu(User user) {
        int choice;
        do {
            System.out.println("\n=== Donation Menu ===");
            System.out.println("1. Make a Donation");
            System.out.println("2. Log Out");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> makeDonation(user);
                case 2 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 2);
    }

    // --- Volunteer Menu ---
    private void showVolunteerMenu(User user) {
        int choice;
        do {
            System.out.println("\n=== Volunteer Menu ===");
            System.out.println("1. View Assigned Mission");
            System.out.println("2. Log Out");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> viewAssignedMission();
                case 2 -> System.out.println("Logging out...");
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 2);
    }

     // Method to view a volunteer's assigned mission
    public void viewAssignedMission() {
        System.out.print("Enter Volunteer User ID: ");
        int userId = scanner.nextInt();
        try {
            String assignedMission = volunteerService.viewAssignedMission(userId);
            System.out.println("Assigned Mission: " + assignedMission);
        } catch (SQLException e) {
            System.out.println("Error fetching assigned mission: " + e.getMessage());
        }
    }

    // Method to assign a volunteer to a mission
    public void assignVolunteerToMission() {
        System.out.print("Enter Mission ID: ");
        int missionId = scanner.nextInt();
        System.out.println("Select Volunteer from the following list:");
        try {
            ResultSet rs = volunteerService.getAllVolunteers();
            while (rs.next()) {
                int userId = rs.getInt("id");
                String firstName = rs.getString("firstname");
                String lastName = rs.getString("lastname");
                System.out.println("User ID: " + userId + ", Name: " + firstName + " " + lastName);
            }

            System.out.print("Enter the Volunteer User ID to assign: ");
            int volunteerId = scanner.nextInt();
            volunteerService.assignVolunteerToMission(missionId, volunteerId);
            System.out.println("Volunteer assigned successfully.");
        } catch (SQLException e) {
            System.out.println("Error assigning volunteer: " + e.getMessage());
        }
    }

    private void makeDonation(User user) {
        System.out.println("\n=== Make a Donation ===");
        System.out.print("Choose donation type (monetary/inkind): ");
        String type = scanner.nextLine();

        try {
            Timestamp donationDate = new Timestamp(System.currentTimeMillis()); // Current timestamp

            if (type.equalsIgnoreCase("monetary")) {
                System.out.print("Enter amount: ");
                BigDecimal amount = scanner.nextBigDecimal();
                scanner.nextLine(); // Consume newline

                Donation donation = new Donation(user.getId(), "monetary");
                int donationId = donationService.donate(donation);

                if (donationId != -1) {
                    MonetaryDonation monetaryDonation = new MonetaryDonation(donationId, amount, donationDate, "Available");
                    donationService.donateMonetary(monetaryDonation);
                    System.out.println("Monetary donation of $" + amount + " submitted!");
                } else {
                    System.out.println("Failed to submit the donation.");
                }
            } else if (type.equalsIgnoreCase("inkind")) {
                System.out.print("Enter kind of donation (e.g., food, clothing): ");
                String kind = scanner.nextLine();

                System.out.print("Enter units: ");
                int units = scanner.nextInt();
                scanner.nextLine(); // Consume newline

                Donation donation = new Donation(user.getId(), "inkind");
                int donationId = donationService.donate(donation);

                if (donationId != -1) {
                    InKindDonation inKindDonation = new InKindDonation(donationId, kind, units, donationDate, "Available");
                    donationService.donateInKind(inKindDonation);
                    System.out.println("In-Kind donation of " + units + " units of " + kind + " submitted!");
                } else {
                    System.out.println("Failed to submit the donation.");
                }
            } else {
                System.out.println("Invalid donation type.");
            }
        } catch (SQLException e) {
            System.out.println("Error during donation: " + e.getMessage());
        }
    }

    // --- Admin Menu ---
    private void showAdminMenu() {
        int choice;
        do {
            System.out.println("\n=== Admin Menu ===");
            System.out.println("1. Manage Users");
            System.out.println("2. Manage Donations");
            System.out.println("3. Manage Missions");
            System.out.println("4. Assign Volunteer to Mission");
            System.out.println("5. Log Out");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1 -> manageUsers();
                case 2 -> manageDonations();
                case 3 -> manageMissions();
                case 4 -> {assignVolunteerToMission();
                    showAdminMenu();}

                case 5 -> {
                            System.out.println("Logging out...");
                            showWelcomeMenu();}
                default -> System.out.println("Invalid choice. Please try again.");
            }
        } while (choice != 4);
    }

    private void manageUsers() {
        int choice;
        do {
            System.out.println("\n=== Manage Users ===");
            System.out.println("1. View All Users");
            System.out.println("2. Update User");
            System.out.println("3. Delete User");
            System.out.println("4. Back to Admin Menu");
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine();

            try {
                switch (choice) {
                    case 1 -> adminService.viewAllUsers();
                    case 2 -> updateUser();
                    case 3 -> deleteUser();
                    case 4 -> System.out.println("Returning to Admin Menu...");
                    default -> System.out.println("Invalid choice. Try again.");
                }
            } catch (SQLException e) {
                System.out.println("Error: " + e.getMessage());
            }
        } while (choice != 4);
    }

    private void updateUser() throws SQLException {
        System.out.print("Enter User ID to update: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter new email: ");
        String email = scanner.nextLine();
        System.out.print("Enter new password: ");
        String password = scanner.nextLine();
        System.out.print("Enter new first name: ");
        String firstname = scanner.nextLine();
        System.out.print("Enter new last name: ");
        String lastname = scanner.nextLine();
        System.out.print("Enter new middle name: ");
        String middlename = scanner.nextLine();
        System.out.print("Enter new age: ");
        int age = scanner.nextInt();
        scanner.nextLine();
        System.out.print("Enter new role (donate/volunteer): ");
        String role = scanner.nextLine();
        System.out.print("Enter new phone number: ");
        String phoneNumber = scanner.nextLine();

        User updatedUser = new User(0, email, password, firstname, lastname, middlename, age, role, phoneNumber);
        adminService.updateUser(userId, updatedUser);
    }

    private void deleteUser() throws SQLException {
        System.out.print("Enter User ID to delete: ");
        int userId = scanner.nextInt();
        scanner.nextLine();

        adminService.deleteUser(userId);
    }

    // Inside the main menu or donations management section
// Method to manage donations
private void manageDonations() {
    boolean running = true;
    while (running) {
        System.out.println("\nDonation Manager");
        System.out.println("1. View all donations");
        System.out.println("2. View monetary donations");
        System.out.println("3. View in-kind donations");
        System.out.println("4. Exit");
        System.out.print("Select an option:");

        int option = scanner.nextInt();

        switch (option) {
            case 1:
                viewAllDonations();
                break;
            case 2:
                viewMonetaryDonations();
                break;
            case 3:
                viewInKindDonations();
                break;
            case 4:
                running = false;
                System.out.println("Exiting Manage Donations.");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }
}

// Method to view all donations made by a user
private void viewAllDonations() {
    System.out.println("Viewing all donations...");
    try {
        List<Donation> donations = donationService.viewAllDonations();
        if (donations.isEmpty()) {
            System.out.println("No donations found.");
        } else {
            for (Donation donation : donations) {
                System.out.println("Donation ID: " + donation.getDonationId());
                System.out.println("User ID: " + donation.getUserId());
                System.out.println("Donation Date: " + donation.getDonationDate());
                System.out.println("Donation Type: " + donation.getDonationType());
                System.out.println("--------------------------");
            }
        }
    } catch (SQLException e) {
        System.out.println("Error viewing donations: " + e.getMessage());
    }
}

// Method to view monetary donations made by a user
private void viewMonetaryDonations() {
    System.out.println("Viewing monetary donations...");
    try {
        List<MonetaryDonation> monetaryDonations = donationService.viewMonetaryDonations();
        if (monetaryDonations.isEmpty()) {
            System.out.println("No monetary donations found.");
        } else {
            for (MonetaryDonation donation : monetaryDonations) {
                System.out.println("Donation ID: " + donation.getDonationId());
                System.out.println("Amount: " + donation.getAmount());
                System.out.println("Donation Date: " + donation.getDonationDate());
                System.out.println("Status: " + donation.getStatus());
                System.out.println("--------------------------");
            }
        }
    } catch (SQLException e) {
        System.out.println("Error viewing monetary donations: " + e.getMessage());
    }
}

// Method to view in-kind donations made by a user
private void viewInKindDonations() {
    System.out.println("Viewing in-kind donations...");
  
    try {
        List<InKindDonation> inKindDonations = donationService.viewInKindDonations();
        if (inKindDonations.isEmpty()) {
            System.out.println("No in-kind donations found.");
        } else {
            for (InKindDonation donation : inKindDonations) {
                System.out.println("Donation ID: " + donation.getDonationId());
                System.out.println("Kind Type: " + donation.getKindType());
                System.out.println("Units: " + donation.getUnits());
                System.out.println("Donation Date: " + donation.getDonationDate());
                System.out.println("Status: " + donation.getStatus());
                System.out.println("--------------------------");
            }
        }
    } catch (SQLException e) {
        System.out.println("Error viewing in-kind donations: " + e.getMessage());
    }
}

// Method to manage missions
public void manageMissions() {
    boolean exit = false;
    while (!exit) {
        // Display the mission management menu
        System.out.println("\nManage Missions:");
        System.out.println("1. Add Mission");
        System.out.println("2. Add Mission Resources");
        System.out.println("3. View All Missions");
        System.out.println("4. Update Mission");
        System.out.println("5. Delete Mission");
        System.out.println("6. Go Back");

        // Get user's choice
        int choice = scanner.nextInt();
        scanner.nextLine();  // consume newline

        switch (choice) {
            case 1:
                // Add a new mission
                addMission();
                break;
            
            case 2: 
                addMissionResources();
                break;

            case 3:
                // View all missions
                viewAllMissions();
                break;

            case 4:
                // Update an existing mission
                updateMission();
                break;

            case 5:
                // Delete a mission
                deleteMission();
                break;

            case 6:
                // Exit and go back to the main menu
                exit = true;
                break;

            default:
                System.out.println("Invalid choice. Please try again.");
                break;
        }
    }
}

// Method to add a new mission
private void addMission() {
    System.out.println("\nEnter Mission Details:");

    System.out.print("Name: ");
    String name = scanner.nextLine();

    System.out.print("Type (EARTHQUAKE, TYPHOON, FIRE, FLOOD, VOLCANIC_ERUPTION, LANDSLIDE, TSUNAMI): ");
    String type = scanner.nextLine();

    System.out.print("Location: ");
    String location = scanner.nextLine();

    System.out.print("Status (ongoing, completed): ");
    String status = scanner.nextLine();

    System.out.print("Occurrence Date (yyyy-mm-dd): ");
    String occurrenceDate = scanner.nextLine();

    System.out.print("Operation Type (rescue operation, relief operation): ");
    String operationType = scanner.nextLine();

    System.out.print("Volunteers Needed: ");
    int volunteersNeeded = scanner.nextInt();
    scanner.nextLine(); // consume newline

    // Create the mission object
    Mission mission = new Mission(name, type, location, status, occurrenceDate, operationType, volunteersNeeded);

    try {
        // Add the mission to the database
        missionService.addMission(mission);
        System.out.println("Mission added successfully.");
    } catch (Exception e) {
        System.out.println("Error adding mission: " + e.getMessage());
    }
}

// Method to view all missions
private void viewAllMissions() {
    try {
        List<Mission> missions = missionService.viewAllMissions();
        System.out.println("\nAll Missions:");
        for (Mission mission : missions) {
            System.out.println(mission);
        }
    } catch (Exception e) {
        System.out.println("Error viewing missions: " + e.getMessage());
    }
}

// Method to update an existing mission
private void updateMission() {
    System.out.print("\nEnter Mission ID to Update: ");
    int missionId = scanner.nextInt();
    scanner.nextLine(); // consume newline

    System.out.print("New Name: ");
    String name = scanner.nextLine();

    System.out.print("New Type (EARTHQUAKE, TYPHOON, FIRE, FLOOD, VOLCANIC_ERUPTION, LANDSLIDE, TSUNAMI): ");
    String type = scanner.nextLine();

    System.out.print("New Location: ");
    String location = scanner.nextLine();

    System.out.print("New Status (ongoing, completed): ");
    String status = scanner.nextLine();

    System.out.print("New Occurrence Date (yyyy-mm-dd): ");
    String occurrenceDate = scanner.nextLine();

    System.out.print("New Operation Type (rescue operation, relief operation): ");
    String operationType = scanner.nextLine();

    System.out.print("New Volunteers Needed: ");
    int volunteersNeeded = scanner.nextInt();
    scanner.nextLine(); // consume newline

    // Create the updated mission object
    Mission mission = new Mission(missionId, name, type, location, status, occurrenceDate, operationType, volunteersNeeded);

    try {
        // Update the mission in the database
        missionService.updateMission(mission);
        System.out.println("Mission updated successfully.");
    } catch (Exception e) {
        System.out.println("Error updating mission: " + e.getMessage());
    }
}

// Method to delete a mission
private void deleteMission() {
    System.out.print("\nEnter Mission ID to Delete: ");
    int missionId = scanner.nextInt();
    scanner.nextLine(); // consume newline

    try {
        // Delete the mission from the database
        missionService.deleteMission(missionId);
        System.out.println("Mission deleted successfully.");
    } catch (Exception e) {
        System.out.println("Error deleting mission: " + e.getMessage());
    }
}



}