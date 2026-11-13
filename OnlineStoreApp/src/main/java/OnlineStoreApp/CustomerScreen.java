package OnlineStoreApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerScreen {
    public static ArrayList<Product> products = new ArrayList<>();

    public static void customerScreen() {
        Scanner scanner = new Scanner(System.in);
        products = (ArrayList<Product>) FileManager.readProducts();

        System.out.println("----- Welcome To Griffin's Electronics -----");
        System.out.println("Please enter your first name");
        String customerName = scanner.nextLine();

        while (true) {
            System.out.println("Welcome, " + customerName + "!");
            System.out.println("1. View Inventory");
            System.out.println("2. View Inventory By Category");
            System.out.println("3. View Your Cart");
            System.out.println("0. Exit");
            System.out.println("Please choose from our menu:");
            int userChoice = scanner.nextInt();

            switch (userChoice) {
                case 1:
                    sort();
                    System.out.println("Please enter the ID of the item you'd like, or enter 0 to go back");
                    int choice = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < products.size(); i++) {
                        Product p = products.get(i);
                        if (p.getId() == choice) {
                            FileManager.addToCart(p);
                            System.out.println("You have added " + p.getProductName() + " to your cart!");
                            System.out.println("Returning to main menu...");
                        }
                    }
                    break;
                case 2:
                    scanner.nextLine();

                    System.out.println("Enter the category you'd like to search for");
                    String userCategory = scanner.nextLine();

                    for (int i = 0; i < products.size(); i++) {
                        Product p = products.get(i);
                        if (p.getCategory().equals(userCategory)) {
                            printProductList(p);
                        }
                    }

                    System.out.println("Please enter the ID of the item you'd like, or enter 0 to go back");
                    int uChoice = scanner.nextInt();
                    scanner.nextLine();

                    for (int i = 0; i < products.size(); i++) {
                        Product p = products.get(i);
                        if (p.getId() == uChoice) {
                            FileManager.addToCart(p);
                            System.out.println("You have added " + p.getProductName() + " to your cart!");
                            System.out.println("Returning to main menu...");
                        }
                    }
                    break;
                case 3:
                    FileManager.readCart();
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }

    public static void sort() {
        for (int i = 0; i < products.size(); i++) {
            Product p = products.get(i);
            printProductList(p);
        }
    }

    public static void printProductList(Product p) {
        System.out.println(
                        "Item " + p.getId() + ": " + p.getProductName() + ", it costs $" + p.getPrice() + ", and we have " + p.getQuantity() + " in stock.");
    }
}

//todo: View cart function with checkout function