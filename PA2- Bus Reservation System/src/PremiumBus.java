/**
 * Class representing a premium bus extending the Bus class.
 */
public class PremiumBus extends Bus {
    // Define the premium fee for the premium bus
    protected int premiumFee;
    // Define the refund cut for the premium bus
    protected int refundCut;
    // Define the voyage type for the premium bus
    private String voyageType = "Premium";

    /**
     * Constructor for the PremiumBus class.
     *
     * @param rows          The number of rows in the premium bus.
     * @param voyageID      The ID of the voyage.
     * @param startingPlace The starting place of the voyage.
     * @param endingPlace   The ending place of the voyage.
     * @param ticketPrice   The price of a ticket.
     * @param premiumFee    The premium fee for premium seats.
     * @param refundCut     The percentage of refund cut.
     */
    public PremiumBus(int rows, int voyageID, String startingPlace, String endingPlace, double ticketPrice, int premiumFee, int refundCut) {
        super(3, rows, voyageID, startingPlace, endingPlace, ticketPrice); // Call the superclass constructor with the appropriate argument
        this.premiumFee = premiumFee;
        this.refundCut = refundCut;
        seatsArray = new int[rows][3];
        total_voyage_count++;
    }

    /**
     * Method to calculate revenue for the premium bus.
     *
     * @param soldSeats An array representing sold seats.
     */
    @Override
    public void revenueCalculator(int soldSeats[]) {
        filledSeats += soldSeats.length;
        for (int i = 0; i < soldSeats.length; i++) {
            if ((soldSeats[i] - 1) % 3 == 0) {
                total_revenue += (ticketPrice * (premiumFee + 100)) / 100;
            } else {
                total_revenue += ticketPrice;
            }
            int rownum = (soldSeats[i] - 1) / 3;
            int colnum = (soldSeats[i] - 1) % 3;
            seatsArray[rownum][colnum] = 1;
        }
    }

    /**
     * Method to calculate refund for the premium bus.
     *
     * @param refundChairs An array representing refunded seats.
     * @param args         Arguments passed to the method (write output to file).
     */
    @Override
    public void refundCalculator(int refundChairs[], String[] args) {
        filledSeats -= refundChairs.length;
        for (int i = 0; i < refundChairs.length; i++) {
            if ((refundChairs[i] - 1) % 3 == 0) {
                total_revenue -= (((100 - refundCut) * ((ticketPrice * (premiumFee + 100)) / 100)) / 100);
            } else {
                total_revenue -= (((100 - refundCut) * ticketPrice) / 100);
            }
            int rownum = (refundChairs[i] - 1) / 3;
            int colnum = (refundChairs[i] - 1) % 3;
            seatsArray[rownum][colnum] = 0;
        }
    }

    /**
     * Method to get the voyage type of the premium bus.
     *
     * @return The voyage type of the premium bus.
     */
    @Override
    public String getVoyageType() {
        return voyageType;
    }

    /**
     * Method to get the premium fee of the premium bus.
     *
     * @return The premium fee of the premium bus.
     */
    public int getPremiumFee() {
        return premiumFee;
    }
}
