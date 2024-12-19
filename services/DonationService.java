package services;

import dao.DonationDAO;
import models.Donation;
import models.MonetaryDonation;
import models.InKindDonation;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class DonationService {
    private final DonationDAO donationDAO;

    public DonationService(Connection connection) {
        this.donationDAO = new DonationDAO(connection);
    }

    // Method to handle donation insertion
    public int donate(Donation donation) throws SQLException {
        // Insert basic donation
        return donationDAO.insertDonation(donation);
    }

    // Method to handle monetary donation insertion
    public void donateMonetary(MonetaryDonation monetaryDonation) throws SQLException {
        // Insert monetary donation
        donationDAO.insertMonetaryDonation(monetaryDonation);
    }

    // Method to handle in-kind donation insertion
    public void donateInKind(InKindDonation inKindDonation) throws SQLException {
        // Insert in-kind donation
        donationDAO.insertInKindDonation(inKindDonation);
    }

    // Method to view all donations made by a user
    public List<Donation> viewAllDonations() throws SQLException {
        // Retrieve all donations for the user
        return donationDAO.viewAllDonations();
    }

    // Method to view all monetary donations made by a user
    public List<MonetaryDonation> viewMonetaryDonations() throws SQLException {
        // Retrieve all monetary donations for the user
        return donationDAO.viewMonetaryDonations();
    }

    // Method to view all in-kind donations made by a user
    public List<InKindDonation> viewInKindDonations() throws SQLException {
        // Retrieve all in-kind donations for the user
        return donationDAO.viewInKindDonations();
    }
    
    // Additional business logic could be added here if needed
}
