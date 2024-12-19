package dao;

import models.Donation;
import models.MonetaryDonation;
import models.InKindDonation;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DonationDAO {
    private final Connection connection;

    public DonationDAO(Connection connection) {
        this.connection = connection;
    }

    // Insert a generic donation
    public int insertDonation(Donation donation) throws SQLException {
        String query = "INSERT INTO Donation (userid, donation_date, donation_type) VALUES (?, CURRENT_TIMESTAMP, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS)) {
            pstmt.setInt(1, donation.getUserId());
            pstmt.setString(2, donation.getDonationType());
            int rowsAffected = pstmt.executeUpdate();

            if (rowsAffected > 0) {
                try (ResultSet rs = pstmt.getGeneratedKeys()) {
                    if (rs.next()) {
                        return rs.getInt(1);  // Return generated donation ID
                    }
                }
            }
        }
        return -1;  // Return failure if donation ID wasn't generated
    }

    // Insert a monetary donation
    public void insertMonetaryDonation(MonetaryDonation donation) throws SQLException {
        String query = "INSERT INTO MonetaryDonation (donationid, amount, donation_date, status) VALUES (?, ?, CURRENT_TIMESTAMP, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, donation.getDonationId());
            pstmt.setBigDecimal(2, donation.getAmount());
            pstmt.setString(3, donation.getStatus());
            pstmt.executeUpdate();
        }
    }

    // Insert an in-kind donation
    public void insertInKindDonation(InKindDonation donation) throws SQLException {
        String query = "INSERT INTO InKindDonation (donationid, kindtype, units, donation_date, status) VALUES (?, ?, ?, CURRENT_TIMESTAMP, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {
            pstmt.setInt(1, donation.getDonationId());
            pstmt.setString(2, donation.getKindType());
            pstmt.setInt(3, donation.getUnits());
            pstmt.setString(4, donation.getStatus());
            pstmt.executeUpdate();
        }
    }

    // View all donations for a user
    // Method to view all donations made by a user
    public List<Donation>viewAllDonations() throws SQLException {
        List<Donation> donations = new ArrayList<>();
        String query = "SELECT * FROM Donation";
    
        try (Statement stmt = connection.createStatement();
        ResultSet rs = stmt.executeQuery(query)) { 
                while (rs.next()) {
                    donations.add(new Donation(
                            rs.getInt("donationid"),
                            rs.getInt("userid"),
                            rs.getTimestamp("donation_date"),
                            rs.getString("donation_type")
                    ));
                }
            }
            return donations;
        }
    
    // Method to view monetary donations made by a user
    public List<MonetaryDonation> viewMonetaryDonations() throws SQLException {
        List<MonetaryDonation> monetaryDonations = new ArrayList<>();
        String query = "SELECT * FROM MonetaryDonation";
    
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    monetaryDonations.add(new MonetaryDonation(
                            rs.getInt("donationid"),
                            rs.getBigDecimal("amount"),
                            rs.getTimestamp("donation_date"),
                            rs.getString("status")
                    ));
                }
            }
            return monetaryDonations;
        }
    
    // Method to view in-kind donations made by a user
    public List<InKindDonation> viewInKindDonations() throws SQLException {
        List<InKindDonation> inkindDonations = new ArrayList<>();
        String query = "SELECT * FROM InKindDonation";
    
        try (Statement stmt = connection.createStatement();
            ResultSet rs = stmt.executeQuery(query)) {

                while (rs.next()) {
                    inkindDonations.add(new InKindDonation(
                            rs.getInt("donationid"),
                            rs.getString("kindType"),
                            rs.getInt("units"),
                            rs.getTimestamp("donation_date"),
                            rs.getString("status")
                    ));
                }
            }
            return inkindDonations;
        }
    }
    
