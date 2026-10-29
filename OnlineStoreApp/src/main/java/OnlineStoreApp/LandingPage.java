package OnlineStoreApp;

import java.util.List;

public class LandingPage {
    public static void main(String[] args) {
        List<Product> productList = FileManager.readProducts();

        Product product = new Product("GAS10001", "Logitech G 920 Racing Wheel", 199.99f, "Gaming Accessories", 6);
    }
}