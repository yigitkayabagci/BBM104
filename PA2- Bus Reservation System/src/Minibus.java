/**
 * Class representing a minibus extending the Bus class.
 */
public class Minibus extends Bus {
    // Define the voyage type for the minibus
    private String voyageType = "Minibus";

    /**
     * Constructor for the Minibus class.
     *
     * @param rows          The number of rows in the minibus.
     * @param voyageID      The ID of the voyage.
     * @param startingPlace The starting place of the voyage.
     * @param endingPlace   The ending place of the voyage.
     * @param ticketPrice   The price of a ticket.
     */
    public Minibus(int rows, int voyageID, String startingPlace, String endingPlace, double ticketPrice) {
        super(2, rows, voyageID, startingPlace, endingPlace, ticketPrice); // Call the superclass constructor with the appropriate argument
        seatsArray = new int[rows][2];
        total_voyage_count++;
    }

    /**
     * Method to calculate revenue for the minibus.
     *
     * @param soldSeats An array representing sold seats.
     */
    @Override
    public void revenueCalculator(int soldSeats[]) {
        filledSeats += soldSeats.length;
        for (int i = 0; i < soldSeats.length; i++) {
            total_revenue += ticketPrice;
            int rownum = (soldSeats[i] - 1) / 2;
            int colnum = (soldSeats[i] - 1) % 2;
            seatsArray[rownum][colnum] = 1;
        }
    }

    /**
     * Method to calculate refund for the minibus.
     *
     * @param refundChairs An array representing refunded seats.
     * @param args         Arguments passed to the method (write output to file).
     */
    @Override
    public void refundCalculator(int refundChairs[], String[] args) {
        Outputwrite.writeToFile(args[1], "ERROR: Minibus tickets are not refundable!", true, true);
    }

    /**
     * Method to get the voyage type of the minibus.
     *
     * @return The voyage type of the minibus.
     */
    @Override
    public String getVoyageType() {
        return voyageType;
    }
}