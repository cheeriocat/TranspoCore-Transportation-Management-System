package dao;

import Dbconnection.DBConnection;
import models.ShipmentProgress;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ShipmentProgressDB {

    private final Connection conn = DBConnection.getConnection();

    public boolean addProgress(ShipmentProgress p) {
        String sql = "INSERT INTO shipment_progress (shipmentId, currentLocation, status, estimatedArrival, delayReason) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, p.getShipmentId());
            stmt.setString(2, p.getCurrentLocation());
            stmt.setString(3, p.getStatus());
            stmt.setTimestamp(4, p.getEstimatedArrival());
            stmt.setString(5, p.getDelayReason());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update shipment progress by progressId
    public boolean updateProgress(ShipmentProgress p) {
        String sql = "UPDATE shipment_progress SET currentLocation = ?, status = ?, estimatedArrival = ?, delayReason = ? WHERE progressId = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, p.getCurrentLocation());
            stmt.setString(2, p.getStatus());
            stmt.setTimestamp(3, p.getEstimatedArrival());
            stmt.setString(4, p.getDelayReason());
            stmt.setInt(5, p.getProgressId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete shipment progress by progressId
    public boolean deleteProgress(int progressId) {
        String sql = "DELETE FROM shipment_progress WHERE progressId = ?";
        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, progressId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Load all progress for a shipmentId ordered by progressId desc
    public List<ShipmentProgress> getProgressByShipmentId(int shipmentId) {
        List<ShipmentProgress> list = new ArrayList<>();
        String sql = "SELECT * FROM shipment_progress WHERE shipmentId = ? ORDER BY progressId DESC";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, shipmentId);
            ResultSet rs = stmt.executeQuery();
            while (rs.next()) {
                ShipmentProgress p = new ShipmentProgress();
                p.setProgressId(rs.getInt("progressId"));
                p.setShipmentId(rs.getInt("shipmentId"));
                p.setCurrentLocation(rs.getString("currentLocation"));
                p.setStatus(rs.getString("status"));
                p.setEstimatedArrival(rs.getTimestamp("estimatedArrival"));
                p.setDelayReason(rs.getString("delayReason"));
                list.add(p);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public List<ShipmentProgress> getAllProgress() {
        List<ShipmentProgress> list = new ArrayList<>();
        String sql = "SELECT * FROM shipment_progress";

        try (Connection conn = DBConnection.getConnection(); PreparedStatement stmt = conn.prepareStatement(sql); ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                ShipmentProgress sp = new ShipmentProgress();
                sp.setProgressId(rs.getInt("progressId"));
                sp.setShipmentId(rs.getInt("shipmentId"));
                sp.setCurrentLocation(rs.getString("currentLocation"));
                sp.setStatus(rs.getString("status"));
                sp.setEstimatedArrival(rs.getTimestamp("estimatedArrival"));
                sp.setDelayReason(rs.getString("delayReason"));

                list.add(sp);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return list;
    }

}
