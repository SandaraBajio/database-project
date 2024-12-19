package services;

import dao.MissionDAO;
import models.Mission;

import java.sql.SQLException;
import java.util.List;
import java.sql.Connection;



public class MissionService {

    private final MissionDAO missionDAO;
    public MissionService(Connection connection) {
        this.missionDAO = new MissionDAO(connection);
    }

    // Add a new mission
    public void addMission(Mission mission)throws SQLException {
        missionDAO.addMission(mission);
    }

    // Add mission resources and update donation status to distributed
    public void addMissionResources(int missionId, int donationId, String resourceType) {
        missionDAO.addMissionResources(missionId, donationId, resourceType);
    }

    // View all missions
    public List<Mission> viewAllMissions() {
        return missionDAO.viewAllMissions();
    }

    // Update an existing mission
    public void updateMission(Mission mission) {
        missionDAO.updateMission(mission);
    }

    // Delete a mission
    public void deleteMission(int missionId) {
        missionDAO.deleteMission(missionId);
    }
}
