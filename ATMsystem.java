
import java.util.*;

class TransactionHistory {
    private List<String> transactions = new ArrayList<>();
    
    public void addTransaction(String transaction) {
        transactions.add(transaction);
    }
    
    public void showHistory() {
        if (transactions.isEmpty()) {
            System.out.println("No transactions yet.");
        } else {
            for (String transaction : transactions) {
                System.out.println(transaction);
            }
        }
    }
}

class Withdraw {
    public double withdrawAmount(TransactionHistory history, double amt, double bal) {
        if (amt > 0 && amt <= bal) {
            bal -= amt;
            history.addTransaction("Withdrawn: Rs." + amt);
            System.out.println("Withdrawal successful. Remaining Balance: Rs." + bal);
        } else {
            System.out.println("Invalid amount or insufficient balance.");
        }
        return bal;
    }
}

class Deposit {
    public double depositAmount(TransactionHistory history, double amount, double balance) {
        if (amount > 0) {
            balance += amount;
            history.addTransaction("Deposited: Rs" + amount);
            System.out.println("Deposit successful. Updated Balance: Rs." + balance);
        } else {
            System.out.println("Invalid deposit amount.");
        }
        return balance;
    }
}

class Transfer {
    public double transferAmount(TransactionHistory history, double amount, double balance, String recipient) {
        if (amount > 0 && amount <= balance) {
            balance -= amount;
            history.addTransaction("Transferred: Rs" + amount + " to " + recipient);
            System.out.println("Transfer successful to " + recipient + ". Remaining Balance: Rs." + balance);
        } else {
            System.out.println("Invalid transfer amount or insufficient balance.");
        }
        return balance;
    }
}

class Exit {
    public void exitATM() {
        System.out.println("Exiting ATM System. Thank you!");
        System.exit(0);
    }
}

public class ATMsystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        TransactionHistory history = new TransactionHistory();
        Withdraw withdraw = new Withdraw();
        Deposit deposit = new Deposit();
        Transfer transfer = new Transfer();
        Exit exit = new Exit();
        double balance = 1000.0;
        
        System.out.print("Enter User ID: ");
        String userId = scanner.next();
        System.out.print("Enter PIN: ");
        String pin = scanner.next();
        
        if (!userId.equals("user123") || !pin.equals("1234")) {
            System.out.println("Invalid credentials. Exiting.");
            return;
        }
        
        while (true) {
            System.out.println("\n***------ATM System------***");
            System.out.println("1. Transaction History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Exit");
            System.out.print("Enter choice: ");
            System.out.println("\n**************************");
            int choice = scanner.nextInt();
            
            switch (choice) {
                case 1:
                    history.showHistory();
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double withdrawAmount = scanner.nextDouble();
                    balance = withdraw.withdrawAmount(history, withdrawAmount, balance);
                    break;
                case 3:
                    System.out.print("Enter amount to deposit: ");
                    double depositAmount = scanner.nextDouble();
                    balance = deposit.depositAmount(history, depositAmount, balance);
                    break;
                case 4:
                    System.out.print("Enter recipient name: ");
                    String recipient = scanner.next();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    balance = transfer.transferAmount(history, transferAmount, balance, recipient);
                    break;
                case 5:
                    exit.exitATM();
                    break;
                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}

