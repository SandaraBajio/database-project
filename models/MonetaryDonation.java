package models;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class MonetaryDonation {
    
    private int donationId; // Foreign Key to Donation
    private BigDecimal amount;
    private Timestamp donationDate;
    private String status;  // enum: available/ distributed

    // Constructor
    public MonetaryDonation(int donationId, BigDecimal amount, Timestamp donationDate, String status) {
        this.donationId = donationId;
        this.amount = amount;
        this.donationDate = donationDate;
        this.status = status;
    }

    // Getters and Setters
    public int getDonationId() {
        return donationId;
    }

    public void setDonationId(int donationId) {
        this.donationId = donationId;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Timestamp getDonationDate() {
        return donationDate;
    }

    public void setDonationDate(Timestamp donationDate) {
        this.donationDate = donationDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
