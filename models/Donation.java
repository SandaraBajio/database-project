package models;

import java.sql.Timestamp;

public class Donation {
    private int donationId;
    private int userId;
    private Timestamp donationDate;
    private String donationType;  // Enum 'monetary', 'inkind'

    // Constructor for creating a new Donation
    public Donation(int userId, String donationType) {
        this.userId = userId;
        this.donationType = donationType;
    }

    // Constructor for retrieving a Donation from the database
    public Donation(int donationId, int userId, Timestamp donationDate, String donationType) {
        this.donationId = donationId;
        this.userId = userId;
        this.donationDate = donationDate;
        this.donationType = donationType;
    }

    // Getters and Setters
    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Timestamp getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Timestamp donationDate) {
        this.donationDate = donationDate;
    }

    public String getDonationType() {
        return donationType;
    }

    public void setDonationType(String donationType) {
        this.donationType = donationType;
    }
}
