package OnlineStoreApp;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<Product> readProducts(){
        List<Product> productList = new ArrayList<>();
        try{
            FileReader fileReader = new FileReader("src/main/resources/products.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String row;

            bufferedReader.readLine();
            while((row = bufferedReader.readLine()) != null){
                String [] brokenUpRow = row.split("\\|");
                String sku = brokenUpRow[0];
                String productName = brokenUpRow[1];
                float price = Float.parseFloat(brokenUpRow[2]);
                String category = brokenUpRow[3];
                int quantity = Integer.parseInt(brokenUpRow[4]);

                Product product = new Product(sku, productName, price, category, quantity);

                productList.add(product);
            }
        }
        catch(IOException ex)  {
            System.out.println("Could not read the file. Check the file and try again. ");
        }
        catch(Exception ex) {
            System.out.println("Could not read data from the file. Check the format.");
        }
        return productList;
    }
}
