package Calculators;

import java.util.Scanner;

public class Calculator1 {
    public static void calc1() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----Mortgage Calculator-----");
        System.out.println("How much is your Principal? (Rounded to the nearest dollar)");
        int principal = scanner.nextInt();

        System.out.println("What is your Annual Interest Rate?");
        double aInterestRate = scanner.nextDouble();
        double aInterestPercent = aInterestRate / 100;

        double mInterestRate = aInterestPercent / 12;

        System.out.println("How long is your loan term? (in years)");
        int loanLength = scanner.nextInt();

        int numMonthlyPayments = loanLength * 12;

        double firstHalf = (mInterestRate * Math.pow(1 + mInterestRate, numMonthlyPayments));
        double secondHalf = (Math.pow(1 + mInterestRate, numMonthlyPayments) - 1);

        double semiTotal = firstHalf / secondHalf;

        double monthlyPayment = principal * semiTotal;
        double totalInterest = (monthlyPayment * numMonthlyPayments) - principal;


        System.out.printf("Your monthly payment is: $%.2f%n", monthlyPayment);
        System.out.printf("Your total interest is: %.2f", totalInterest);





        //System.out.println();










    }

        //principal = int
        //aInterestRate = double
        //mInterestRate = double
        //loanTerm = int
        //numMonthlyPayments = int



}

