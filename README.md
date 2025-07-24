# javadevelopment 
# task1
import java.util.*;

class AccountManager {
    private final String validUser = "admin";
    private final String validPass = "1234";

    boolean verifyCredentials(String user, String pass) {
        return user.equals(validUser) && pass.equals(validPass);
    }
}

class Booking {
    String passengerName;
    String origin;
    String destination;
    String trainTitle;
    String travelDate;
    String bookingCode;

    Booking(String name, String from, String to, String train, String date, String code) {
        this.passengerName = name;
        this.origin = from;
        this.destination = to;
        this.trainTitle = train;
        this.travelDate = date;
        this.bookingCode = code;
    }
}

class BookingService {
    Map<String, Booking> bookingRecords = new HashMap<>();
    Random random = new Random();

    String createBooking(String name, String from, String to, String train, String date) {
        String code = "TKT" + (random.nextInt(9000) + 1000);
        Booking newBooking = new Booking(name, from, to, train, date, code);
        bookingRecords.put(code, newBooking);
        System.out.println("✅ Ticket booked successfully! Your code: " + code);
        return code;
    }

    void removeBooking(String code) {
        if (bookingRecords.containsKey(code)) {
            System.out.println("⚠ Cancelling ticket for: " + bookingRecords.get(code).passengerName);
            bookingRecords.remove(code);
            System.out.println("✔ Ticket cancelled.");
        } else {
            System.out.println("❌ No booking found with code: " + code);
        }
    }

    void showBooking(String code) {
        Booking b = bookingRecords.get(code);
        if (b != null) {
            System.out.println("\n--- Ticket Info ---");
            System.out.println("Passenger: " + b.passengerName);
            System.out.println("Train: " + b.trainTitle);
            System.out.println("Route: " + b.origin + " ➡ " + b.destination);
            System.out.println("Date: " + b.travelDate);
            System.out.println("Code: " + b.bookingCode);
        } else {
            System.out.println("🔍 Code not found.");
        }
    }
}

public class ReservationApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        AccountManager login = new AccountManager();
        BookingService service = new BookingService();

        System.out.println("=== Welcome to TravelMate Booking System ===");

        
        System.out.print("👤 Username: ");
        String user = scanner.next();
        System.out.print("🔐 Password: ");
        String pass = scanner.next();

        if (!login.verifyCredentials(user, pass)) {
            System.out.println("🚫 Access Denied. Try again.");
            return;
        }

        int menuOption;
        do {
            System.out.println("\n📋 Menu:");
            System.out.println("1. Book a Ticket");
            System.out.println("2. Cancel a Ticket");
            System.out.println("3. View Ticket");
            System.out.println("4. Exit");
            System.out.print("Choose option: ");
            menuOption = scanner.nextInt();

            scanner.nextLine(); 

            switch (menuOption) {
                case 1:
                    System.out.print("Enter Passenger Name: ");
                    String name = scanner.nextLine();
                    System.out.print("Start Station: ");
                    String from = scanner.nextLine();
                    System.out.print("End Station: ");
                    String to = scanner.nextLine();
                    System.out.print("Train Name: ");
                    String train = scanner.nextLine();
                    System.out.print("Journey Date: ");
                    String date = scanner.nextLine();
                    service.createBooking(name, from, to, train, date);
                    break;

                case 2:
                    System.out.print("Enter Booking Code: ");
                    String codeToCancel = scanner.nextLine();
                    service.removeBooking(codeToCancel);
                    break;

                case 3:
                    System.out.print("Enter Booking Code: ");
                    String codeToView = scanner.nextLine();
                    service.showBooking(codeToView);
                    break;

                case 4:
                    System.out.println("👋 Thank you for using TravelMate!");
                    break;

                default:
                    System.out.println("❗ Invalid choice. Try again.");
            }

        } while (menuOption != 4);

        scanner.close();
    }
}
