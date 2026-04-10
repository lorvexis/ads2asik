import java.util.Scanner;

public class Main {

    static Scanner scanner = new Scanner(System.in);
    static AccountService service = new AccountService();

    public static void main(String[] args) {

        PhysicalStorage.demonstrateArray();

        System.out.println("\n=============================");
        System.out.println("  Welcome to Simple Bank App");
        System.out.println("=============================");

        boolean running = true;
        while (running) {
            System.out.println("\n--- Main Menu ---");
            System.out.println("1 - Enter Bank");
            System.out.println("2 - Enter ATM");
            System.out.println("3 - Admin Area");
            System.out.println("4 - Exit");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    bankMenu();
                    break;
                case "2":
                    atmMenu();
                    break;
                case "3":
                    adminMenu();
                    break;
                case "4":
                    System.out.println("Goodbye!");
                    running = false;
                    break;
                default:
                    System.out.println("Invalid option. Please try again.");
            }
        }
    }

    static void bankMenu() {
        boolean inBank = true;
        while (inBank) {
            System.out.println("\n--- Bank Menu ---");
            System.out.println("1 - Open new account (submit request)");
            System.out.println("2 - Deposit money");
            System.out.println("3 - Withdraw money");
            System.out.println("4 - View all accounts");
            System.out.println("5 - Search account by username");
            System.out.println("6 - Back to main menu");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter account number: ");
                    String accNum = scanner.nextLine().trim();
                    System.out.print("Enter username: ");
                    String username = scanner.nextLine().trim();
                    System.out.print("Enter initial balance: ");
                    double balance = readDouble();
                    BankAccount newAcc = new BankAccount(accNum, username, balance);
                    service.submitAccountRequest(newAcc);
                    break;
                case "2":
                    System.out.print("Enter username: ");
                    String depUser = scanner.nextLine().trim();
                    System.out.print("Enter deposit amount: ");
                    double depAmount = readDouble();
                    service.deposit(depUser, depAmount);
                    break;
                case "3":
                    System.out.print("Enter username: ");
                    String witUser = scanner.nextLine().trim();
                    System.out.print("Enter withdraw amount: ");
                    double witAmount = readDouble();
                    service.withdraw(witUser, witAmount);
                    break;
                case "4":
                    service.displayAllAccounts();
                    break;
                case "5":
                    System.out.print("Enter username to search: ");
                    String searchName = scanner.nextLine().trim();
                    BankAccount found = service.searchByUsername(searchName);
                    if (found != null) {
                        System.out.println("Found: ");
                        found.display();
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case "6":
                    inBank = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    static void atmMenu() {
        boolean inATM = true;
        while (inATM) {
            System.out.println("\n--- ATM Menu ---");
            System.out.println("1 - Balance enquiry");
            System.out.println("2 - Withdraw");
            System.out.println("3 - Back to main menu");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    System.out.print("Enter username: ");
                    String name = scanner.nextLine().trim();
                    BankAccount acc = service.searchByUsername(name);
                    if (acc != null) {
                        System.out.println("Balance for " + acc.username + ": " + acc.balance);
                    } else {
                        System.out.println("Account not found.");
                    }
                    break;
                case "2":
                    System.out.print("Enter username: ");
                    String witUser = scanner.nextLine().trim();
                    System.out.print("Enter amount to withdraw: ");
                    double amount = readDouble();
                    service.withdraw(witUser, amount);
                    break;
                case "3":
                    inATM = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    static void adminMenu() {
        boolean inAdmin = true;
        while (inAdmin) {
            System.out.println("\n--- Admin Menu ---");
            System.out.println("1 - View pending account requests");
            System.out.println("2 - Process next account request");
            System.out.println("3 - View bill payment queue");
            System.out.println("4 - Process next bill payment");
            System.out.println("5 - Back to main menu");
            System.out.print("Choose: ");

            String choice = scanner.nextLine().trim();

            switch (choice) {
                case "1":
                    service.displayPendingRequests();
                    break;
                case "2":
                    service.processNextAccountRequest();
                    break;
                case "3":
                    service.displayBillQueue();
                    break;
                case "4":
                    service.processNextBill();
                    break;
                case "5":
                    inAdmin = false;
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    static double readDouble() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Double.parseDouble(input);
            } catch (NumberFormatException e) {
                System.out.print("Invalid number. Try again: ");
            }
        }
    }
}
