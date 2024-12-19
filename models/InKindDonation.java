package models;
import java.sql.Timestamp;

public class InKindDonation {
    private int donationId;  // Foreign Key to Donation
    private String kindType;  // Enum 'food', 'clothing', etc.
    private int units;
    private Timestamp donationDate;
    private String status;  // available, distributed

    // Constructor
    public InKindDonation(int donationId, String kindType, int units, Timestamp donationDate, String status) {
        this.donationId = donationId;
        this.kindType = kindType;
        this.units = units;
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

    public String getKindType() {
        return kindType;
    }

    public void setKindType(String kindType) {
        this.kindType = kindType;
    }

    public int getUnits() {
        return units;
    }

    public void setUnits(int units) {
        this.units = units;
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
