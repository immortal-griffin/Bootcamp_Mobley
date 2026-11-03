package AccountingLedger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;

public class HomeScreen {
    public static void main(String[] args) {
        HomeScreen.launchHomeScreen();
    }

    public static Scanner scanner = new Scanner(System.in);

    public static void launchHomeScreen() {
    Scanner scanner = new Scanner(System.in);
        while (true) {

            //display customized main menu
            System.out.println(UIFormatting.BG_CHARCOAL + UIFormatting.GOLD +
                    "\n╔══════════════════════════════╗\n" +
                    "║        POTARA CUSTOMS        ║\n" +
                    "║       FINANCIAL LEDGER       ║\n" +
                    "║                              ║\n" +
                    "║D) Add Deposit                ║\n" +
                    "║P) Make Payment or Add Debit  ║\n" +
                    "║L) Access Ledger              ║\n" +
                    "║X) Exit                       ║\n" +
                    "╠══════════════════════════════╣\n" +
                    "║    Please choose an option   ║\n" +
                    "╚══════════════════════════════╝\n" +
                    UIFormatting.RESET);

            char userChoice = scanner.nextLine().charAt(0);

            switch (userChoice) {
                case 'D':
                    LocalDate date = promptForDate("Enter Date |yyyy-mm-dd|");
                    LocalTime time = promptForTime("Enter Time |hh:mm:ss|");
                    String description = promptForString("Enter Description");
                    String vendor = promptForString("Enter Vendor");
                    float amount = promptForDeposit("Enter Amount");
                    String status = "Deposit";

                    Transaction transaction = new Transaction(date, time, description, vendor, amount, status);
                    FileManager.writeTransaction(transaction);
                    break;
                case 'P':
                    LocalDate pDate = promptForDate("Enter Date |yyyy-mm-dd|");
                    LocalTime pTime = promptForTime("Enter Time |hh:mm:ss|");
                    String pDescription = promptForString("Enter Description");
                    String pVendor = promptForString("Enter Vendor");
                    float pAmount = promptForPayment("Enter Amount");
                    String pStatus = "Payment";

                    Transaction payment = new Transaction(pDate, pTime, pDescription, pVendor, pAmount, pStatus);
                    FileManager.writeTransaction(payment);
                    break;
                case 'L':
                    Ledger.ledgerMain();
                    break;
                case 'X':
                    System.out.println(UIFormatting.RED + "You have chosen to exit" + UIFormatting.RESET);
                    System.exit(0);
                    break;
                default:
                    System.out.println("Try again");

            }
        }
    }

    public static String promptForString(String prompt) {
        UIFormatting.printSHeader(prompt);
        String input = scanner.nextLine();
        return input;
    }

    public static float promptForDeposit(String prompt) {
        while(true) {
            try {
                UIFormatting.printSHeader(prompt);
                String input = scanner.nextLine();
                return Float.parseFloat(input);
            } catch (Exception e) {
                System.out.println("Invalid Entry, please try again.");
            }
        }
    }

    public static float promptForPayment(String prompt) {
        while(true) {
            try {
                UIFormatting.printSHeader(prompt);
                String input = "-" + scanner.nextLine();
                return Float.parseFloat(input);
            } catch (Exception e) {
                System.out.println("Invalid Entry, please try again.");
            }
        }
    }

    public static LocalDate promptForDate(String prompt) {
        while(true) {
            try {
                UIFormatting.printSHeader(prompt);
                String input = scanner.nextLine();
                return LocalDate.parse(input);
            } catch (Exception ex) {
                System.out.println("Invalid Format. Please use yyyy-mm-dd");
            }
        }
    }

    public static LocalTime promptForTime(String prompt) {
        while(true) {
            try {
                UIFormatting.printSHeader(prompt);
                String input = scanner.nextLine();
                return LocalTime.parse(input);
            } catch (Exception ex) {
                System.out.println("Invalid Format. Please use hh:mm:ss");
            }
        }
    }
}

//todo: prepare the HomeScreen for polish and formatting