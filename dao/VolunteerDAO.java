package dao;
import java.sql.*;

public class VolunteerDAO {

    private Connection connection;

    public VolunteerDAO(Connection connection) {
        this.connection = connection;
    }

    // Method to get the assigned mission for a volunteer
    public String getAssignedMission(int userId) throws SQLException {
        String query = "SELECT missionid FROM MissionVolunteers WHERE userid = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                int missionId = rs.getInt("missionid");
                // Retrieve mission details based on the missionId
                return getMissionDetails(missionId);
            } else {
                return "Not yet assigned";
            }
        }
    }

    // Method to get mission details by missionId
    private String getMissionDetails(int missionId) throws SQLException {
        String query = "SELECT name, type, location FROM Mission WHERE id = ?";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, missionId);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                String name = rs.getString("name");
                String type = rs.getString("type");
                String location = rs.getString("location");
                return "Mission Name: " + name + ", Type: " + type + ", Location: " + location;
            } else {
                return "Mission not found";
            }
        }
    }

    // Method to assign a volunteer to a mission
    public void assignVolunteerToMission(int missionId, int userId) throws SQLException {
        String query = "UPDATE MissionVolunteers SET userid = ? WHERE missionid = ? AND userid IS NULL LIMIT 1";
        try (PreparedStatement stmt = connection.prepareStatement(query)) {
            stmt.setInt(1, userId);
            stmt.setInt(2, missionId);
            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated == 0) {
                throw new SQLException("No unassigned volunteers for this mission.");
            }
        }
    }

    // Method to get all volunteers for the admin to choose from
    public ResultSet getAllVolunteers() throws SQLException {
        String query = "SELECT id, firstname, lastname FROM User WHERE role = 'volunteer'";
        Statement stmt = connection.createStatement();
        return stmt.executeQuery(query);
    }
}
