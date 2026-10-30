package OnlineStoreApp;

import java.util.Scanner;

public class InventoryManagement {
    public static void staffScreen() {
        Scanner scanner = new Scanner(System.in);

        int i = 0;
        System.out.println("Hello, welcome to the Inventory Management system");
        System.out.println("Please input your employee password:");
        int employeePass = scanner.nextInt();
        if (employeePass == 1234) {     //Default password, can make employee class later
            System.out.println("Thank you, Griffin.");
            System.out.println("Loading The system...");
        } else if (employeePass != 1234) {
            System.out.println("Invalid Password.");
            System.out.println("Please try again.");
            i++;
            while (i <= 2) {
                System.out.println("Please reenter your password.");
                int passwordRetry = scanner.nextInt();
                if (passwordRetry != 1234 && i < 2) {
                    System.out.println("Invalid password. Please try again.");
                    i++;
                } else if (i == 2) {
                    System.out.println("You have input the wrong password too many times. Closing the program...");
                    System.exit(0);
                } else {
                    System.out.println("Loading the system...");
                    i = 3;
                }
            }
        }

        while (true) {
        System.out.println("----- Inventory Management Menu -----");
        System.out.println("1. View All Inventory");
        System.out.println("2. Add New Item To Inventory");
        System.out.println("3. Update Inventory Count");
        System.out.println("0. Log Out");
        System.out.println("Please choose what you would like to do");
        int managerChoice = scanner.nextInt();

            switch (managerChoice) {
                case 1:
                    FileManager.printCsv();
                    System.out.println("Returning to the main menu...");
                    break;
                case 2:
                    System.out.println("Please enter the ID number of the product");
                    int id = scanner.nextInt();
                    scanner.nextLine();
                    System.out.println("Please enter the SKU number of the product");
                    String sku = scanner.nextLine();
                    System.out.println("Please enter the product name");
                    String productName = scanner.nextLine();
                    System.out.println("Please enter the price of the product");
                    float price = scanner.nextFloat();
                    scanner.nextLine();
                    System.out.println("Please enter the category that the product belongs to");
                    String category = scanner.nextLine();
                    System.out.println("Please enter the number of items for this product");
                    int quantity = scanner.nextInt();

                    Product product = new Product(id, sku, productName, price, category, quantity);
                    FileManager.writeProduct(product);
                    System.out.println("Returning to the main menu...");
                    break;
                case 3:
                    break;
                case 0:
                    System.out.println("Logging out...");
                    System.exit(0);
                    break;
                default:
                    System.out.println();
            }
        }
    }
}

//Have to fix the program looping back to the top after inventoryManagement