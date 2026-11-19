package org.example;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Run {
    public static void main(String[] args) {
        House myHouse = new House("my house", "11/12/2026", new BigDecimal(145000.00), "144 Delavau Dr", 3, 1700, 500);
        House vacationHome = new House("my vacation home", "11/17/2026", new BigDecimal(255000.00), "2654 Hollywood Blvd", 4, 3200, 750);

        Vehicle dailyDriver = new Vehicle("my daily driver", "05/24/2001", new BigDecimal(15000.00), "Honda Accord", 2000, 125000);
        Vehicle fancyCar = new Vehicle("my fancy car", "11/17/2026", new BigDecimal(62000.00), "Genesis G70", 2026, 145);

        List<Asset> assets = new ArrayList<>();

        assets.add(myHouse);
        assets.add(vacationHome);
        assets.add(dailyDriver);
        assets.add(fancyCar);

        for (Asset asset : assets) {
            BigDecimal value = asset.getValue(); // Store or use the value if needed

            String message = "";
            if (asset instanceof House house) {
                message = "House at " + house.getAddress();
            } else if (asset instanceof Vehicle vehicle) {
                message = "Vehicle: " + vehicle.getYear() + " " + vehicle.getMakeModel();
            }

            if (!message.isEmpty()) {
                System.out.println(message + " (Value: $" + value + ")");
            }
        }
    }
}
