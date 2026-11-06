package AccountingLedger;

import java.util.ArrayList;
import java.util.Scanner;

public class Ledger {
    public static ArrayList<Transaction> transactions = new ArrayList<>();

    public static void ledgerMain() {
        Scanner scanner = new Scanner(System.in);
        transactions = (ArrayList<Transaction>) FileManager.readTransactions();

        while (true) {

            System.out.println(UIFormatting.BG_CHARCOAL + UIFormatting.GOLD +
                    "\n╔══════════════════════════════╗\n" +
                    "║        WELCOME TO THE        ║\n" +
                    "║       LEDGER MAIN MENU       ║\n" +
                    "║                              ║\n" +
                    "║A) Display All Transactions   ║\n" +
                    "║D) Display All Deposits       ║\n" +
                    "║P) Display All Payments       ║\n" +
                    "║R) Reports                    ║\n" +
                    "║H) Return To Home Screen      ║\n" +
                    "╠══════════════════════════════╣\n" +
                    "║    Please choose an option   ║\n" +
                    "╚══════════════════════════════╝\n" +
                    UIFormatting.RESET);

            char choice = scanner.nextLine().charAt(0);

            switch (choice) {
                case 'A':
                    sort();
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

    public static void sort() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
                printTransaction(t);
        }
    }

    public static void sortByDeposit() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            if (t.getStatus().equalsIgnoreCase("Deposit")) {
                printTransaction(t);
            }
        }
    }

    public static void sortByPayment() {
        for (int i = transactions.size() - 1; i >= 0; i--) {
            Transaction t = transactions.get(i);
            if (t.getStatus().equalsIgnoreCase("Payment")) {
                printTransaction(t);
            }
        }
    }

    public static void printTransaction(Transaction t) {
        System.out.println(UIFormatting.STEEL_SILVER +
                t.getDate() + "|" +
                t.getTime() + "|" +
                t.getDescription() + "|" +
                t.getVendor() + "|" +
                t.getAmount() + "|" +
                t.getStatus() +
                UIFormatting.RESET);
    }
}

//todo:
