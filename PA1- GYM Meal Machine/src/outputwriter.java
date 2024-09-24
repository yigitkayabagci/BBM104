import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintStream;

public class outputwriter {
    /**
     * This method writes given content to file at given path.
     *
     * @param path    Path for the file content is going to be written.
     * @param content Content that is going to be written to file.
     * @param append  Append status, true if wanted to append to file if it exists, false if wanted to create file from zero.
     * @param newLine True if wanted to append a new line after content, false if vice versa.
     */
    public static void writeToFile(String path, String content, boolean append, boolean newLine) {
        PrintStream ps = null;
        try {
            ps = new PrintStream(new FileOutputStream(path, append));
            ps.print(content + (newLine ? "\n" : ""));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } finally {
            if (ps != null) { //Flushes all the content and closes the stream if it has been successfully created.
                ps.flush();
                ps.close();
            }
        }
    }
}

/*

            // Diziye kalan sayıları ekliyoruz
            for (int j = 0; j < remainValues.length; j++) {
                remainarr[i][j] = Double.parseDouble(remainValues[j]); // Double olarak saklıyoruz
            }
        }

        // Kalan sayıları yazdırıyoruz
        for (int i = 0; i < content.length; i++) {
            for (int j = 0; j < 3; j++) {
                if (remainarr[i][j] == (int) remainarr[i][j]) { // Eğer double, bir tamsayı ise
                    System.out.print((int) remainarr[i][j] + " "); // Ondalık kısmı atarak yazdır
                } else {
                    System.out.print(remainarr[i][j] + " "); // Aksi halde doğrudan yazdır
                }
            }
            System.out.println();*/

/*
public void loadProduct(String[] names,int[] prices, double[][] remain, Product[][] slots) {
    for (int j=0;j<names.length;j++){
        String name = names[j];
        double price = prices[j];
        double fat = remain[j][2];
        double carb = remain[j][0];
        double protein = remain[j][1];
        boolean found = false;
        for (int i = 0; !found && i < 6; i++) {
            for (int k = 0; !found && k < 4; k++) {
                if (slots[i][k] != null && slots[i][k].getName().equals(names[j]) && slots[i][k].getCount() < 10) {
                    slots[i][k].incrementCount();
                    found = true;
                    break;
                }
            }
            if (found)  {
                break;
            }

        }

        if(!found) {Product product1 = new Product(name, carb, protein, price, fat);
            loadMachine(product1);}







    }


}*/

/*public void loadMachine(Product product) {
        if (getRow() == 6 && getCol() == 0){
            System.out.println("Machine is full");}
        if (getRow() >= 0 && getRow() < MAX_ROWS && col >= 0 && col < MAX_COLS) {
            if (slots[getRow()][getCol()] == null) {
                slots[getRow()][getCol()] = product;
                if(this.col<MAX_COLS) this.col = col +1;
                else {this.row = row + 1;
                this.col = 0;}
            }
        }
    }*/