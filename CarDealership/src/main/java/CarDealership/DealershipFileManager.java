package CarDealership;

import java.io.*;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class DealershipFileManager {

    public static List<Vehicle> readVehicles() {
        List<Vehicle> inventory = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("src/main/resources/inventory.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String row;

            bufferedReader.readLine();
            while ((row = bufferedReader.readLine()) != null) {
                String[] brokenUpRow = row.split("\\|");
                int vin = Integer.parseInt(brokenUpRow[0]);
                int year = Integer.parseInt(brokenUpRow[1]);
                String make = brokenUpRow[2];
                String model = brokenUpRow[3];
                String vehicleType = (brokenUpRow[4]);
                String color = brokenUpRow[5];
                int odometer = Integer.parseInt(brokenUpRow[6]);
                BigDecimal price = new BigDecimal(brokenUpRow[7]);

                Vehicle vehicle = new Vehicle(vin, year, make, model, vehicleType, color, odometer, price);

                inventory.add(vehicle);
            }
        } catch (IOException ex) {
            System.out.println("Could not read the file. Check the file and try again.");
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return inventory;
    }

    public static boolean writeVehicle(Vehicle vehicle) {

        String filePath = "src/main/resources/inventory.csv";

        try (BufferedWriter bufferedWriter =
                     new BufferedWriter(new FileWriter(filePath, true))) {

            String line = String.format(
                    "%d|%d|%s|%s|%s|%s|%d|%.2f",
                    vehicle.getVin(),
                    vehicle.getYear(),
                    vehicle.getMake(),
                    vehicle.getModel(),
                    vehicle.getVehicleType(),
                    vehicle.getColor(),
                    vehicle.getOdometer(),
                    vehicle.getPrice()
            );

            bufferedWriter.newLine();
            bufferedWriter.write(line);

            System.out.println("New vehicle added to the inventory!");
            return true;
        } catch (IOException ex) {
            System.out.println("Could not write to the file.");
            return false;
        }
    }

    public static void saveVehicles(List<Vehicle> inventory) {
        try (PrintWriter writer = new PrintWriter(
                new FileWriter("src/main/resources/inventory.csv"))) {

            writer.println("Akatsuki Luxury Autos|2314 Konoha Blvd|888-515-4545");

            for (Vehicle vehicle : inventory) {
                writer.println(
                        vehicle.getVin() + "|" +
                        vehicle.getYear() + "|" +
                        vehicle.getMake() + "|" +
                        vehicle.getModel() + "|" +
                        vehicle.getVehicleType() + "|" +
                        vehicle.getColor() + "|" +
                        vehicle.getOdometer() + "|" +
                        vehicle.getPrice().toPlainString()
                );
            }

        } catch (IOException e) {
            System.out.println("Error saving inventory");
        }
    }
}
