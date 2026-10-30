package OnlineStoreApp;

import java.util.Scanner;

public class InventoryManagement {
    public static void staffScreen() {
        Scanner scanner = new Scanner(System.in);

        int i = 0;
            System.out.println("Hello, welcome to the Inventory Management system");
            while (true) {
            System.out.println("Please input your employee password:");
            int employeePass = scanner.nextInt();
            if (employeePass == 1234) {     //Default password, can make employee class later
                System.out.println("Thank you, Griffin. Loading the system...");
                InventoryManagement.inventoryManagement();
            } else {
                System.out.println("Invalid Password.");
                System.out.println("Please try again.");
                i++;
            }
        }
        //System.out.println("You have input the wrong password too many times. Closing the program...");
            //System.exit(0);
        }
        public static void inventoryManagement () {
            Scanner scanner = new Scanner(System.in);

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
                    break;
                case 2:
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

//Have to fix the program looping back to the top after inventoryManagement