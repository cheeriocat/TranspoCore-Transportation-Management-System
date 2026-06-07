package services;

import dao.ShipmentProgressDB;
import models.ShipmentProgress;

import java.util.List;

public class ShipmentProgressService {

    private final ShipmentProgressDB dao = new ShipmentProgressDB();

    // Add progress
    public boolean addProgress(ShipmentProgress progress) {
        return dao.addProgress(progress);
    }

    // Update progress
    public boolean updateProgress(ShipmentProgress progress) {
        return dao.updateProgress(progress);
    }

    // Delete progress
    public boolean deleteProgress(int progressId) {
        return dao.deleteProgress(progressId);
    }

    // Get all progress updates for a given shipment
    public List<ShipmentProgress> getProgressByShipmentId(int shipmentId) {
        return dao.getProgressByShipmentId(shipmentId);
    }

    // ✅ Get all shipment progress records
    public List<ShipmentProgress> getAllProgress() {
        return dao.getAllProgress();
    }
}

