package models;

public class Driver {

    private int driverId;
    private String name;
    private String email;
    private String phone;
    private String assignedRoute;
    private String schedule;
    private String deliveryHistory; // Could be JSON or a formatted string
    
    public Driver(){}

    // Getters and setters
    public int getDriverId() {
        return driverId;
    }

    public void setDriverId(int driverId) {
        this.driverId = driverId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAssignedRoute() {
        return assignedRoute;
    }

    public void setAssignedRoute(String assignedRoute) {
        this.assignedRoute = assignedRoute;
    }

    public String getSchedule() {
        return schedule;
    }

    public void setSchedule(String schedule) {
        this.schedule = schedule;
    }

    public String getDeliveryHistory() {
        return deliveryHistory;
    }

    public void setDeliveryHistory(String deliveryHistory) {
        this.deliveryHistory = deliveryHistory;
    }
}
