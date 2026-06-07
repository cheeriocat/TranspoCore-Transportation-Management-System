package dao;

import Dbconnection.DBConnection;
import Dbconnection.EmailUtility;
import models.Driver;
import jakarta.mail.MessagingException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DriverDB {

    private Connection conn = DBConnection.getConnection();

    // Add a new driver
    public boolean addDriver(Driver d) {
        String sql = "INSERT INTO drivers (name, email, phone, assignedRoute, schedule, deliveryHistory) VALUES (?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, d.getName());
            stmt.setString(2, d.getEmail());
            stmt.setString(3, d.getPhone());
            stmt.setString(4, d.getAssignedRoute());
            stmt.setString(5, d.getSchedule());
            stmt.setString(6, d.getDeliveryHistory());
            boolean result = stmt.executeUpdate() > 0;

            if (result) {
                try {
                    String subject = "Welcome to the Delivery Team";
                    String body = "Hi " + d.getName() + ",\n\nYou have been successfully added as a delivery driver.\n\nThank you!";
                    EmailUtility.sendEmail(d.getEmail(), subject, body);
                } catch (MessagingException e) {
                    e.printStackTrace();  // Or better logging
                }
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Update existing driver
    public boolean updateDriver(Driver d) {
        String sql = "UPDATE drivers SET name=?, email=?, phone=?, assignedRoute=?, schedule=?, deliveryHistory=? WHERE driverId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, d.getName());
            stmt.setString(2, d.getEmail());
            stmt.setString(3, d.getPhone());
            stmt.setString(4, d.getAssignedRoute());
            stmt.setString(5, d.getSchedule());
            stmt.setString(6, d.getDeliveryHistory());
            stmt.setInt(7, d.getDriverId());
            boolean result = stmt.executeUpdate() > 0;

            if (result) {
                try {
                    String subject = "Driver Profile Updated";
                    String body = "Hi " + d.getName() + ",\n\nYour driver profile has been updated.\n\nThank you!";
                    EmailUtility.sendEmail(d.getEmail(), subject, body);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Delete a driver
    public boolean deleteDriver(int driverId) {
        // First get driver info to send email after deletion
        Driver d = getDriverById(driverId);
        if (d == null) {
            return false; // Driver not found
        }

        String sql = "DELETE FROM drivers WHERE driverId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, driverId);
            boolean result = stmt.executeUpdate() > 0;

            if (result) {
                try {
                    String subject = "Driver Account Removed";
                    String body = "Hi " + d.getName() + ",\n\nYour driver account has been removed from the system.\n\nThank you for your service!";
                    EmailUtility.sendEmail(d.getEmail(), subject, body);
                } catch (MessagingException e) {
                    e.printStackTrace();
                }
            }
            return result;

        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Get all drivers
    public List<Driver> getAllDrivers() {
        List<Driver> list = new ArrayList<>();
        String sql = "SELECT * FROM drivers";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Driver d = new Driver();
                d.setDriverId(rs.getInt("driverId"));
                d.setName(rs.getString("name"));
                d.setEmail(rs.getString("email"));
                d.setPhone(rs.getString("phone"));
                d.setAssignedRoute(rs.getString("assignedRoute"));
                d.setSchedule(rs.getString("schedule"));
                d.setDeliveryHistory(rs.getString("deliveryHistory"));
                list.add(d);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    // Get driver by ID
    public Driver getDriverById(int driverId) {
        String sql = "SELECT * FROM drivers WHERE driverId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, driverId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Driver d = new Driver();
                    d.setDriverId(rs.getInt("driverId"));
                    d.setName(rs.getString("name"));
                    d.setEmail(rs.getString("email"));
                    d.setPhone(rs.getString("phone"));
                    d.setAssignedRoute(rs.getString("assignedRoute"));
                    d.setSchedule(rs.getString("schedule"));
                    d.setDeliveryHistory(rs.getString("deliveryHistory"));
                    return d;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
