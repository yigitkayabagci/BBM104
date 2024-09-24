/**
 * The Stationery class represents a stationery item in the inventory.
 * Each stationery item has a name, barcode, price, and type.
 */
public class Stationery implements NamedItem {
    private final String name;  // The name of the stationery item
    private final String barcode;  // The barcode of the stationery item
    private final double price;  // The price of the stationery item
    private final String type;  // The type of the stationery item

    /**
     * Constructs a new Stationery with the given parameters.
     *
     * @param name the name of the stationery item
     * @param type the type of the stationery item
     * @param barcode the barcode of the stationery item
     * @param price the price of the stationery item
     */
    public Stationery(String name, String type, String barcode, double price) {
        this.name = name;
        this.barcode = barcode;
        this.price = price;
        this.type = type;
    }

    /**
     * Returns the name of the stationery item.
     *
     * @return the name of the stationery item
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the barcode of the stationery item.
     *
     * @return the barcode of the stationery item
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Returns a string representation of the stationery item.
     *
     * @return a string representation of the stationery item
     */
    @Override
    public String toString() {
        return "Kind of the " + name + " is " + type + ". Its barcode is " + barcode + " and its price is " + price;
    }
}