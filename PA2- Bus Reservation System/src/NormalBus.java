/**
 * Class representing a normal bus extending the Bus class.
 */
public class NormalBus extends Bus {
    // Define the refund cut for the normal bus
    protected int refundCut;
    // Define the voyage type for the normal bus
    private String voyageType = "Standard";

    /**
     * Constructor for the NormalBus class.
     *
     * @param rows          The number of rows in the normal bus.
     * @param voyageID      The ID of the voyage.
     * @param startingPlace The starting place of the voyage.
     * @param endingPlace   The ending place of the voyage.
     * @param ticketPrice   The price of a ticket.
     * @param refundCut     The percentage of refund cut.
     */
    public NormalBus(int rows, int voyageID, String startingPlace, String endingPlace, double ticketPrice, int refundCut) {
        super(4, rows, voyageID, startingPlace, endingPlace, ticketPrice); // Call the superclass constructor with the appropriate argument
        this.refundCut = refundCut;
        seatsArray = new int[rows][4];
        total_voyage_count++;
    }

    /**
     * Method to calculate revenue for the normal bus.
     *
     * @param soldSeats An array representing sold seats.
     */
    @Override
    public void revenueCalculator(int soldSeats[]) {
        filledSeats += soldSeats.length;
        for (int i = 0; i < soldSeats.length; i++) {
            total_revenue += ticketPrice;
            int rownum = (soldSeats[i] - 1) / 4;
            int colnum = (soldSeats[i] - 1) % 4;
            seatsArray[rownum][colnum] = 1;
        }
    }

    /**
     * Method to calculate refund for the normal bus.
     *
     * @param refundChairs An array representing refunded seats.
     * @param args         Arguments passed to the method (write output to file).
     */
    public void refundCalculator(int refundChairs[], String[] args) {
        filledSeats -= refundChairs.length;
        for (int i = 0; i < refundChairs.length; i++) {
            total_revenue -= (((100 - refundCut) * ticketPrice) / 100);
            int rownum = (refundChairs[i] - 1) / 4;
            int colnum = (refundChairs[i] - 1) % 4;
            seatsArray[rownum][colnum] = 0;
        }
    }

    /**
     * Method to get the voyage type of the normal bus.
     *
     * @return The voyage type of the normal bus.
     */
    @Override
    public String getVoyageType() {
        return voyageType;
    }
}
