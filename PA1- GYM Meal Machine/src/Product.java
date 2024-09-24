/**
 * Represents a product available in the vending machine.
 */
public class Product {
    // Fields to store product information
    private String name;
    private double price;
    private double protein;
    private double carbohydrate;
    private double fat;
    private double calorie;

    // Field to keep track of the count of this product
    private int count = 1;

    /**
     * Constructs a new Product with the given parameters.
     * @param name The name of the product.
     * @param carbohydrate The amount of carbohydrates in the product.
     * @param protein The amount of protein in the product.
     * @param price The price of the product.
     * @param fat The amount of fat in the product.
     */
    public Product(String name, double carbohydrate, double protein, double price, double fat) {
        this.name = name;
        this.price = price;
        this.protein = protein;
        this.carbohydrate = carbohydrate;
        this.fat = fat;
        this.calorie = calculateCalorie();
    }

    /**
     * Gets the name of the product.
     * @return The name of the product.
     */
    public String getName() {
        return name;
    }

    /**
     * Gets the count of this product.
     * @return The count of this product.
     */
    public int getCount() {
        return count;
    }

    /**
     * Increments the count of this product.
     */
    public void incrementCount() {
        count++;
    }

    /**
     * Gets the calorie content of the product.
     * @return The calorie content of the product.
     */
    public double getCalorie() {
        return calorie;
    }

    /**
     * Calculates the calorie content of the product.
     * @return The calculated calorie content of the product.
     */
    public double calculateCalorie() {
        return 4 * protein + 4 * carbohydrate + 9 * fat;
    }

    /**
     * Gets the protein content of the product.
     * @return The protein content of the product.
     */
    public double getProtein() {
        return protein;
    }

    /**
     * Adjusts the protein content of the product.
     * @param protein The new protein content of the product.
     */
    public void setProtein(double protein) {
        this.protein = protein;
    }

    /**
     * Gets the carbohydrate content of the product.
     * @return The carbohydrate content of the product.
     */
    public double getCarbohydrate() {
        return carbohydrate;
    }

    /**
     * Adjusts the carbohydrate content of the product.
     * @param carbohydrate The new carbohydrate content of the product.
     */
    public void setCarbohydrate(double carbohydrate) {
        this.carbohydrate = carbohydrate;
    }

    /**
     * Gets the fat content of the product.
     * @return The fat content of the product.
     */
    public double getFat() {
        return fat;
    }

    /**
     * Adjusts the fat content of the product.
     * @param fat The new fat content of the product.
     */
    public void setFat(double fat) {
        this.fat = fat;
    }

    /**
     * Adjusts the count of this product.
     * @param count The new count of this product.
     */
    public void setCount(int count) {
        this.count = count;
    }

    /**
     * Gets the price of the product.
     * @return The price of the product.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Adjusts the price of the product.
     * @param price The new price of the product.
     */
    public void setPrice(double price) {
        this.price = price;
    }
}
