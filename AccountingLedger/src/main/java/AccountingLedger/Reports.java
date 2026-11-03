package AccountingLedger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Scanner;

import static AccountingLedger.Ledger.transactions;

public class Reports {
    public static Scanner scanner = new Scanner(System.in);

    public static void reportsMain() {

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
                    System.out.println("Custom Search");
                    System.out.println("Enter the start date yyyy-mm-dd (Leave empty for any)");
                    LocalDate startDate = null;
                    while (true) {
                        try {
                            String input = scanner.nextLine();
                            startDate = input.isEmpty()
                                    ? null
                                    : LocalDate.parse(input);
                                    break;
                        } catch (Exception e) {
                            System.out.println("Invalid Entry, please try again.");
                        }
                    }
                    System.out.println("Enter the end date yyyy-mm-dd (Leave empty for any)");
                    LocalDate endDate = null;
                    while (true) {
                        try {
                            String input = scanner.nextLine();
                            endDate = input.isEmpty()
                                    ? null
                                    : LocalDate.parse(input);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid Entry, please try again.");
                        }
                    }
                    System.out.println("Enter the start time hh:mm:ss (Leave empty for any)");
                    LocalTime startTime = null;
                    while (true) {
                        try {
                            String input = scanner.nextLine();
                            startTime = input.isEmpty()
                                    ? null
                                    : LocalTime.parse(input);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid Entry, please try again.");
                        }
                    }
                    System.out.println("Enter the end time hh:mm:ss (Leave empty for any)");
                    LocalTime endTime = null;
                    while (true) {
                        try {
                            String input = scanner.nextLine();
                            endTime = input.isEmpty()
                                    ? null
                                    : LocalTime.parse(input);
                            break;
                        } catch (Exception e) {
                            System.out.println("Invalid Entry, please try again.");
                        }
                    }
                    System.out.println("Enter the vendor name (Leave empty for any)");
                    String vendorFilter = "";
                    while (true) {
                        try {
                            String input = scanner.nextLine();
                            vendorFilter = input.isEmpty()
                                    ? null
                                    : input;
                            break;
                        } catch (Exception ex) {
                            System.out.println("Invalid Entry, please try again.");
                        }
                    }
                    System.out.println("Enter the status | Deposit/Payment (Leave empty for both)");
                    String statusFilter = "";
                    while (true) {
                        try {
                            String input = scanner.nextLine();
                            statusFilter = input.isEmpty()
                                    ? null
                                    : input;
                            break;
                        } catch (Exception ex) {
                            System.out.println("Invalid Entry, please try again.");
                        }
                    }

                    searchTransactions(transactions, startDate, endDate, startTime, endTime, vendorFilter, statusFilter);
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
            } else {
                System.out.println("No transactions available for this search criteria.");
                break;
            }
        }
        return results;
    }
}

//todo: further testing for the search options and prepare to address edge cases