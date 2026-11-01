package OnlineStoreApp;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class FileManager {
    public static List<Product> readProducts() {
        List<Product> productList = new ArrayList<>();
        try {
            FileReader fileReader = new FileReader("src/main/resources/products.csv");
            BufferedReader bufferedReader = new BufferedReader(fileReader);

            String row;

            bufferedReader.readLine();
            while ((row = bufferedReader.readLine()) != null) {
                String[] brokenUpRow = row.split("\\|");
                int id = Integer.parseInt(brokenUpRow[0]);
                String sku = brokenUpRow[1];
                String productName = brokenUpRow[2];
                float price = Float.parseFloat(brokenUpRow[3]);
                String category = brokenUpRow[4];
                int quantity = Integer.parseInt(brokenUpRow[5]);

                Product product = new Product(id, sku, productName, price, category, quantity);

                productList.add(product);
            }
        } catch (IOException ex) {
            System.out.println("Could not read the file. Check the file and try again. ");
        } catch (Exception ex) {
            System.out.println("Could not read data from the file. Check the format.");
        }
        return productList;
    }

    public static void writeProduct(Product product) {
        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/products.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            String line = String.format("%d|%s|%s|%.2f|%s|%d",
                    product.getId(),
                    product.getSku(),
                    product.getProductName(),
                    product.getPrice(),
                    product.getCategory(),
                    product.getQuantity());

            bufferedWriter.newLine();
            bufferedWriter.write(line);

            bufferedWriter.close();

            System.out.println("New product added to inventory!");
        } catch (IOException ex) {
            System.out.println("Could not write to the file.");
        } catch (Exception ex) {
            System.out.println("Error when handling the data.");
        }
    }

    public static void addToCart(String product) {

        try {
            FileWriter fileWriter = new FileWriter("src/main/resources/cart.csv", true);
            BufferedWriter bufferedWriter = new BufferedWriter(fileWriter);

            bufferedWriter.write(product);
            bufferedWriter.newLine();

            bufferedWriter.close();
        } catch (IOException ex) {
            System.out.println("Could not write to cart.");
            ex.printStackTrace();
        }
    }

    public static void printCsv() {
        try {
            FileReader fileReader = new FileReader("src/main/resources/products.csv");
                BufferedReader bufferedReader = new BufferedReader(fileReader);

                String line;

            while((line =bufferedReader.readLine())!=null) {
                    System.out.println(line);
                }
        } catch (FileNotFoundException ex) {
            System.out.println("Issue reading file.");
            ex.printStackTrace();
        } catch (IOException ex) {
            System.out.println("Issue reading file.");
            ex.printStackTrace();
        }
    }

    public static List<Product> searchForProductsByCategory(String category) {
        List<Product> products = loadProductsFromFile();
        List<Product> foundProducts = new ArrayList<>();
        for (Product product : products) {
            if (product.getCategory().toLowerCase().contains(category.toLowerCase())) {
                foundProducts.add(product);
            }
        }
        return foundProducts;
    }

    private static List<Product> loadProductsFromFile() {
        List<Product> products = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(
                new FileReader("src/main/resources/products.csv"))) {

            String line;

            reader.readLine(); // Skip header

            while ((line = reader.readLine()) != null) {
                String[] data = line.split("\\|");

                int id = Integer.parseInt(data[0]);
                String sku = data[1];
                String productName = data[2];
                float price = Float.parseFloat(data[3]);
                String category = data[4];
                int quantity = Integer.parseInt(data[5]);

            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return products;
    }
    }

/*List<Product> products = loadProductsFromFile();
List<Product> foundProducts = new ArrayList<>();
        for (Product product : products) {
        if (product.getCategory().toLowerCase().contains(category.toLowerCase())) {
        foundProducts.add(product);
            }
                    }
                    return foundProducts;
    } */