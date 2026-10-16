package Calculators;

import java.util.Scanner;

public class Calculator2 {
    public static void calc2() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----Certificate Of Deposit-----");
        System.out.println("How much is your Principal? (Rounded to the nearest dollar)");
        int principal = scanner.nextInt();

        System.out.println("What is your Annual Interest Rate?");
        double aInterestRate = scanner.nextDouble();
        double aInterestPercent = aInterestRate / 100;

        System.out.println("How many years until it matures?");
        int numOfYears = scanner.nextInt();

        int daysPerYear = 365;
        int totalNumOfDays = numOfYears * daysPerYear;

        double temp = (Math.pow(1 + aInterestPercent / daysPerYear, totalNumOfDays));
        double futureValue = principal * temp;

        double totalInterestEarned = futureValue - principal;

        System.out.printf("Your CD's future value is $%.2f.%n", futureValue);
        System.out.printf("Your total interest earned is $%.2f.", totalInterestEarned);



    }
}
