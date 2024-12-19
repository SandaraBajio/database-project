package services;

import dao.VolunteerDAO; 
import java.sql.*;

public class VolunteerService {

    private VolunteerDAO volunteerDAO;

    public VolunteerService(Connection connection) {
        this.volunteerDAO = new VolunteerDAO(connection);
    }

    // Method to view assigned mission for a volunteer
    public String viewAssignedMission(int userId) throws SQLException {
        return volunteerDAO.getAssignedMission(userId);
    }

    // Method to assign volunteer to a mission
    public void assignVolunteerToMission(int missionId, int userId) throws SQLException {
        volunteerDAO.assignVolunteerToMission(missionId, userId);
    }

    // Method to get all volunteers for the admin to choose from
    public ResultSet getAllVolunteers() throws SQLException {
        return volunteerDAO.getAllVolunteers();
    }
}
