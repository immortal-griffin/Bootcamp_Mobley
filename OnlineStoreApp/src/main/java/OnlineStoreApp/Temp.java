package OnlineStoreApp;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class Temp {
    /* try {
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
            if (category.equals(userCategory)) {
                System.out.println("Item " + i + ": " + brokenUpRow[2] + ", it costs $" + brokenUpRow[3] + ", and we have " + brokenUpRow[5] + " in stock.");
                i++;
            } else {
                System.out.println("Category Not Found or Invalid Choice");
                System.exit(0);
            }
        }
    } catch(
    FileNotFoundException ex){
        System.out.println("Could not read file.");
        ex.printStackTrace();
    } catch(
    IOException ex){
        System.out.println("IO Exception.");
        ex.printStackTrace();
    } */
}
