package AccountingLedger;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Scanner;


public class Methodology {
    private static Scanner scanner = new Scanner(System.in);

    public static String promptForString(String prompt) {
        System.out.println(prompt);
        String input = scanner.nextLine();
        return input;
    }

    public static float promptForFloat(String prompt) {
        while(true) {
            try {
                System.out.println(prompt);
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
                System.out.println(prompt);
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
                System.out.println(prompt);
                String input = scanner.nextLine();
                return LocalTime.parse(input);
            } catch (Exception ex) {
                System.out.println("Invalid Format. Please use hh:mm:ss");
            }
        }
    }
}

//todo: Rename promptForFloat to promptForDeposit
//todo: create promptForPayment, copy line 21
//todo: Remove "-" from line 21