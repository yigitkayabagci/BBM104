/**
 * Abstract class representing a bus.
 */
public abstract class Bus {
    protected double total_revenue = 0;
    protected int seats;
    protected int rows;
    protected int voyageID;
    protected String startingPlace;
    protected String endingPlace;
    protected double ticketPrice;
    protected int[][] seatsArray;
    protected static int total_voyage_count = 0;
    protected int filledSeats = 0;
    protected String voyageType;

    /**
     * Constructor for the Bus class.
     *
     * @param seats         The total number of seats in the bus.
     * @param rows          The number of rows in the bus.
     * @param voyageID      The ID of the voyage.
     * @param startingPlace The starting place of the voyage.
     * @param endingPlace   The ending place of the voyage.
     * @param ticketPrice   The price of a ticket.
     */
    public Bus(int seats, int rows, int voyageID, String startingPlace, String endingPlace, double ticketPrice) {
        this.seats = seats;
        this.rows = rows;
        this.voyageID = voyageID;
        this.startingPlace = startingPlace;
        this.endingPlace = endingPlace;
        this.ticketPrice = ticketPrice;
    }

    /**
     * Abstract method to calculate revenue.
     *
     * @param soldSeats An array representing sold seats.
     */
    abstract public void revenueCalculator(int soldSeats[]);

    /**
     * Abstract method to calculate refund.
     *
     * @param refundChairs An array representing refunded seats.
     * @param args         Arguments passed to the method (write output to file).
     */
    abstract public void refundCalculator(int refundChairs[], String[] args);

    // Getters for class attributes

    public int getSeats() {
        return seats;
    }

    public double gettotal_revenue() {
        return total_revenue;
    }

    public int getvoyageID() {
        return voyageID;
    }

    public String getStartingPlace() {
        return startingPlace;
    }

    public String getEndingPlace() {
        return endingPlace;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public int[][] getSeatsArray() {
        return seatsArray;
    }

    public static int getTotalVoyageCount() {
        return total_voyage_count;
    }

    public String getVoyageType() {
        return voyageType;
    }
}