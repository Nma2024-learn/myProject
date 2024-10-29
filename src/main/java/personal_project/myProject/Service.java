package personal_project.myProject;

public class Service {
    private int serviceId;
    private String serviceName; // Renamed for clarity
    private double price;

    // Constructor
    public Service(int serviceId, String serviceName, double price) {
        this.serviceId = serviceId;
        this.serviceName = serviceName;
        this.price = price;
    }

    // Getters
    public int getServiceId() {
        return serviceId;
    }

    public String getServiceName() { // Updated getter name
        return serviceName;
    }

    public double getPrice() {
        return price;
    }

    @Override
    public String toString() {
        return "Service ID: " + serviceId + ", Name: " + serviceName + ", Price: $" + price;
    }
}
