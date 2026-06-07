package models;

import java.sql.Timestamp;

public class DriverAssignment {
    private int assignmentId;
    private int shipmentId;
    private int driverId;
    private Timestamp assignedDate;
    private String status; // e.g. "assigned", "completed", "cancelled"
    
    
    public DriverAssignment(int assignmentId, int shipmentId, int driverId, Timestamp assignedDate, String status) {
    this.assignmentId = assignmentId;
    this.shipmentId = shipmentId;
    this.driverId = driverId;
    this.assignedDate = assignedDate;
    this.status = status;
}


    // Getters and setters
    public int getAssignmentId() { return assignmentId; }
    public void setAssignmentId(int assignmentId) { this.assignmentId = assignmentId; }

    public int getShipmentId() { return shipmentId; }
    public void setShipmentId(int shipmentId) { this.shipmentId = shipmentId; }

    public int getDriverId() { return driverId; }
    public void setDriverId(int driverId) { this.driverId = driverId; }

    public Timestamp getAssignedDate() { return assignedDate; }
    public void setAssignedDate(Timestamp assignedDate) { this.assignedDate = assignedDate; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }
}

