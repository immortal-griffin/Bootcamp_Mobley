package OnlineStoreApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CustomerScreen {
    public static void customerScreen() {
        Scanner scanner = new Scanner(System.in);

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
                    ArrayList<String> products = new ArrayList<>();
                    try {
                        FileReader fileReader = new FileReader("src/main/resources/products.csv");
                        BufferedReader bufferedReader = new BufferedReader(fileReader);

                        String row;
                        int i = 1;

                        bufferedReader.readLine();
                        while ((row = bufferedReader.readLine()) != null) {
                            String[] brokenUpRow = row.split("\\|");
                            int id = Integer.parseInt(brokenUpRow[0]);
                            String sku = brokenUpRow[1];
                            String productName = brokenUpRow[2];
                            float price = Float.parseFloat(brokenUpRow[3]);
                            String category = brokenUpRow[4];
                            int quantity = Integer.parseInt(brokenUpRow[5]);
                            products.add(row);

                            System.out.println("Item " + i + ": " + brokenUpRow[2] + ", it costs $" + brokenUpRow[3] + ", and we have " + brokenUpRow[5] + " in stock.");
                            i++;
                        }
                    } catch (FileNotFoundException ex) {
                        System.out.println("Could not read file.");
                        ex.printStackTrace();
                    } catch (IOException ex) {
                        System.out.println("IO Exception.");
                        ex.printStackTrace();
                    }
                    System.out.println("Please enter the ID of the item you'd like, or enter 0 to go back");
                    int choice = scanner.nextInt();

                    for (String product : products) {
                        String[] brokenUpRow = product.split("\\|");
                        int id = Integer.parseInt(brokenUpRow[0]);
                        if (id == choice) {
                            FileManager.addToCart(product);
                            System.out.println("You have added " + brokenUpRow[2] + " to your cart!");
                            System.out.println("Returning to main menu...");
                        }
                    }
                    break;
                case 2:
                    System.out.println("Choice 2.");
                    break;
                case 3:
                    System.out.println("Choice 3.");
                    break;
                case 0:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Invalid choice, please try again.");
            }
        }
    }
}