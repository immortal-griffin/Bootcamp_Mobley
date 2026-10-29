package OnlineStoreApp;

public class Product {
    private String sku;
    private String productName;
    private float price;
    private String category;
    private int quantity;

    public String getSku() {
        return sku;
    }

    public void setSku(String sku) {
        this.sku = sku;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public Product(String sku, String productName, float price, String category, int quantity) {
        this.sku = sku;
        this.productName = productName;
        this.price = price;
        this.category = category;
        this.quantity = quantity;


    }
}
