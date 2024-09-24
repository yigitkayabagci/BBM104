/**
 * This class represents a DataProducer which processes sold seats, refund seats, and checks for the last empty line in a given array of strings.
 */
public class DataProducer {

    /**
     * Parses a string representing sold seats and returns an array of integers representing those seats.
     *
     * @param soldSeatsLine The string containing sold seats separated by underscores.
     * @param args          Additional arguments  write output to a file.
     * @return An array of integers representing sold seats.
     */
    public int[] soldSeats(String soldSeatsLine, String[] args) {
        String[] soldSeatsArray = soldSeatsLine.split("_");
        int c = 0; // Counter variable for error handling
        try {
            int[] soldSeatsInt = new int[soldSeatsArray.length];
            for (int i = 0; i < soldSeatsArray.length; i++) {
                c = i; // Store the index where the exception occurred
                soldSeatsInt[i] = Integer.parseInt(soldSeatsArray[i]);
            }
            return soldSeatsInt;
        } catch (NumberFormatException e) {
            // Log error and return a default value
            Outputwrite.writeToFile(args[1], "ERROR: " + soldSeatsArray[c] + " is not a positive number, price must be a positive number!", true, true);
            return new int[]{-2395};
        }
    }

    /**
     * Parses a string representing refund seats and returns an array of integers representing those seats.
     *
     * @param refundSeatsLine The string containing refund seats separated by underscores.
     * @param args            Additional arguments  write output to a file.
     * @return An array of integers representing refund seats.
     */
    public int[] refundSeats(String refundSeatsLine, String[] args) {
        String[] refundSeatsArray = refundSeatsLine.split("_");
        int c = 0; // Counter variable for error handling
        try {
            int[] refundSeatsInt = new int[refundSeatsArray.length];
            for (int i = 0; i < refundSeatsArray.length; i++) {
                c = i; // Store the index where the exception occurred
                refundSeatsInt[i] = Integer.parseInt(refundSeatsArray[i]);
            }
            return refundSeatsInt;
        } catch (NumberFormatException e) {
            // Log error and return a default value
            Outputwrite.writeToFile(args[1], "ERROR: " + refundSeatsArray[c] + " is not a positive number, price must be a positive number!", true, true);
            return new int[]{-2395};
        }
    }

    /**
     * Finds the number of consecutive empty lines starting from the given index in the array.
     *
     * @param allcontent The array of strings to search for empty lines.
     * @param i          The starting index for the search.
     * @return The number of consecutive empty lines from the given index.
     */
    public int lastEmptyLine(String[] allcontent, int i) {
        int lastchecker = 0; // Counter for consecutive empty lines
        for (; i < allcontent.length; i++) {
            if (allcontent[i].trim().equals("")) {
                lastchecker++;
            }
        }
        return lastchecker;
    }
}
