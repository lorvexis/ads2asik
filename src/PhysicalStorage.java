public class PhysicalStorage {

    public static void demonstrateArray() {
        System.out.println("\n--- Task 6: Physical Data Structure (Array) ---");

        BankAccount[] bankAccounts = new BankAccount[3];

        bankAccounts[0] = new BankAccount("ACC001", "Ali", 150000);
        bankAccounts[1] = new BankAccount("ACC002", "Sara", 220000);
        bankAccounts[2] = new BankAccount("ACC003", "Dias", 80000);

        System.out.println("Bank Accounts stored in Array:");
        for (int i = 0; i < bankAccounts.length; i++) {
            System.out.print((i + 1) + ". ");
            bankAccounts[i].display();
        }
    }
}
