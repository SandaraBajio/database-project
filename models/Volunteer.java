package models;
import java.sql.Date;

public class Volunteer {
    private int userId;  // Foreign Key to User
    private Date volunteered;
    private String operationType;  // Enum 'relief', 'rescue'
    private String availability;  // Enum 'yes', 'no'
    private String assignmentStatus;  // Enum 'unassigned', 'assigned', 'completed'

    // Constructor
    public Volunteer(int userId, Date volunteered, String operationType, String availability, String assignmentStatus) {
        this.userId = userId;
        this.volunteered = volunteered;
        this.operationType = operationType;
        this.availability = availability;
        this.assignmentStatus = assignmentStatus;
    }

    // Getters and Setters
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public Date getVolunteered() {
        return volunteered;
    }

    public void setVolunteered(Date volunteered) {
        this.volunteered = volunteered;
    }

    public String getOperationType() {
        return operationType;
    }

    public void setOperationType(String operationType) {
        this.operationType = operationType;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public String getAssignmentStatus() {
        return assignmentStatus;
    }

    public void setAssignmentStatus(String assignmentStatus) {
        this.assignmentStatus = assignmentStatus;
    }
}
