package personal_project.myProject;

import java.util.ArrayList;
import java.util.List;

// Manages salon services, offering methods to display and retrieve services by ID.


public class ServiceManager {
    private List<Service> services;

    // Constructor
    public ServiceManager() {
        services = new ArrayList<>();
        // Adding some default services
        services.add(new Service(1, "Haircut", 50.0));
        services.add(new Service(2, "Hair Coloring", 100.0));
        services.add(new Service(3, "Short Braids", 200.0));
        services.add(new Service(4, "Long Braids", 300.0));
    }

    // Displays available services
    public void displayServices() {
        System.out.println("\n--- Available Salon Services ---");
        for (Service service : services) {
            System.out.println(service);
        }
    }

    // Gets a service by ID
    public Service getServiceById(int serviceId) {
        for (Service service : services) {
            if (service.getServiceId() == serviceId) {
                return service;
            }
        }
        return null; // Return null if service not found
    }
}
