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

            System.out.println(UIFormatting.BG_CHARCOAL + UIFormatting.GOLD +
                    "\n╔══════════════════════════════╗\n" +
                    "║        WELCOME TO THE        ║\n" +
                    "║      REPORTS MAIN MENU       ║\n" +
                    "║                              ║\n" +
                    "║1) Month To Date              ║\n" +
                    "║2) Previous Month             ║\n" +
                    "║3) Year To Date               ║\n" +
                    "║4) Previous Year              ║\n" +
                    "║5) Search By Vendor           ║\n" +
                    "║6) Custom Search              ║\n" +
                    "║9) Return To Ledger           ║\n" +
                    "║0) Return To Home             ║\n" +
                    "╠══════════════════════════════╣\n" +
                    "║    Please choose an option   ║\n" +
                    "╚══════════════════════════════╝\n" +
                    UIFormatting.RESET);

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
                    UIFormatting.printLHeader("Enter The Vendor You'd Like To Search For");
                    String vChoice = scanner.nextLine();
                    for (int i = transactions.size() - 1; i >= 0; i--) {
                        Transaction t = transactions.get(i);
                        if (t.getVendor().equalsIgnoreCase(vChoice)) {
                            Ledger.printTransaction(t);
                        }
                    }
                    break;
                case 6:
                    UIFormatting.printSHeader("Custom Search");
                    UIFormatting.printLHeader("Enter the start date yyyy-mm-dd (Leave empty for any)");
                    LocalDate startDate = null;
                    while (true) {
                        try {
                            String input = scanner.nextLine();
                            startDate = input.isEmpty()
                                    ? null
                                    : LocalDate.parse(input);
                            break;
                        } catch (Exception e) {
                            errorFormat("Invalid Entry, please try again.");
                        }
                    }
                    UIFormatting.printLHeader("Enter the end date yyyy-mm-dd (Leave empty for any)");
                    LocalDate endDate = null;
                    while (true) {
                        try {
                            String input = scanner.nextLine();
                            endDate = input.isEmpty()
                                    ? null
                                    : LocalDate.parse(input);
                            break;
                        } catch (Exception e) {
                            errorFormat("Invalid Entry, please try again.");
                        }
                    }
                    UIFormatting.printLHeader("Enter the start time hh:mm:ss (Leave empty for any)");
                    LocalTime startTime = null;
                    while (true) {
                        try {
                            String input = scanner.nextLine();
                            startTime = input.isEmpty()
                                    ? null
                                    : LocalTime.parse(input);
                            break;
                        } catch (Exception e) {
                            errorFormat("Invalid Entry, please try again.");
                        }
                    }
                    UIFormatting.printLHeader("Enter the end time hh:mm:ss (Leave empty for any)");
                    LocalTime endTime = null;
                    while (true) {
                        try {
                            String input = scanner.nextLine();
                            endTime = input.isEmpty()
                                    ? null
                                    : LocalTime.parse(input);
                            break;
                        } catch (Exception e) {
                            errorFormat("Invalid Entry, please try again.");
                        }
                    }
                    UIFormatting.printLHeader("Enter the vendor name (Leave empty for any)");
                    String vendorFilter = "";
                    while (true) {
                        try {
                            String input = scanner.nextLine();
                            vendorFilter = input.isEmpty()
                                    ? null
                                    : input;
                            break;
                        } catch (Exception ex) {
                            errorFormat("Invalid Entry, please try again.");
                        }
                    }
                    UIFormatting.printLHeader("Enter the status | Deposit/Payment (Leave empty for both)");
                    String statusFilter = "";
                    while (true) {
                        try {
                            String input = scanner.nextLine();
                            statusFilter = input.isEmpty()
                                    ? null
                                    : input;
                            break;
                        } catch (Exception ex) {
                            errorFormat("Invalid Entry, please try again.");
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

    //Formatting method to change colors of the text
    public static void genFormat(String prompt) {
        System.out.println(UIFormatting.GOLD + prompt + UIFormatting.RESET);
    }

    public static void errorFormat(String prompt) {
        System.out.println(UIFormatting.RED + prompt + UIFormatting.RESET);
    }

    public static ArrayList<Transaction> searchTransactions(
            ArrayList<Transaction> transactions,
            LocalDate startDate,
            LocalDate endDate,
            LocalTime startTime,
            LocalTime endTime,
            String vendorFilter,
            String statusFilter) {

        if (startDate != null && endDate != null
                && startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(
                    "Start date cannot be after end date.");
        }

        ArrayList<Transaction> results = new ArrayList<>();

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

        if (results.isEmpty()) {
            errorFormat("No transactions available for this search criteria.");
        }
        return results;
    }

    /*public static void passwordSystem() {
        int i = 0;
        System.out.println("Please input your manager password:");
        int managerPass = scanner.nextInt();
        if (managerPass == 1234) {     //Default password
            System.out.println("Thank you, Griffin.");
            System.out.println("Loading The system...");
        } else if (managerPass != 1234) {
            errorFormat("Invalid Password.");
            errorFormat("Please try again.");
            i++;
            while (i <= 2) {
                System.out.println("Please reenter your password.");
                int passwordRetry = scanner.nextInt();
                 if (passwordRetry != 1234 && i < 2) {
                    errorFormat("Invalid password. Please try again.");
                    i++;
                } else if (i == 2) {
                    errorFormat("You have input the wrong password too many times. Closing the program...");
                    System.exit(0);
                } else {
                     System.out.println("Thank you, Griffin.");
                    System.out.println("Loading the system...");
                    i = 3;
                }
            }
        } */
    }

//todo: further testing for the search options and prepare to address edge cases
//todo: password system for transaction management