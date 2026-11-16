package CarDealership;

import java.util.List;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Dealership dealership = new Dealership(
                "Akatsuki Luxury Autos",
                "2314 Konoha Blvd",
                "888-515-4545"
        );

        List<Vehicle> inventory = dealership.getAllVehicles();
        dealership.printInventory();

        System.out.println("Enter the VIN of the vehicle you'd like to remove");
        int vin = scanner.nextInt();

        dealership.removeVehicle(vin);

        //Dealership.addVehicle();
    }

    public void scannerInitiate() {
        Scanner scanner = new Scanner(System.in);
    }
}
