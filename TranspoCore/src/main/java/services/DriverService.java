package services;

import dao.DriverDB;
import models.Driver;
import Dbconnection.EmailUtility;
import jakarta.mail.MessagingException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DriverService {

    private final DriverDB dao = new DriverDB();

    public boolean addDriver(Driver d) {
        boolean result = dao.addDriver(d);
        if (result) {
            try {
                String subject = "Welcome to the Delivery Team";
                String body = "Hi " + d.getName() + ",\n\nYou have been successfully added as a delivery driver.\n\nThank you!";
                EmailUtility.sendEmail(d.getEmail(), subject, body);
            } catch (MessagingException e) {
                e.printStackTrace();  // Or use logger
            }
        }
        return result;
    }

    public boolean updateDriver(Driver d) {
        boolean result = dao.updateDriver(d);
        if (result) {
            try {
                String subject = "Driver Profile Updated";
                String body = "Hi " + d.getName() + ",\n\nYour driver profile has been updated.\n\nThank you!";
                EmailUtility.sendEmail(d.getEmail(), subject, body);
            } catch (MessagingException e) {
                Logger.getLogger(DriverService.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return result;
    }

    public boolean deleteDriver(int driverId) {
        Driver d = dao.getDriverById(driverId);
        if (d == null) {
            return false; // Driver not found
        }

        boolean result = dao.deleteDriver(driverId);
        if (result) {
            try {
                String subject = "Driver Account Removed";
                String body = "Hi " + d.getName() + ",\n\nYour driver account has been removed from the system.\n\nThank you for your service!";
                EmailUtility.sendEmail(d.getEmail(), subject, body);
            } catch (MessagingException e) {
                Logger.getLogger(DriverService.class.getName()).log(Level.SEVERE, null, e);
            }
        }
        return result;
    }

    public List<Driver> getAllDrivers() {
        return dao.getAllDrivers();
    }

    public Driver getDriverById(int driverId) {
        return dao.getDriverById(driverId);
    }
}
