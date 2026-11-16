package CarDealership;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Dealership {

    private String name;
    private String address;
    private String phone;
    private List<Vehicle> inventory;

    public Dealership(String name, String address, String phone) {

        this.name = name;
        this.address = address;
        this.phone = phone;
        this.inventory = DealershipFileManager.readVehicles();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public void addVehicle() {
        Scanner scanner = new Scanner(System.in);

        System.out.println("VIN?");
        int vin = scanner.nextInt();
        System.out.println("Year?");
        int year = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Make?");
        String make = scanner.nextLine();
        System.out.println("Model?");
        String model = scanner.nextLine();
        System.out.println("Vehicle Type?");
        String vehicleType = scanner.nextLine();
        System.out.println("Color?");
        String color = scanner.nextLine();
        System.out.println("Odometer?");
        int odometer = scanner.nextInt();
        scanner.nextLine();
        System.out.println("Price?");
        BigDecimal price = scanner.nextBigDecimal();

        Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

        inventory.add(vehicle);
        DealershipFileManager.writeVehicle(vehicle);
    }

    public void removeVehicle(int vin) {
        for (int i = 0; i < inventory.size(); i++) {
            Vehicle v = inventory.get(i);
            if (v.getVin() == vin) {
                inventory.remove(i);
                DealershipFileManager.saveVehicles(inventory);

                System.out.println("Vehicle successfully removed.");
                return;
            }
        }
        System.out.println("Vehicle not found.");
    }

    public List<Vehicle> getAllVehicles() {
        return inventory;
    }


    public void printInventory() {
        for (Vehicle vehicle: inventory) {
            System.out.println(
                    vehicle.getVin() + "|" +
                    vehicle.getYear() + "|" +
                    vehicle.getMake() + "|" +
                    vehicle.getModel() + "|" +
                    vehicle.getVehicleType() + "|" +
                    vehicle.getColor() + "|" +
                    vehicle.getOdometer() + "|" +
                    vehicle.getPrice()
            );
        }
    }
}
