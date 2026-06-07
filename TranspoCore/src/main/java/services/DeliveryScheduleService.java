package services;

import dao.DeliveryScheduleDB;
import models.DeliverySchedule;

import java.util.List;

public class DeliveryScheduleService {

    private DeliveryScheduleDB dao = new DeliveryScheduleDB();

    // Add a new delivery schedule
    public boolean addSchedule(DeliverySchedule schedule) {
        return dao.addSchedule(schedule);
    }

    // Update an existing schedule
    public boolean updateSchedule(DeliverySchedule schedule) {
        return dao.updateSchedule(schedule);
    }

    // Delete a schedule by its ID
    public boolean deleteSchedule(int scheduleId) {
        return dao.deleteSchedule(scheduleId);
    }

    // Get a schedule by its ID
    public DeliverySchedule getScheduleByShipmentId(int shipmentId) {
        return dao.getScheduleByShipmentId(shipmentId);
    }

    // Get all schedules
    public List<DeliverySchedule> getAllSchedules() {
        return dao.getAllSchedules();
    }
}
