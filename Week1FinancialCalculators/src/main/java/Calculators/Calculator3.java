package Calculators;

import java.util.Scanner;

public class Calculator3 {
    public static void calc3() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----Annuity Calculator-----");
        System.out.println("How much does the annuity pay out monthly? (Rounded to the nearest dollar)");
        int mPayout = scanner.nextInt();

        System.out.println("What is the expected annual interest rate?");
        float interestRate = scanner.nextFloat() / 100;

        System.out.println("How many years does the annuity pay out for?");
        int annuityLength = scanner.nextInt();

        double monthlyRate = interestRate / 12;
        int payments = annuityLength * 12;

        double presentValue = mPayout * (1 - Math.pow(1 + monthlyRate, -payments)) / monthlyRate;

        System.out.printf("Your present value is $%.2f.", presentValue);



    }
}
