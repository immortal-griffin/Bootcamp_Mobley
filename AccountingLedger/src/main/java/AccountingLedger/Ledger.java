package AccountingLedger;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void ledgerMain() {
        Scanner scanner = new Scanner(System.in);
        transactions = (ArrayList<Transaction>) FileManager.readTransactions();

        while (true) {
            System.out.println("----- Welcome To The Ledger -----");
            System.out.println("Main Menu");
            System.out.println("A) Display All Transactions");
            System.out.println("D) Display All Deposits");
            System.out.println("P) Display All Payments");
            ;
            System.out.println("R) Reports");
            System.out.println("H) Return To Home Screen");
            char choice = scanner.nextLine().charAt(0);

            switch (choice) {
                case 'A':
                    try {
                        //Reads through .csv file to print each line to the User Interface
                        FileReader fileReader = new FileReader("src/main/resources/transactions.csv");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        String line;

                        while ((line = bufferedReader.readLine()) != null) {
                            System.out.println(line);
                        }
                    } catch (FileNotFoundException ex) {
                        System.out.println("Issue reading file.");
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        System.out.println("Issue reading file.");
                        ex.printStackTrace();
                    }
                    break;
                case 'D':
                    Ledger.sortByDeposit();
                    break;
                case 'P':
                    Ledger.sortByPayment();
                    break;
                case 'R':
                    Reports.reportsMain();
                    break;
                case 'H':
                    System.out.println("Returning to Home Screen...");
                    HomeScreen.launchHomeScreen();
                    break;
                default:
            }
        }
    }

    public static void sortByDeposit() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            if (t.getStatus().equalsIgnoreCase("Deposit")) {
                System.out.println(
                        t.getDate() + "|" +
                                t.getTime() + "|" +
                                t.getDescription() + "|" +
                                t.getVendor() + "|" +
                                t.getAmount() + "|" +
                                t.getStatus());
            }
        }
    }

    public static void sortByPayment() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            if (t.getStatus().equalsIgnoreCase("Payment")) {
                System.out.println(
                        t.getDate() + "|" +
                                t.getTime() + "|" +
                                t.getDescription() + "|" +
                                t.getVendor() + "|" +
                                t.getAmount() + "|" +
                                t.getStatus());
            }
        }
    }

    public static void printTransaction(Transaction t) {
        System.out.println(
                t.getDate() + "|" +
                t.getTime() + "|" +
                t.getDescription() + "|" +
                t.getVendor() + "|" +
                t.getAmount() + "|" +
                t.getStatus());
    }
}
//todo: Create Reports class to contain Reports branch and methods
//todo: Create predetermined search functions for Reports class
//todo: Work out custom search function
