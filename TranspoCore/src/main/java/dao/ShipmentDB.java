package dao;

import Dbconnection.DBConnection;
import static Dbconnection.EmailUtility.sendEmail;
import jakarta.mail.MessagingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.Shipment;

public class ShipmentDB {

    private Connection conn = DBConnection.getConnection();

    public boolean addShipment(Shipment s) {
        String sql = "INSERT INTO shipments (senderName, receiverName, packageContents, deliveryStatus, senderEmail, shipmentDate, weight, deliveryAddress) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getSenderName());
            stmt.setString(2, s.getReceiverName());
            stmt.setString(3, s.getPackageContents());
            stmt.setString(4, s.getDeliveryStatus());
            stmt.setString(5, s.getSenderEmail());
            stmt.setString(6, s.getShipmentDate());
            stmt.setDouble(7, s.getWeight());
            stmt.setString(8, s.getDeliveryAddress());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean updateShipment(Shipment s) {
        String sql = "UPDATE shipments SET senderName=?, receiverName=?, packageContents=?, deliveryStatus=?, senderEmail=?, shipmentDate=?, weight=?, deliveryAddress=? WHERE shipmentId=?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, s.getSenderName());
            stmt.setString(2, s.getReceiverName());
            stmt.setString(3, s.getPackageContents());
            stmt.setString(4, s.getDeliveryStatus());
            stmt.setString(5, s.getSenderEmail());
            stmt.setString(6, s.getShipmentDate());
            stmt.setDouble(7, s.getWeight());
            stmt.setString(8, s.getDeliveryAddress());
            stmt.setInt(9, s.getShipmentId());
            return stmt.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public boolean deleteShipment(int shipmentId) {
        String deleteDriverAssignment = "DELETE FROM driver_assignment WHERE shipment_id = ?";
        String deleteShipment = "DELETE FROM shipments WHERE shipmentId = ?";
        try (
                PreparedStatement stmt1 = conn.prepareStatement(deleteDriverAssignment); PreparedStatement stmt2 = conn.prepareStatement(deleteShipment)) {
            conn.setAutoCommit(false); // Begin transaction

            stmt1.setInt(1, shipmentId);
            stmt1.executeUpdate();

            stmt2.setInt(1, shipmentId);
            int rows = stmt2.executeUpdate();

            conn.commit(); // Commit transaction
            return rows > 0;
        } catch (SQLException e) {
            try {
                conn.rollback();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
            e.printStackTrace();
            return false;
        } finally {
            try {
                conn.setAutoCommit(true);
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public List<Shipment> getAllShipments() {
        List<Shipment> list = new ArrayList<>();
        String sql = "SELECT * FROM shipments";
        try (Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                Shipment s = new Shipment();
                s.setShipmentId(rs.getInt("shipmentId"));
                s.setSenderName(rs.getString("senderName"));
                s.setReceiverName(rs.getString("receiverName"));
                s.setPackageContents(rs.getString("packageContents"));
                s.setDeliveryStatus(rs.getString("deliveryStatus"));
                s.setSenderEmail(rs.getString("senderEmail"));
                s.setShipmentDate(rs.getString("shipmentDate"));
                s.setWeight(rs.getDouble("weight"));
                s.setDeliveryAddress(rs.getString("deliveryAddress"));
                list.add(s);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return list;
    }

    public Shipment getShipmentById(int shipmentId) {
        String sql = "SELECT * FROM shipments WHERE shipmentId = ?";
        try (PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setInt(1, shipmentId);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    Shipment s = new Shipment();
                    s.setShipmentId(rs.getInt("shipmentId"));
                    s.setSenderName(rs.getString("senderName"));
                    s.setReceiverName(rs.getString("receiverName"));
                    s.setPackageContents(rs.getString("packageContents"));
                    s.setDeliveryStatus(rs.getString("deliveryStatus"));
                    s.setSenderEmail(rs.getString("senderEmail"));
                    s.setShipmentDate(rs.getString("shipmentDate"));
                    s.setWeight(rs.getDouble("weight"));
                    s.setDeliveryAddress(rs.getString("deliveryAddress"));
                    return s;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;

    }
}
