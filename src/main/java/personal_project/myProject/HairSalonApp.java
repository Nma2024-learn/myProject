package personal_project.myProject;           
import java.util.Scanner;

// Main application class, providing the menu and managing user interactions.
public class HairSalonApp {
    private ServiceManager serviceManager;  // Manages available salon services
    private AppointmentManager appointmentManager; // Manages appointments
    private Scanner scanner; // For user input

    // Constructor to initialize the managers and scanner
    public HairSalonApp() {
        this.serviceManager = new ServiceManager();
        this.appointmentManager = new AppointmentManager(serviceManager);
        this.scanner = new Scanner(System.in);
        start(); // Call the start method to display the menu
    }

    // Main method to run the application
    public static void main(String[] args) {
        new HairSalonApp(); // Create an instance of HairSalonApp
    }

    // Starts the application and handles the main menu loop
    private void start() {
        int choice; // Variable to store user choice
        do {
            displayMenu();
            choice = getUserInput();
            switch (choice) {
                case 1:
                    serviceManager.displayServices();
                    break;
                case 2:
                    handleBookAppointment();
                    break;
                case 3:
                    handleCancelAppointment();
                    break;
                case 4:
                    handleRescheduleAppointment();
                    break;
                case 5:
                    appointmentManager.viewAppointments();
                    break;
                case 0:
                    System.out.println("Exiting the Hair Salon Management System. Thank you!");
                    break; // Exit the loop to stop the application
                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        } while (choice != 0); // Continue until the user chooses to exit
    }

    // Displays menu options
    private void displayMenu() {
        System.out.println(Color.BLUE + "\n--- Hair Salon Management System ---" + Color.RESET);
        System.out.println(Color.YELLOW + "1. View Salon Services" + Color.RESET);
        System.out.println(Color.PURPLE + "2. Book an Appointment" + Color.RESET);
        System.out.println(Color.RED + "3. Cancel an Appointment" + Color.RESET);
        System.out.println(Color.BLUE + "4. Reschedule an Appointment" + Color.RESET);
        System.out.println(Color.CYAN + "5. View All Appointments" + Color.RESET);
        System.out.println(Color.BLACK + "0. Exit" + Color.RESET);
        System.out.print("Enter your choice: ");
    }

    // Gets user input and validates it
    private int getUserInput() {
        return Integer.parseInt(scanner.nextLine());
    }

    // Handles booking an appointment
    private void handleBookAppointment() {
        System.out.print("Enter Service ID: ");
        int serviceId = getUserInput();
        Service service = serviceManager.getServiceById(serviceId);
        if (service == null) {
            System.out.println("Invalid Service ID.");
            return;
        }
        System.out.print("Enter your name: ");
        String customerName = scanner.nextLine();
        System.out.print("Enter appointment date (YYYY-MM-DD): ");
        String appointmentDate = scanner.nextLine();
        
        appointmentManager.bookAppointment(service, customerName, appointmentDate);
    }

    // Handles canceling an appointment
    private void handleCancelAppointment() {
        System.out.print("Enter Appointment ID to cancel: ");
        int appointmentId = getUserInput();
        appointmentManager.cancelAppointment(appointmentId);
    }

    // Handles rescheduling an appointment
    private void handleRescheduleAppointment() {
        System.out.print("Enter Appointment ID to reschedule: ");
        int appointmentId = getUserInput();
        System.out.print("Enter new appointment date (YYYY-MM-DD): ");
        String newDate = scanner.nextLine();
        appointmentManager.rescheduleAppointment(appointmentId, newDate);
    }
}
