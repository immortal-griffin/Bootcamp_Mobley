package AccountingLedger;

public class Ledger {
    public static void ledgerMain() {
        System.out.println("----- Welcome To The Ledger -----");
        System.out.println("Main Menu");
        System.out.println("A) Display All Transactions");
        System.out.println("D) Display All Deposits");
        System.out.println("P) Display All Payments");
        System.out.println("R) Reports");
        System.out.println("H) Return To Home Screen");
    }
}

//todo: Add while loop to keep user in the ledgerMain
//todo: Create a method to retrieve all Transactions
//todo: Create a reusable method that can call for payments and deposits based on a keyword
