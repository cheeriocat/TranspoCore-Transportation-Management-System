package services;

import dao.DriverAssignmentDB;
import models.DriverAssignment;

import java.sql.Connection;
import java.util.List;

public class DriverAssignmentService {

    private DriverAssignmentDB dao;

    public DriverAssignmentService(Connection conn) {
        this.dao = new DriverAssignmentDB(conn);
    }

    public boolean addAssignment(DriverAssignment assignment) {
        return dao.addAssignment(assignment);
    }

    public boolean updateAssignment(DriverAssignment assignment) {
        return dao.updateAssignment(assignment);
    }

    public boolean deleteAssignment(int assignmentId) {
        return dao.deleteAssignment(assignmentId);
    }

    public DriverAssignment getAssignmentById(int assignmentId) {
        return dao.getAssignmentById(assignmentId);
    }

    public List<DriverAssignment> getAssignmentsByShipment(int shipmentId) {
        return dao.getAssignmentsByShipment(shipmentId);
    }

    public List<DriverAssignment> getAllAssignments() {
        return dao.getAllAssignments();
    }
}
