/**
 * The NamedItem interface represents an item with a name and a barcode.
 * This interface is implemented by any class that represents an item that can be named and identified by a barcode.
 */
public interface NamedItem {
    /**
     * Returns the name of the item.
     *
     * @return the name of the item
     */
    String getName();

    /**
     * Returns the barcode of the item.
     *
     * @return the barcode of the item
     */
    String getBarcode();
}