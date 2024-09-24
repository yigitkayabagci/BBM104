import java.util.List;

/**
 * The Main class is the entry point of the application.
 * It reads commands from a file, executes them, and writes the results to another file.
 */
public class Main {
    /**
     * The main method of the application.
     * It reads commands from a file specified by the first argument, executes them, and writes the results to another file specified by the second argument.
     *
     * @param args the command-line arguments
     */
    public static void main(String[] args) {
        // Read all lines from the input file
        String[] allcontent = Inputreader.readFile(args[0], false, false);
        // Initialize the output file
        Outputwriter.writeToFile(args[1],"",false,false);
        // Create a new inventory
        Inventory<NamedItem> inventory = new Inventory<>();
        // Process each line from the input file
        for (int i = 0; i < allcontent.length; i++) {
            // Split the line into parts
            String[] parts = allcontent[i].split("\t");
            // Get the command from the first part
            String command = parts[0];
            // Execute the command
            switch (command) {
                case "ADD":
                    // Add a new item to the inventory
                    String type = parts[1];
                    if (type.equals("Book")) {
                        Book book = new Book(parts[2], parts[3], parts[4], Double.parseDouble(parts[5]));
                        inventory.addItem(book);
                    } else if (type.equals("Toy")) {
                        Toy toy = new Toy(parts[2], parts[3], parts[4], Double.parseDouble(parts[5]));
                        inventory.addItem(toy);
                    } else if (type.equals("Stationery")) {
                        Stationery stationery = new Stationery(parts[2], parts[3], (parts[4]), Double.parseDouble(parts[5]));
                        inventory.addItem(stationery);
                    }
                    break;
                case "REMOVE":
                    // Remove an item from the inventory
                    Outputwriter.writeToFile(args[1], "REMOVE RESULTS:", true, true);
                    boolean removed = inventory.removeItem(parts[1]);
                    if (removed) {
                        Outputwriter.writeToFile(args[1],"Item is removed.",true,true);
                    }
                    else {
                        Outputwriter.writeToFile(args[1], "Item is not found.", true, true);
                    }
                    Outputwriter.writeToFile(args[1], "------------------------------", true, true);
                    break;
                case "SEARCHBYBARCODE":
                    // Search for an item by barcode
                    Outputwriter.writeToFile(args[1], "SEARCH RESULTS:", true, true);
                    NamedItem item = inventory.searchByBarcode(parts[1]);
                    if (item != null) {
                        Outputwriter.writeToFile(args[1], String.valueOf(item), true, true);
                    } else {
                        Outputwriter.writeToFile(args[1], "Item is not found.", true, true);
                    }
                    Outputwriter.writeToFile(args[1], "------------------------------", true, true);
                    break;
                case "SEARCHBYNAME":
                    // Search for items by name
                    Outputwriter.writeToFile(args[1], "SEARCH RESULTS:", true, true);
                    List<NamedItem> items = inventory.searchByName(parts[1]);
                    for (NamedItem foundItem : items) {
                        Outputwriter.writeToFile(args[1], String.valueOf(foundItem), true, true);
                    }
                    if (items.isEmpty()) {
                        Outputwriter.writeToFile(args[1], "Item is not found.", true, true);
                    }
                    Outputwriter.writeToFile(args[1], "------------------------------", true, true);
                    break;
                case "DISPLAY":
                    // Display all items in the inventory
                    Outputwriter.writeToFile(args[1],"INVENTORY:", true, true);
                    List<NamedItem> allItems = inventory.getSortedItems();
                    for (NamedItem foundItem : allItems) {
                        Outputwriter.writeToFile(args[1], String.valueOf(foundItem), true, true);;
                    }
                    Outputwriter.writeToFile(args[1], "------------------------------", true, true);
                    break;
                default:
                    // Unknown command
                    System.out.println("Unknown command.");
            }
        }
    }
}