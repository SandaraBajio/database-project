package dao;

import utils.DatabaseConnection;
import models.Mission;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MissionDAO {
    private final Connection connection;

    public MissionDAO(Connection connection) {
        this.connection = connection;
    } 
    
    public void addMission(Mission mission)throws SQLException {
        String query = "INSERT INTO Mission (name, type, location, status, occurrence_date, operationtype, volunteers_needed) VALUES (?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(query)) {

            pstmt.setString(1, mission.getName());
            pstmt.setString(2, mission.getType());
            pstmt.setString(3, mission.getLocation());
            pstmt.setString(4, mission.getStatus());
            pstmt.setString(5, mission.getOccurrenceDate());
            pstmt.setString(6, mission.getOperationType());
            pstmt.setInt(7, mission.getVolunteersNeeded());
            pstmt.executeUpdate();
        
    }}

    // Add mission resources (change the status of the donation to distributed)
    public void addMissionResources(int missionId, int donationId, String resourceType) {
        String sql = "INSERT INTO MissionResources (missionid, donationid, resource_type) VALUES (?, ?, ?)";
        String updateDonationStatus = "UPDATE MonetaryDonation SET status = 'distributed' WHERE donationid = ?";

        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             PreparedStatement updateStmt = conn.prepareStatement(updateDonationStatus)) {

            // Insert into MissionResources table
            stmt.setInt(1, missionId);
            stmt.setInt(2, donationId);
            stmt.setString(3, resourceType);
            stmt.executeUpdate();

            // Update the status of the donation
            updateStmt.setInt(1, donationId);
            updateStmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // View all missions
    public List<Mission> viewAllMissions() {
        List<Mission> missions = new ArrayList<>();
        String sql = "SELECT * FROM Mission";
        try (Connection conn = DatabaseConnection.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(sql)) {

            while (rs.next()) {
                Mission mission = new Mission();
                mission.setId(rs.getInt("id"));
                mission.setName(rs.getString("name"));
                mission.setType(rs.getString("type"));
                mission.setLocation(rs.getString("location"));
                mission.setStatus(rs.getString("status"));
                mission.setOccurrenceDate(rs.getString("occurrence_date"));
                mission.setOperationType(rs.getString("operationtype"));
                mission.setVolunteersNeeded(rs.getInt("volunteers_needed"));
                missions.add(mission);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return missions;
    }

    // Update a mission's details
    public void updateMission(Mission mission) {
        String sql = "UPDATE Mission SET name = ?, type = ?, location = ?, status = ?, occurrence_date = ?, operationtype = ?, volunteers_needed = ? WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            
            stmt.setString(1, mission.getName());
            stmt.setString(2, mission.getType());
            stmt.setString(3, mission.getLocation());
            stmt.setString(4, mission.getStatus());
            stmt.setString(5, mission.getOccurrenceDate());
            stmt.setString(6, mission.getOperationType());
            stmt.setInt(7, mission.getVolunteersNeeded());
            stmt.setInt(8, mission.getId());

            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Delete a mission by its ID
    public void deleteMission(int missionId) {
        String sql = "DELETE FROM Mission WHERE id = ?";
        try (Connection conn = DatabaseConnection.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setInt(1, missionId);
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
