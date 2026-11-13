package OnlineStoreApp;

import java.util.ArrayList;
import java.util.Scanner;

public class InventoryManagement {
    public static ArrayList<Product> products = new ArrayList<>();
    public static void staffScreen() {
        Scanner scanner = new Scanner(System.in);
        products = (ArrayList<Product>) FileManager.readProducts();

            passwordSystem();

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
                case 2: {
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
                    products.add(product);
                    System.out.println("Returning to the main menu...");
                    break;
                }
                case 3:
                    System.out.println("Products in list: " + products.size());
                    for (int j = 0; j < products.size(); j++) {
                        Product p = products.get(j);
                        printProductList(p);
                    }
                    System.out.println("Please choose the item ID of the item you'd like to update");
                    int edit = scanner.nextInt();
                    scanner.nextLine();
                    Product chosenProduct = null;

                    for (int j = 0; j < products.size(); j++) {
                        Product p = products.get(j);
                        if (p.getId() == edit) {
                            chosenProduct = p;
                            System.out.println("You've chosen " + p.getProductName() + "!");
                            break;
                        }
                    }
                    if (chosenProduct == null) {
                        System.out.println("Product ID not found.");
                        break;
                    }
                    System.out.println("Would you like to add inventory or remove inventory (A/R)");
                    char userChoice = scanner.nextLine().toUpperCase().charAt(0);

                    if (userChoice == 'A') {
                        int quantity = chosenProduct.getQuantity();

                        System.out.println("How many items would you like to add?");
                        int addInventory = scanner.nextInt();
                        scanner.nextLine();

                        if (addInventory <= 0) {
                            System.out.println("Please enter a quantity greater than zero.");
                            break;
                        }

                        chosenProduct.setQuantity(quantity + addInventory);
                        FileManager.updateProducts(products);

                        System.out.println("Inventory successfully updated!");
                        System.out.println("New quantity: " + chosenProduct.getQuantity());
                    } else if (userChoice == 'R') {
                        System.out.println("Permission only granted to senior management.");
                    }

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

    public static void passwordSystem() {
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
    }

    public static void printProductList(Product p) {
        System.out.println(
                p.getId() + "|" +
                p.getSku() + "|" +
                p.getProductName() + "|" +
                p.getPrice() + "|" +
                p.getCategory() + "|" +
                p.getQuantity()
        );
    }
}

//todo: Add function to be able to update inventory count