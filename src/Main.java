import java.util.*;

class BankAccount {
    int accountNumber;
    String username;
    int balance;

    BankAccount(int accountNumber, String username, int balance) {
        this.accountNumber = accountNumber;
        this.username = username;
        this.balance = balance;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        LinkedList<BankAccount> accounts = new LinkedList<>();
        Stack<String> history = new Stack<>();
        Queue<String> bills = new LinkedList<>();
        Queue<BankAccount> requests = new LinkedList<>();

        while (true) {
            System.out.println("1 Add account");
            System.out.println("2 Show accounts");
            System.out.println("3 Search");
            System.out.println("4 Deposit");
            System.out.println("5 Withdraw");
            System.out.println("6 Add bill");
            System.out.println("7 Process bill");
            System.out.println("8 Add request");
            System.out.println("9 Process request");
            System.out.println("10 Exit");

            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Number: ");
                int num = sc.nextInt();
                System.out.print("Name: ");
                String name = sc.next();
                System.out.print("Balance: ");
                int bal = sc.nextInt();

                accounts.add(new BankAccount(num, name, bal));
                System.out.println("Added");
            }

            else if (choice == 2) {
                for (BankAccount a : accounts) {
                    System.out.println(a.username + " " + a.balance);
                }
            }

            else if (choice == 3) {
                System.out.print("Name: ");
                String name = sc.next();

                for (BankAccount a : accounts) {
                    if (a.username.equals(name)) {
                        System.out.println("Found " + a.balance);
                    }
                }
            }

            else if (choice == 4) {
                System.out.print("Name: ");
                String name = sc.next();
                System.out.print("Money: ");
                int m = sc.nextInt();

                for (BankAccount a : accounts) {
                    if (a.username.equals(name)) {
                        a.balance += m;
                        history.push("Deposit " + m + " to " + name);
                        System.out.println("New balance " + a.balance);
                    }
                }
            }

            else if (choice == 5) {
                System.out.print("Name: ");
                String name = sc.next();
                System.out.print("Money: ");
                int m = sc.nextInt();

                for (BankAccount a : accounts) {
                    if (a.username.equals(name)) {
                        a.balance -= m;
                        history.push("Withdraw " + m + " from " + name);
                        System.out.println("New balance " + a.balance);
                    }
                }
            }

            else if (choice == 6) {
                System.out.print("Bill: ");
                String b = sc.next();
                bills.add(b);
            }

            else if (choice == 7) {
                if (!bills.isEmpty()) {
                    System.out.println("Processing " + bills.poll());
                }
            }

            else if (choice == 8) {
                System.out.print("Name: ");
                String name = sc.next();
                requests.add(new BankAccount(0, name, 0));
            }

            else if (choice == 9) {
                if (!requests.isEmpty()) {
                    BankAccount a = requests.poll();
                    accounts.add(a);
                    System.out.println("Account created for " + a.username);
                }
            }

            else if (choice == 10) {
                break;
            }
        }
    }
}