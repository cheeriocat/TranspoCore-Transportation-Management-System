package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import models.DriverAssignment;

public class DriverAssignmentDB {

    private Connection conn;
    private static final String DB_NAME = "project"; // Change to your actual DB name

    public DriverAssignmentDB(Connection conn) {
        this.conn = conn;
    }

    // Add assignment
    public boolean addAssignment(DriverAssignment assignment) {
        String sql = "INSERT INTO " + DB_NAME + ".driver_assignment (shipment_id, driver_id, assignment_date, status) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assignment.getShipmentId());
            stmt.setInt(2, assignment.getDriverId());
            stmt.setTimestamp(3, assignment.getAssignedDate());
            stmt.setString(4, assignment.getStatus());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update assignment
    public boolean updateAssignment(DriverAssignment assignment) {
        String sql = "UPDATE " + DB_NAME + ".driver_assignment SET shipment_id = ?, driver_id = ?, assignment_date = ?, status = ? WHERE assignment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assignment.getShipmentId());
            stmt.setInt(2, assignment.getDriverId());
            stmt.setTimestamp(3, assignment.getAssignedDate());
            stmt.setString(4, assignment.getStatus());
            stmt.setInt(5, assignment.getAssignmentId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete assignment
    public boolean deleteAssignment(int assignmentId) {
        String sql = "DELETE FROM " + DB_NAME + ".driver_assignment WHERE assignment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assignmentId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get assignment by ID
    public DriverAssignment getAssignmentById(int assignmentId) {
        String sql = "SELECT * FROM " + DB_NAME + ".driver_assignment WHERE assignment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, assignmentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    return new DriverAssignment(
                        rs.getInt("assignment_id"),
                        rs.getInt("shipment_id"),
                        rs.getInt("driver_id"),
                        rs.getTimestamp("assignment_date"),
                        rs.getString("status")
                    );
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    // Get all assignments for a shipment
    public List<DriverAssignment> getAssignmentsByShipment(int shipmentId) {
        List<DriverAssignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM " + DB_NAME + ".driver_assignment WHERE shipment_id = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, shipmentId);
            try (ResultSet rs = stmt.executeQuery()) {
                while (rs.next()) {
                    DriverAssignment assignment = new DriverAssignment(
                        rs.getInt("assignment_id"),
                        rs.getInt("shipment_id"),
                        rs.getInt("driver_id"),
                        rs.getTimestamp("assignment_date"),
                        rs.getString("status")
                    );
                    assignments.add(assignment);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }

    // Get all assignments (admin overview)
    public List<DriverAssignment> getAllAssignments() {
        List<DriverAssignment> assignments = new ArrayList<>();
        String sql = "SELECT * FROM " + DB_NAME + ".driver_assignment";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DriverAssignment assignment = new DriverAssignment(
                    rs.getInt("assignment_id"),
                    rs.getInt("shipment_id"),
                    rs.getInt("driver_id"),
                    rs.getTimestamp("assignment_date"),
                    rs.getString("status")
                );
                assignments.add(assignment);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return assignments;
    }
}

