package AccountingLedger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import static AccountingLedger.Ledger.transactions;

public class Reports {
    public static void reportsMain() {
        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("Reports");
            System.out.println("1) Month To Date");
            System.out.println("2) Previous Month");
            System.out.println("3) Year To Date");
            System.out.println("4) Previous Year");
            System.out.println("5) Search By Vendor");
            System.out.println("6) Custom Search");
            System.out.println("9) Return To Ledger");
            System.out.println("0) Return To Home");
            System.out.println("Please Choose An Option");
            int choice = scanner.nextInt();
            scanner.nextLine();
            LocalDate today = LocalDate.now();

            switch (choice) {
                case 1:
                    for (int i = transactions.size() - 1; i >= 0; i--) {
                        Transaction t = transactions.get(i);
                        if (t.getDate().getMonth() == today.getMonth() && t.getDate().getYear() == today.getYear()) {
                            Ledger.printTransaction(t);
                        }
                    }
                    break;
                case 2:
                    LocalDate previousMonth = today.minusMonths(1);
                    for (int i = transactions.size() - 1; i >= 0; i--) {
                        Transaction t = transactions.get(i);
                        LocalDate date = t.getDate();
                        if (date.getYear() == previousMonth.getYear() && date.getMonth() == previousMonth.getMonth()) {
                            Ledger.printTransaction(t);
                        }
                    }
                    break;
                case 3:
                    for (int i = transactions.size() - 1; i >= 0; i--) {
                        Transaction t = transactions.get(i);
                        if (t.getDate().getYear() == today.getYear()) {
                            Ledger.printTransaction(t);
                        }
                    }
                    break;
                case 4:
                    int previousYear = LocalDate.now().getYear() - 1;
                    for (int i = transactions.size() - 1; i >= 0; i--) {
                        Transaction t = transactions.get(i);
                        if (t.getDate().getYear() == previousYear) {
                            Ledger.printTransaction(t);
                        }
                    }
                    break;
                case 5:
                    System.out.println("Enter The Vendor You'd Like To Search For");
                    String vChoice = scanner.nextLine();
                    for (int i = transactions.size() - 1; i >= 0; i--) {
                        Transaction t = transactions.get(i);
                        if (t.getVendor().equalsIgnoreCase(vChoice)) {
                            Ledger.printTransaction(t);
                        }
                    }
                    break;
                case 6:
                    System.out.println("Custom search coming soon....");
                    break;
                case 9:
                    Ledger.ledgerMain();
                    break;
                case 0:
                    HomeScreen.launchHomeScreen();
                    break;
                default:
            }
        }
    }

    public static ArrayList<Transaction> searchTransactions(
            ArrayList<Transaction> transactions,
            LocalDate startDate,
            LocalDate endDate,
            LocalTime startTime,
            LocalTime endTime,
            String vendorFilter,
            String statusFilter) {

        ArrayList<Transaction> results = new ArrayList<>();

        if (startDate != null && endDate != null
                && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                    "Start date cannot be after end date.");
        }

        for (Transaction transaction : transactions) {

            LocalDate date = transaction.getDate();
            LocalTime time = transaction.getTime();

            boolean matchesDate =
                    (startDate == null || !date.isBefore(startDate))
                            && (endDate == null || !date.isAfter(endDate));

            boolean matchesTime =
                    (startTime == null || !time.isBefore(startTime))
                            && (endTime == null || !time.isAfter(endTime));

            boolean matchesVendor =
                    vendorFilter == null || vendorFilter.isBlank()
                            || transaction.getVendor()
                            .toLowerCase()
                            .contains(vendorFilter.toLowerCase());

            boolean matchesStatus =
                    statusFilter == null || statusFilter.isBlank()
                            || transaction.getStatus()
                            .equalsIgnoreCase(statusFilter);

            if (matchesDate && matchesTime
                    && matchesVendor && matchesStatus) {
                results.add(transaction);
                System.out.println(
                        transaction.getDate() + "|" +
                                transaction.getTime() + "|" +
                                transaction.getDescription() + "|" +
                                transaction.getVendor() + "|" +
                                transaction.getAmount() + "|" +
                                transaction.getStatus());
            }
        }
        return results;
    }
}
