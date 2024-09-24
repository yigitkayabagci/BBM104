/**
 * The Toy class represents a toy in the inventory.
 * Each toy has a name, color, barcode, and price.
 */
public class Toy implements NamedItem {
    private final String name;  // The name of the toy
    private final String color;  // The color of the toy
    private final String barcode;  // The barcode of the toy
    private final double price;  // The price of the toy

    /**
     * Constructs a new Toy with the given parameters.
     *
     * @param name the name of the toy
     * @param color the color of the toy
     * @param barcode the barcode of the toy
     * @param price the price of the toy
     */
    public Toy(String name, String color, String barcode, double price) {
        this.name = name;
        this.color = color;
        this.barcode = barcode;
        this.price = price;
    }

    /**
     * Returns the name of the toy.
     *
     * @return the name of the toy
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the barcode of the toy.
     *
     * @return the barcode of the toy
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Returns a string representation of the toy.
     *
     * @return a string representation of the toy
     */
    @Override
    public String toString() {
        return "Color of the " + name + " is " + color + ". Its barcode is " + barcode + " and its price is " + price;
    }
}