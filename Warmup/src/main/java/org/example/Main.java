package org.example;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("Welcome To Griffin's Restaurant!");
        System.out.println("Here is our menu:");
        System.out.println("Burger");
        System.out.println("Fries");
        System.out.println("Shake");
        System.out.println("Please choose what you would like.");
        String customerChoice = scanner.nextLine();

        Reciept.printReciept(customerChoice);
    }
}