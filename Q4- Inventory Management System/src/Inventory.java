import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * The Inventory class represents a collection of items.
 * Each item in the inventory is an instance of a class that implements the NamedItem interface.
 * The inventory supports operations to add an item, remove an item by barcode, search for an item by barcode or name, and get all items sorted by type.
 *
 * @param <E> the type of items in the inventory
 */
public class Inventory<E extends NamedItem> {
    private List<E> items;  // The list of items in the inventory

    /**
     * Constructs a new, empty Inventory.
     */
    public Inventory() {
        items = new ArrayList<>();
    }

    /**
     * Adds the specified item to the inventory.
     *
     * @param item the item to be added
     */
    public void addItem(E item) {
        items.add(item);
    }

    /**
     * Removes the item with the specified barcode from the inventory.
     *
     * @param barcode the barcode of the item to be removed
     * @return true if an item was removed, false otherwise
     */
    public boolean removeItem(String barcode) {
        for (Iterator<E> iterator = items.iterator(); iterator.hasNext();) {
            E item = iterator.next();
            if (item.getBarcode().equals(barcode)) {
                iterator.remove();
                return true;
            }
        }
        return false;
    }

    /**
     * Searches for an item with the specified barcode in the inventory.
     *
     * @param barcode the barcode of the item to search for
     * @return the item with the specified barcode, or null if no such item exists
     */
    public E searchByBarcode(String barcode) {
        for (E item : items) {
            if (item instanceof Book && ((Book) item).getBarcode().equals(barcode)) {
                return item;
            } else if (item instanceof Toy && ((Toy) item).getBarcode().equals(barcode)) {
                return item;
            } else if (item instanceof Stationery && ((Stationery) item).getBarcode().equals(barcode)) {
                return item;
            }
        }
        return null;
    }

    /**
     * Searches for items with the specified name in the inventory.
     *
     * @param name the name of the items to search for
     * @return a list of items with the specified name
     */
    public List<E> searchByName(String name) {
        List<E> result = new ArrayList<>();
        for (E item : items) {
            if (item.getName().contains(name)) {
                result.add(item);
            }
        }
        return result;
    }

    /**
     * Returns a list of all items in the inventory, sorted by type.
     * The items are first sorted into books, toys, and stationeries, and then concatenated into a single list.
     *
     * @return a list of all items in the inventory, sorted by type
     */
    public List<E> getSortedItems() {
        List<E> sortedItems = new ArrayList<>(items);

        List<E> books = new ArrayList<>();
        List<E> toys = new ArrayList<>();
        List<E> stationeries = new ArrayList<>();

        for (E item : sortedItems) {
            if (item instanceof Book) {
                books.add(item);
            } else if (item instanceof Toy) {
                toys.add(item);
            } else if (item instanceof Stationery) {
                stationeries.add(item);
            }
        }

        List<E> result = new ArrayList<>();
        result.addAll(books);
        result.addAll(toys);
        result.addAll(stationeries);

        return result;
    }
}