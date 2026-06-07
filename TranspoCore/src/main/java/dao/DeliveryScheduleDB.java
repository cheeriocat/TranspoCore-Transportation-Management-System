package dao;

import Dbconnection.DBConnection;
import models.DeliverySchedule;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DeliveryScheduleDB {

    private Connection conn = DBConnection.getConnection();

    // Add a new delivery schedule
    public boolean addSchedule(DeliverySchedule schedule) {
        String sql = "INSERT INTO delivery_schedule (shipmentId, scheduledDeliveryTime) VALUES (?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, schedule.getShipmentId());
            stmt.setTimestamp(2, Timestamp.valueOf(schedule.getScheduledDeliveryTime()));
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update an existing delivery schedule
    public boolean updateSchedule(DeliverySchedule schedule) {
        String sql = "UPDATE delivery_schedule SET scheduledDeliveryTime=? WHERE scheduleId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setTimestamp(1, Timestamp.valueOf(schedule.getScheduledDeliveryTime()));
            stmt.setInt(2, schedule.getScheduleId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a delivery schedule by scheduleId
    public boolean deleteSchedule(int scheduleId) {
        String sql = "DELETE FROM delivery_schedule WHERE scheduleId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, scheduleId);
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all delivery schedules
    public List<DeliverySchedule> getAllSchedules() {
        List<DeliverySchedule> list = new ArrayList<>();
        String sql = "SELECT * FROM delivery_schedule";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                DeliverySchedule ds = new DeliverySchedule();
                ds.setScheduleId(rs.getInt("scheduleId"));
                ds.setShipmentId(rs.getInt("shipmentId"));
                ds.setScheduledDeliveryTime(rs.getTimestamp("scheduledDeliveryTime").toLocalDateTime());
                list.add(ds);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get delivery schedule by shipmentId
    public DeliverySchedule getScheduleByShipmentId(int shipmentId) {
        String sql = "SELECT * FROM delivery_schedule WHERE shipmentId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, shipmentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    DeliverySchedule ds = new DeliverySchedule();
                    ds.setScheduleId(rs.getInt("scheduleId"));
                    ds.setShipmentId(rs.getInt("shipmentId"));
                    ds.setScheduledDeliveryTime(rs.getTimestamp("scheduledDeliveryTime").toLocalDateTime());
                    return ds;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
