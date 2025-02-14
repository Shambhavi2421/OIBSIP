import java.util.*;

public class ATMsystem {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter User ID: ");
        String userId = scanner.next();
        System.out.print("Enter PIN: ");
        String userPin = scanner.next();

        User user1 = new User("user111", "1111", 1000);
        User user2 = new User("user222", "2222", 2000);
        
        if (user1.authenticate(userId, userPin)) {
            ATM atm = new ATM(user1);
            atm.start();
        } else if (user2.authenticate(userId, userPin)) {
            ATM atm = new ATM(user2);
            atm.start();
        }  
        else {
            System.out.println("Authentication failed!");
        }
    }
}

class User {
    public String userId;
    public String userPin;
    public double bal;
    public List<String> transactionHistory;

    public User(String userId, String userPin, double bal) {
        this.userId = userId;
        this.userPin = userPin;
        this.bal = bal;
        this.transactionHistory = new ArrayList<>();
    }

    public boolean authenticate(String userId, String userPin) {
        return this.userId.equals(userId) && this.userPin.equals(userPin);
    }
}

class Deposit{
    public void deposit(double amount) {
        bal += amount;
        transactionHistory.add("Deposited: " + amount);
        System.out.println("Deposit successful! New balance: " + bal);
    }
}

class Withdraw {
    public void withdraw(double amount) {
        if (amount > bal) {
            System.out.println("Insufficient balance!");
        } else {
            bal -= amount;
            transactionHistory.add("Withdrew: " + amount);
            System.out.println("Withdrawal successful! New balance: " + bal);
        }
    }
}

class Transfer {
   
    public void transfer(User receiver, double amount) {
        if (amount > bal) {
            System.out.println("Insufficient balance!");
        } else {
            bal -= amount;
            receiver.bal += amount;
            transactionHistory.add("Transferred: " + amount + " to " + receiver.userId);
            receiver.transactionHistory.add("Received: " + amount + " from " + this.userId);
            System.out.println("Transfer successful! New balance: " + bal);
        }
    }
}

class TransactionHistory{

    public void showTransactionHistory() {
        System.out.println("Transaction History:");
        for (String transaction : transactionHistory) {
            System.out.println(transaction);
        }
    	}
	}
}

class ATM extends ATMsystem {
    private User user;
    private Scanner scanner;

    public ATM(User user) {
        this.user = user;
        this.scanner = new Scanner(System.in);
    }

    public void start() {
        while (true) {
            System.out.println("\nATM Menu:");
            System.out.println("1. Transactions History");
            System.out.println("2. Withdraw");
            System.out.println("3. Deposit");
            System.out.println("4. Transfer");
            System.out.println("5. Quit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    user.showTransactionHistory();
                    break;
                case 2:
                    System.out.print("Enter withdrawal amount: ");
                    double withdrawAmount = scanner.nextDouble();
                    user.withdraw(withdrawAmount);
                    break;
                case 3:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    user.deposit(depositAmount);
                    break;
                case 4:
                    System.out.print("Enter recipient user ID: ");
                    String receiverId = scanner.next();
                    System.out.print("Enter amount to transfer: ");
                    double transferAmount = scanner.nextDouble();
                    
                    User receiver = new User(receiverId, "0000", 0);
                    user.transfer(receiver, transferAmount);
                    break;
                case 5:
                    System.out.println("Thank you for using our ATM!");
                    return;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}


