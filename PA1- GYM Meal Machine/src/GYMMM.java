public class GYMMM {
    // Constants for maximum rows and columns in the vending machine
    private final int MAX_ROWS = 6;
    private final int MAX_COLS = 4;

    // Current row and column indices
    private int row = 0;
    private int col = 0;

    // Array to hold products and slots in the vending machine
    private Product[] products;
    public Product[][] slots;

    // Index to keep track of products
    private int productIndex;

    // Constructor
    public GYMMM() {
        products = new Product[MAX_ROWS * MAX_COLS];
        slots = new Product[MAX_ROWS][MAX_COLS];
        productIndex = 0;
    }

    // Maximum capacity of each slot in the vending machine
    private final int MAX_CAPACITY = 10;

    // Getters and setters
    /**
     * Gets the current row index.
     * @return The current row index.
     */
    public int getRow() {
        return row;
    }

    /**
     * Gets the current column index.
     * @return The current column index.
     */
    public int getCol() {
        return col;
    }

    /**
     * Sets the current row index.
     * @param row The row index to set.
     */
    public void setRow(int row) {
        this.row = row;
    }

    /**
     * Sets the current column index.
     * @param col The column index to set.
     */
    public void setCol(int col) {
        this.col = col;
    }

    /**
     * Checks if the vending machine is full.
     * @return -1 if full, otherwise returns the count of filled slots.
     */
    public int isMachineFull() {
        int count = 0;
        for (int i = 0; i < slots.length; i++) {
            for (int j = 0; j < slots[i].length; j++) {
                if (slots[i][j] != null && slots[i][j].getCount() == MAX_CAPACITY) {
                    count++;
                }
            }
        }
        if (count == MAX_ROWS * MAX_COLS) {
            return 25; // Machine is full
        }
        return count;
    }

    /**
     * Loads a product into the vending machine.
     * @param product The product to load.
     * @param args File-related arguments for output.
     */
    public void loadMachine(Product product, String args[]) {
        int row = 0;
        int col = 0;
        boolean productLoaded = false;

        // Find an empty slot to load the product
        while (row < MAX_ROWS && !productLoaded) {
            if (slots[row][col] == null) {
                slots[row][col] = product;
                productLoaded = true;
            } else if (slots[row][col].getName().equals(product.getName()) && slots[row][col].getCount() < MAX_CAPACITY) {
                slots[row][col].incrementCount();
                productLoaded = true;
            }

            col++;
            if (col >= MAX_COLS) {
                col = 0;
                row++;
            }
        }
        // If product couldn't be loaded, print a message
        if (!productLoaded) {
            String line = "INFO: There is no available place to put " + product.getName();
            outputwriter.writeToFile(args[2], line, true, true);
        }
    }

    /**
     * Loads products into the vending machine based on provided arrays.
     * @param names Array of product names.
     * @param prices Array of product prices.
     * @param remain 2D array containing remaining nutrients of products.
     * @param args File-related arguments for output.
     * @return 0 if successful, -1 if machine is full.
     */
    public int fill(String[] names, int[] prices, double[][] remain, String args[]) {
        // Load products into the machine
        for (int j = 0; j < names.length; j++) {
            String name = names[j];
            double price = prices[j];
            double fat = remain[j][2];
            double carb = remain[j][1];
            double protein = remain[j][0];
            Product product = new Product(name, carb, protein, price, fat);
            if (isMachineFull() != 25) {
                loadMachine(product, args); // Call loadMachine to load the product
            } else {
                // Print message if machine is full and returns -1
                String line1 = "INFO: There is no available place to put " + product.getName();
                outputwriter.writeToFile(args[2], line1, true, true);
                String line2 = "INFO: The machine is full!";
                outputwriter.writeToFile(args[2], line2, true, true);
                return -1;
            }
        }
        return 0;
    }

    /**
     * Prints the output of the vending machine.
     * @param args File-related arguments for output.
     */
    public void printGMMOutput(String args[]) {
        // Header
        String line = "-----Gym Meal Machine-----";
        outputwriter.writeToFile(args[2], line, true, true);

        // Loop through slots and print product information
        for (int i = 0; i < MAX_ROWS; i++) {
            for (int j = 0; j < MAX_COLS; j++) {
                if (slots[i][j] != null) {
                    if (slots[i][j].getCount() != 0) {
                        // Round the calorie value to the nearest integer
                        double calorie = slots[i][j].getCalorie();
                        int roundedCalorie = (int) Math.round(calorie);
                        String line1 = slots[i][j].getName() + "(" + roundedCalorie + ", " + slots[i][j].getCount() + ")";
                        outputwriter.writeToFile(args[2], line1, true, false);
                    } else {
                        String line2 = "___(0, 0)";
                        outputwriter.writeToFile(args[2], line2, true, false);
                    }
                } else {
                    String line3 = "___(0, 0)";
                    outputwriter.writeToFile(args[2], line3, true, false);
                }
                if (j < MAX_COLS - 1) {
                    String line4 = "___";
                    outputwriter.writeToFile(args[2], line4, true, false);
                }
            }
            String line5 = "___\n";
            outputwriter.writeToFile(args[2], line5, true, false);
        }
        String line6 = "----------";
        outputwriter.writeToFile(args[2], line6, true, true);
    }
}
