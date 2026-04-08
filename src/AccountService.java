import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class AccountService {

    LinkedList<BankAccount> accounts = new LinkedList<>();

    Stack<String> transactionHistory = new Stack<>();

    Queue<String> billQueue = new LinkedList<>();

    Queue<BankAccount> accountRequests = new LinkedList<>();


    public void addAccount(BankAccount account) {
        accounts.add(account);
        System.out.println("Account added successfully!");
    }

    public void displayAllAccounts() {
        if (accounts.isEmpty()) {
            System.out.println("No accounts found.");
            return;
        }
        System.out.println("\nAccounts List:");
        int i = 1;
        for (BankAccount acc : accounts) {
            System.out.println(i + ". " + acc.username + " -- Balance: " + acc.balance);
            i++;
        }
    }

    public BankAccount searchByUsername(String username) {
        for (BankAccount acc : accounts) {
            if (acc.username.equalsIgnoreCase(username)) {
                return acc;
            }
        }
        return null;
    }


    public void deposit(String username, double amount) {
        BankAccount acc = searchByUsername(username);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }
        acc.balance += amount;
        System.out.println("Deposited " + amount + " to " + username + ". New balance: " + acc.balance);
        transactionHistory.push("Deposit " + amount + " to " + username);
    }

    public void withdraw(String username, double amount) {
        BankAccount acc = searchByUsername(username);
        if (acc == null) {
            System.out.println("Account not found.");
            return;
        }
        if (acc.balance < amount) {
            System.out.println("Insufficient balance.");
            return;
        }
        acc.balance -= amount;
        System.out.println("Withdrawn " + amount + " from " + username + ". New balance: " + acc.balance);
        transactionHistory.push("Withdraw " + amount + " from " + username);
    }


    public void addTransaction(String transaction) {
        transactionHistory.push(transaction);
        System.out.println("Transaction added: " + transaction);
    }

    public void undoLastTransaction() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions to undo.");
            return;
        }
        String removed = transactionHistory.pop();
        System.out.println("Undo → " + removed + " removed.");
    }

    public void peekLastTransaction() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions.");
            return;
        }
        System.out.println("Last transaction: " + transactionHistory.peek());
    }

    public void addBillPayment(String bill) {
        billQueue.add(bill);
        System.out.println("Added: " + bill);
    }

    public void processNextBill() {
        if (billQueue.isEmpty()) {
            System.out.println("No bills in queue.");
            return;
        }
        System.out.println("Processing: " + billQueue.poll());
        if (!billQueue.isEmpty()) {
            System.out.println("Remaining bills:");
            for (String bill : billQueue) {
                System.out.println("  - " + bill);
            }
        } else {
            System.out.println("No remaining bills.");
        }
    }

    public void displayBillQueue() {
        if (billQueue.isEmpty()) {
            System.out.println("Bill queue is empty.");
            return;
        }
        System.out.println("Bills in queue:");
        for (String bill : billQueue) {
            System.out.println("  - " + bill);
        }
    }


    public void submitAccountRequest(BankAccount account) {
        accountRequests.add(account);
        System.out.println("Request submitted for: " + account.username);
    }

    public void processNextAccountRequest() {
        if (accountRequests.isEmpty()) {
            System.out.println("No pending requests.");
            return;
        }
        BankAccount acc = accountRequests.poll();
        accounts.add(acc);
        System.out.println("Account approved and created for: " + acc.username);
    }

    public void displayPendingRequests() {
        if (accountRequests.isEmpty()) {
            System.out.println("No pending account requests.");
            return;
        }
        System.out.println("Pending account requests:");
        for (BankAccount acc : accountRequests) {
            System.out.println("  - " + acc.username + " (Account#: " + acc.accountNumber + ")");
        }
    }
}
