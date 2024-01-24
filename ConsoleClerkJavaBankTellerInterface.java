package ConsoleClerkJavaBankTellerInterface;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class User {
    private String username;
    private String password;

    public User(String username, String password) {
        this.username = username;
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }
}

class UserManager {
    private Map<String, User> users;

    public UserManager() {
        users = new HashMap<>();
    }

    public void addUser(String username, String password) {
        User newUser = new User(username, password);
        users.put(username, newUser);
        System.out.println("User registered successfully!");
    }

    public User getUser(String username) {
        return users.get(username);
    }
}

class BankTeller {
    private String accountNumber;
    private double balance;

    public BankTeller(String accountNumber, double initialBalance) {
        this.accountNumber = accountNumber;
        this.balance = initialBalance;
    }

    public void deposit(double amount) {
        balance += amount;
        System.out.println("Deposit successful. New balance: " + balance);
    }

    public void withdraw(double amount) {
        if (amount <= balance) {
            balance -= amount;
            System.out.println("Withdrawal successful. New balance: " + balance);
        } else {
            System.out.println("Insufficient funds.");
        }
    }

    public double getBalance() {
        return balance;
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        UserManager userManager = new UserManager();
        BankTeller myAccount = null;
//You need to sign up first to proceed 
        while (true) {
            System.out.println("1. Sign Up\n2. Login\n3. Exit");
            System.out.print("Choose an option: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter username:");
                    String signUpUsername = scanner.nextLine();
                    System.out.print("Enter password:");
                    String signUpPassword = scanner.nextLine();
                    userManager.addUser(signUpUsername, signUpPassword);
                    break;

                case 2:
                    System.out.print("Enter username:");
                    String loginUsername = scanner.nextLine();
                    User loginUser = userManager.getUser(loginUsername);
                    if (loginUser != null) {
                        System.out.print("Enter password:");
                        String loginPassword = scanner.nextLine();
                        if (loginUser.getPassword().equals(loginPassword)) {
                            System.out.println("Login successful!");
                            myAccount = new BankTeller(loginUsername, 0.0);
                        } else {
                            System.out.println("Invalid password. Please try again.");
                        }
                    } else {
                        System.out.println("User not found. Please sign up.");
                    }
                    break;

                case 3:
                    System.out.println("Exiting program. Goodbye!");
                    System.exit(0);
                    break;

                default:
                    System.out.println("Invalid choice. Please enter 1, 2, or 3.");
            }

            if (myAccount != null) {
                System.out.println("Welcome to the Bank Teller System!");
                // Perform bank transactions
                while (true) {
                    System.out.println("\n1. Withdraw");
                    System.out.println("2. Deposit");
                    System.out.println("3. Check Balance");
                    System.out.println("4. Exit");
                    System.out.print("Enter your choice: ");

                    int bankChoice = scanner.nextInt();

                    switch (bankChoice) {
                        case 1:
                            System.out.print("Enter withdrawal amount: ");
                            double withdrawalAmount = scanner.nextDouble();
                            myAccount.withdraw(withdrawalAmount);
                            break;

                        case 2:
                            System.out.print("Enter deposit amount: ");
                            double depositAmount = scanner.nextDouble();
                            myAccount.deposit(depositAmount);
                            break;

                        case 3:
                            System.out.println("Current balance: " + myAccount.getBalance());
                            break;

                        case 4:
                            System.out.println("Thank you for using the Bank Teller System. Goodbye!");
                            System.exit(0);

                        default:
                            System.out.println("Invalid choice. Please enter a valid option.");
                    }
                }
            }
        }
    }
}
