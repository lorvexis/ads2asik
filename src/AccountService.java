
public class AccountService {

    MyLinkedList<BankAccount> accounts = new MyLinkedList<>();
    MyStack<String> transactionHistory = new MyStack<>();
    MyQueue<String> billQueue = new MyQueue<>();
    MyQueue<BankAccount> accountRequests = new MyQueue<>();

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
        MyNode<BankAccount> current = accounts.getHead();
        int i = 1;
        while (current != null) {
            System.out.println(i + ". " + current.data.username + " -- Balance: " + current.data.balance);
            current = current.next;
            i++;
        }
    }

    public BankAccount searchByUsername(String username) {
        MyNode<BankAccount> current = accounts.getHead();
        while (current != null) {
            if (current.data.username.equalsIgnoreCase(username)) {
                return current.data;
            }
            current = current.next;
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
            System.out.println("Insufficient funds!");
            return;
        }
        acc.balance -= amount;
        System.out.println("Withdrew " + amount + " from " + username + ". New balance: " + acc.balance);
        transactionHistory.push("Withdraw " + amount + " from " + username);
    }

    public void showTransactionHistory() {
        if (transactionHistory.isEmpty()) {
            System.out.println("No transactions yet.");
            return;
        }
        System.out.println("\nTransaction History (Last first):");
        MyNode<String> current = transactionHistory.getTop();
        while (current != null) {
            System.out.println(" - " + current.data);
            current = current.next;
        }
    }

    public void addBillToQueue(String billName) {
        billQueue.enqueue(billName);
        System.out.println("Bill added: " + billName);
    }

    public void processNextBill() {
        String bill = billQueue.dequeue();
        if (bill == null) {
            System.out.println("No bills to process.");
        } else {
            System.out.println("Processed bill: " + bill);
        }
    }

    public void displayBillQueue() {
        if (billQueue.isEmpty()) {
            System.out.println("Bill queue is empty.");
            return;
        }
        System.out.println("Bills in queue:");
        MyNode<String> current = billQueue.getFront();
        while (current != null) {
            System.out.println("  - " + current.data);
            current = current.next;
        }
    }

    public void submitAccountRequest(BankAccount account) {
        accountRequests.enqueue(account);
        System.out.println("Request submitted for: " + account.username);
    }

    public void processNextAccountRequest() {
        BankAccount acc = accountRequests.dequeue();
        if (acc == null) {
            System.out.println("No pending requests.");
            return;
        }
        accounts.add(acc);
        System.out.println("Account approved and created for: " + acc.username);
    }

    public void displayPendingRequests() {
        if (accountRequests.isEmpty()) {
            System.out.println("No pending account requests.");
            return;
        }
        System.out.println("Pending account requests:");
        MyNode<BankAccount> current = accountRequests.getFront();
        while (current != null) {
            System.out.println("  - " + current.data.username + " (Account#: " + current.data.accountNumber + ")");
            current = current.next;
        }
    }
}