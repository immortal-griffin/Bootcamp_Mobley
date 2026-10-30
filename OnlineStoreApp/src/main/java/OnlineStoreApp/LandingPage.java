package OnlineStoreApp;

import java.util.List;
import java.util.Scanner;

import static OnlineStoreApp.CustomerScreen.customerScreen;
import static OnlineStoreApp.InventoryManagement.staffScreen;

public class LandingPage {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("----- Welcome to Griffin's Electronics -----");
        System.out.println("If you are a customer, please press 1.");
        System.out.println("If you are a staff member, please press 2.");
        int userInput = scanner.nextInt();

        switch(userInput) {
            case 1:
                customerScreen();
                break;
            case 2:
                staffScreen();
                break;
            default:
        }

    }
}