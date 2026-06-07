package services;

import Dbconnection.DBConnection;
import Dbconnection.EmailUtility;
import dao.ShipmentDB;
import jakarta.mail.MessagingException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;
import models.Shipment;

public class ShipmentService {

    private ShipmentDB dao = new ShipmentDB();

    private void sendEmail(String to, String subject, String body) {
        try {
            EmailUtility.sendEmail(to, subject, body);
        } catch (MessagingException e) {
            e.printStackTrace(); // handle messaging exception here
        } catch (Exception e) {
            e.printStackTrace(); // handle other exceptions here
        }
    }

    public boolean addShipment(Shipment s) {
        boolean result = dao.addShipment(s);
        if (result) {
            String subject = "Shipment Confirmation from TransapoCore";
            String body = "Hi " + s.getSenderName() + ",\n\nYour shipment has been successfully added.\n\nThank you!";
            sendEmail(s.getSenderEmail(), subject, body);
        }
        return result;
    }

    public boolean updateShipment(Shipment s) {
        boolean result = dao.updateShipment(s);
        if (result) {
            String subject = "Shipment Update Notification";
            String body = "Hi " + s.getSenderName() + ",\n\nYour shipment details have been updated.\n\nThank you!";
            sendEmail(s.getSenderEmail(), subject, body);
        }
        return result;
    }

    public boolean deleteShipment(int shipmentId) {
        Shipment s = dao.getShipmentById(shipmentId);
        if (s == null) {
            return false;
        }

        boolean result = dao.deleteShipment(shipmentId);

        if (result) {
            String subject = "Shipment Cancellation Notice";
            String body = "Hi " + s.getSenderName() + ",\n\nYour shipment has been deleted from the system.\n\nThank you!";
            sendEmail(s.getSenderEmail(), subject, body);  // sendEmail handles exceptions internally
        }

        return result;
    }

    public List<Shipment> getAllShipments() {
        return dao.getAllShipments();
    }
}
