import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

class ATM {
    float balance = 1000;
    int atmNumber = 12345;
    int atmPin = 123;
    HashMap<Double, String> miniStmt = new HashMap<>();

    public void checkAccount() {
        Scanner in = new Scanner(System.in);
        System.out.println("Welcome to ATM Interface.....");
        System.out.print("Enter ATM Number: ");
        int enteredAtmNumber = in.nextInt();
        System.out.print("Enter PIN: ");
        int enteredPin = in.nextInt();
        if (atmNumber == enteredAtmNumber && atmPin == enteredPin) {
            System.out.println("Validation successful");
            System.out.println("1. View Available Balance\n2. Withdraw Amount\n3. Deposit Amount\n4. View Mini-statement\n5. Exit");
            menu();
        } else {
            System.out.println("Incorrect ATM Number or PIN.");
            System.exit(0);
        }
    }

    public void menu() {
        Scanner in = new Scanner(System.in);
        System.out.print("Choose the operation you want to perform: ");
        int choice = in.nextInt();
        switch (choice) {
            case 1:
                checkBalance();
                break;
            case 2:
                withdrawMoney();
                break;
            case 3:
                depositMoney();
                break;
            case 4:
                viewMiniStatement();
                break;
            default:
                System.out.println("Collect your ATM Card. Thank you for using ATM Card.");
                System.out.println("\n");
                System.exit(0);
        }
    }

    public void checkBalance() {
        System.out.println("Loading Account Balance...");
        System.out.println("Balance: " + balance);
        System.out.println("\n");
        menu();
    }

    public void withdrawMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter Amount to Withdraw: ");
        float amount = sc.nextFloat();
        if (amount > balance) {
            System.out.println("Insufficient Balance");
        } else {
            balance -= amount;
            float res = balance;
            System.out.println("Money Withdrawa Successfully");
            System.out.println(res + " Collect the Cash");
            miniStmt.put((double) amount, "Amount Withdrawn");
        }
        System.out.println("\n");
        menu();
    }

    public void depositMoney() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter the Amount: ");
        float amount = sc.nextFloat();
        balance += amount;
        float res = balance;
        System.out.println(res + " Money Deposited Successfully");
        miniStmt.put((double) amount, "Amount Deposited");
        System.out.println("\n");
        menu();
    }

    public void viewMiniStatement() {
        System.out.println("Mini Statement");
        System.out.println("************************");
        miniStmt.forEach((key, value) -> System.out.println(key + " " + value));
        System.out.println("\n");
        menu();
    }
}

public class Main {
    public static void main(String[] args) {
        ATM atm = new ATM();
        atm.checkAccount();
    }
}
