import java.util.*;

class BankUser {
    private String userId = "user101";
    private String pin = "4321";
    private double balance = 10000.00;
    private List<String> history = new ArrayList<>();

    boolean authenticate(String id, String inputPin) {
        return userId.equals(id) && pin.equals(inputPin);
    }

    void deposit(double amount) {
        balance += amount;
        history.add("Deposited ₹" + amount);
        System.out.println("✅ ₹" + amount + " added to your account.");
    }

    void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            history.add("Withdrew ₹" + amount);
            System.out.println("✅ ₹" + amount + " withdrawn successfully.");
        } else {
            System.out.println("❌ Insufficient funds!");
        }
    }

    void transfer(double amount, String recipientId) {
        if (amount <= balance) {
            balance -= amount;
            history.add("Transferred ₹" + amount + " to " + recipientId);
            System.out.println("✅ ₹" + amount + " transferred to " + recipientId);
        } else {
            System.out.println("❌ Transfer failed: Not enough balance.");
        }
    }

    void showBalance() {
        System.out.println("💰 Current Balance: ₹" + balance);
    }

    void showHistory() {
        System.out.println("\n📜 Transaction History:");
        if (history.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String record : history) {
                System.out.println("- " + record);
            }
        }
    }
}

public class ATMInterfaceApp {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankUser user = new BankUser();

        System.out.println("🏦 Welcome to DigiBank ATM");

        System.out.print("👤 Enter User ID: ");
        String inputId = scanner.nextLine();
        System.out.print("🔒 Enter PIN: ");
        String inputPin = scanner.nextLine();

        if (!user.authenticate(inputId, inputPin)) {
            System.out.println("❌ Login failed! Invalid credentials.");
            return;
        }

        System.out.println("✅ Login successful! Access granted.");

        int option;
        do {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1. View Balance");
            System.out.println("2. Deposit Funds");
            System.out.println("3. Withdraw Cash");
            System.out.println("4. Transfer Money");
            System.out.println("5. Transaction History");
            System.out.println("6. Exit");
            System.out.print("Select an option: ");
            option = scanner.nextInt();

            switch (option) {
                case 1:
                    user.showBalance();
                    break;
                case 2:
                    System.out.print("Enter amount to deposit: ₹");
                    double depositAmt = scanner.nextDouble();
                    user.deposit(depositAmt);
                    break;
                case 3:
                    System.out.print("Enter amount to withdraw: ₹");
                    double withdrawAmt = scanner.nextDouble();
                    user.withdraw(withdrawAmt);
                    break;
                case 4:
                    System.out.print("Enter recipient ID: ");
                    scanner.nextLine(); // clear buffer
                    String recipient = scanner.nextLine();
                    System.out.print("Enter amount to transfer: ₹");
                    double transferAmt = scanner.nextDouble();
                    user.transfer(transferAmt, recipient);
                    break;
                case 5:
                    user.showHistory();
                    break;
                case 6:
                    System.out.println("👋 Thank you for using DigiBank. Have a nice day!");
                    break;
                default:
                    System.out.println("⚠ Invalid option. Please try again.");
            }

        } while (option != 6);

        scanner.close();
    }
}