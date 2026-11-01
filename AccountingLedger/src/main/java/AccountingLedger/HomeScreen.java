package AccountingLedger;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class HomeScreen {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Main Menu");
            System.out.println("D) Add Deposit");
            System.out.println("P) Make Payment or Add Debit");
            System.out.println("L) Access Ledger");
            System.out.println("X) Exit");
            char userChoice = scanner.nextLine().charAt(0);

            switch (userChoice) {
                case 'D':
                    LocalDate date = Methodology.promptForDate("Enter Date |yyyy-mm-dd|");
                    LocalTime time = Methodology.promptForTime("Enter Time |hh:mm:ss|");
                    String description = Methodology.promptForString("Enter Description");
                    String vendor = Methodology.promptForString("Enter Vendor");
                    float amount = Methodology.promptForDeposit("Enter Amount");
                    String status = "Deposit";

                    Transaction transaction = new Transaction(date, time, description, vendor, amount, status);
                    FileManager.writeTransaction(transaction);
                    break;
                case 'P':
                    LocalDate pDate = Methodology.promptForDate("Enter Date |yyyy-mm-dd|");
                    LocalTime pTime = Methodology.promptForTime("Enter Time |hh:mm:ss|");
                    String pDescription = Methodology.promptForString("Enter Description");
                    String pVendor = Methodology.promptForString("Enter Vendor");
                    float pAmount = Methodology.promptForPayment("Enter Amount");
                    String pStatus = "Payment";

                    Transaction payment = new Transaction(pDate, pTime, pDescription, pVendor, pAmount, pStatus);
                    FileManager.writeTransaction(payment);
                    break;
                case 'L':
                    Ledger.ledgerMain();
                    break;
                case 'X':
                    System.out.println("You have chosen to exit");
                    System.exit(0);
                    break;
                default:
                    System.out.println("Try again");

            }
        }
    }
}