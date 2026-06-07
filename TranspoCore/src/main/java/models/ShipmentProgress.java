/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package models;

import java.sql.Timestamp;

public class ShipmentProgress {
    private int progressId;
    private int shipmentId;
    private String currentLocation;
    private String status;
    private Timestamp estimatedArrival;
    private String delayReason;

    // Getters and setters

    public int getProgressId() { return progressId; }
    public void setProgressId(int progressId) { this.progressId = progressId; }

    public int getShipmentId() { return shipmentId; }
    public void setShipmentId(int shipmentId) { this.shipmentId = shipmentId; }

    public String getCurrentLocation() { return currentLocation; }
    public void setCurrentLocation(String currentLocation) { this.currentLocation = currentLocation; }

    public String getStatus() { return status; }
    public void setStatus(String status) { this.status = status; }

    public Timestamp getEstimatedArrival() { return estimatedArrival; }
    public void setEstimatedArrival(Timestamp estimatedArrival) { this.estimatedArrival = estimatedArrival; }

    public String getDelayReason() { return delayReason; }
    public void setDelayReason(String delayReason) { this.delayReason = delayReason; }
}
