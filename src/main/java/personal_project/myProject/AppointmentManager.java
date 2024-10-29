
	package personal_project.myProject;

	import java.io.*;
	import java.util.ArrayList;
	import java.util.List;

	// Manages appointments, handling booking, canceling, rescheduling, and file operations for persistent storage.
	public class AppointmentManager {
	    private List<Appointment> appointments;  // List to store appointments
	    private int appointmentCounter = 1;      // Counter for generating unique appointment IDs
	    private static final String FILE_NAME = "appointments.txt";  //  This File name for storing appointments
	    private ServiceManager serviceManager;   // Reference to ServiceManager for fetching services

	    // Constructor initializes the appointments list and loads existing appointments from the file
	    public AppointmentManager(ServiceManager serviceManager) {
	        this.serviceManager = serviceManager;
	        appointments = new ArrayList<>();
	        loadAppointments();
	    }

	    // Books a new appointment and saves it to the file
	    public void bookAppointment(Service service, String customerName, String appointmentDate) {
	        Appointment appointment = new Appointment(appointmentCounter++, service, customerName, appointmentDate);
	        appointments.add(appointment);
	        saveAppointments();
	        System.out.println("Appointment booked successfully: " + appointment);
	    }

	    // Displays all scheduled appointments in the console
	    public void viewAppointments() {
	        System.out.println("\n--- All Appointments ---");
	        for (Appointment appointment : appointments) {
	            System.out.println(appointment);
	        }
	    }

	    // Cancels an appointment by ID, removes it from the list, and updates the file
	    public void cancelAppointment(int appointmentId) {
	        boolean removed = appointments.removeIf(appointment -> appointment.getAppointmentId() == appointmentId);
	        if (removed) {
	            saveAppointments();
	            System.out.println("Appointment canceled.");
	        } else {
	            System.out.println("Appointment not found.");
	        }
	    }

	    // Reschedules an existing appointment to a new date by appointment ID and updates the file
	    public void rescheduleAppointment(int appointmentId, String newDate) {
	        for (Appointment appointment : appointments) {
	            if (appointment.getAppointmentId() == appointmentId) {
	                appointment.setAppointmentDate(newDate);  // Updates the appointment date
	                saveAppointments();
	                System.out.println("Appointment rescheduled.");
	                return;
	            }
	        }
	        System.out.println("Appointment not found.");
	    }

	    // Loads appointments from the file; if the file doesn't exist, it creates a new file
	    private void loadAppointments() {
	        File file = new File(FILE_NAME);
	        if (!file.exists()) {
	            try {
	                file.createNewFile();
	                System.out.println("Created a new file for appointments.");
	            } catch (IOException e) {
	                System.out.println("Error creating file: " + e.getMessage());
	            }
	        }

	        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
	            String line;
	            while ((line = br.readLine()) != null) {
	                String[] data = line.split(",");
	                int appointmentId = Integer.parseInt(data[0]);
	                int serviceId = Integer.parseInt(data[1]);
	                String customerName = data[2];
	                String appointmentDate = data[3];

	                Service service = serviceManager.getServiceById(serviceId);
	                if (service != null) {
	                    Appointment appointment = new Appointment(appointmentId, service, customerName, appointmentDate);
	                    appointments.add(appointment);
	                    appointmentCounter = Math.max(appointmentCounter, appointmentId + 1);
	                }
	            }
	        } catch (IOException e) {
	            System.out.println("Error loading appointments: " + e.getMessage());
	        }
	    }

	    // Saves all appointments to the file, overwriting it with the current list of appointments
	    private void saveAppointments() {
	        try (BufferedWriter bw = new BufferedWriter(new FileWriter(FILE_NAME))) {
	            for (Appointment appointment : appointments) {
	                bw.write(appointment.toFileFormat());  // Converts appointment to file format for saving
	                bw.newLine();
	            }
	        } catch (IOException e) {
	            System.out.println("Error saving appointments: " + e.getMessage());
	        }
	    }
	}



