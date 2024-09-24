/**
 * The Book class represents a book in the inventory.
 * Each book has a name, author, barcode, and price.
 */
public class Book implements NamedItem {
    private final String name;  // The name of the book
    private final String author;  // The author of the book
    private final String barcode;  // The barcode of the book
    private final double price;  // The price of the book

    /**
     * Constructs a new Book with the given parameters.
     *
     * @param name the name of the book
     * @param author the author of the book
     * @param barcode the barcode of the book
     * @param price the price of the book
     */
    public Book(String name, String author, String barcode, double price) {
        this.name = name;
        this.author = author;
        this.barcode = barcode;
        this.price = price;
    }

    /**
     * Returns the name of the book.
     *
     * @return the name of the book
     */
    @Override
    public String getName() {
        return name;
    }

    /**
     * Returns the author of the book.
     *
     * @return the author of the book
     */
    public String getAuthor() {
        return author;
    }

    /**
     * Returns the barcode of the book.
     *
     * @return the barcode of the book
     */
    public String getBarcode() {
        return barcode;
    }

    /**
     * Returns the price of the book.
     *
     * @return the price of the book
     */
    public double getPrice() {
        return price;
    }

    /**
     * Returns a string representation of the book.
     *
     * @return a string representation of the book
     */
    @Override
    public String toString() {
        return "Author of the " + name + " is " + author + ". Its barcode is " + barcode + " and its price is " + price;
    }
}