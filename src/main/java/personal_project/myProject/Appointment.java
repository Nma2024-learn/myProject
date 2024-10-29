package personal_project.myProject;

public class Appointment {
    private int appointmentId;
    private Service service;
    private String customerName;
    private String date;

    // Constructor
    public Appointment(int appointmentId, Service service, String customerName, String date) {
        this.appointmentId = appointmentId;
        this.service = service;
        this.customerName = customerName;
        this.date = date;
    }

    // Getters
    public int getAppointmentId() {
        return appointmentId;
    }

    public Service getService() {
        return service;
    }

    public String getCustomerName() {
        return customerName;
    }

    public String getDate() {
        return date;
    }

    // Setter for the appointment date
    public void setAppointmentDate(String date) {
        this.date = date; // Set the new date for the appointment
    }

    @Override
    public String toString() {
        return "Appointment ID: " + appointmentId + ", Service: " + service.getServiceName() + ", Customer: " + customerName + ", Date: " + date;
    }

    // Method to convert appointment data to a string format for file storage
    public String toFileFormat() {
        return appointmentId + "," + service.getServiceId() + "," + customerName + "," + date;
    }
}
