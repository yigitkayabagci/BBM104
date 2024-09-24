import java.util.List;

/**
 * The Road class represents a road with a starting point, an ending point, a length, and an ID.
 */
public class Road {
    private final String starting_point;
    private final String ending_point;
    private final int length;
    private final int id;

    /**
     * Constructs a new Road with the given starting point, ending point, length, and ID.
     *
     * @param starting_point The starting point of the road.
     * @param ending_point The ending point of the road.
     * @param length The length of the road.
     * @param id The ID of the road.
     */
    Road(String starting_point, String ending_point, int length, int id) {
        this.starting_point = starting_point;
        this.ending_point = ending_point;
        this.length = length;
        this.id = id;
    }

    /**
     * Returns the starting point of the road.
     *
     * @return The starting point of the road.
     */
    public String getStarting_point() {
        return starting_point;
    }

    /**
     * Returns the ending point of the road.
     *
     * @return The ending point of the road.
     */
    public String getEnding_point() {
        return ending_point;
    }

    /**
     * Returns the length of the road.
     *
     * @return The length of the road.
     */
    public int getLength() {
        return length;
    }

    /**
     * Returns the ID of the road.
     *
     * @return The ID of the road.
     */
    public int getId() {
        return id;
    }

    /**
     * Calculates the total distance of all roads in the given list.
     *
     * @param roads The list of roads.
     * @return The total distance of all roads in the list.
     */
    public static double getTotalDistance(List<Road> roads) {
        double totaldistance = 0;
        for (int i= 0; i < roads.size(); i++){
            totaldistance =  roads.get(i).getLength()  + totaldistance;
        }
        return totaldistance;
    }

    /**
     * Prints the details of all roads in the given list to a file.
     *
     * @param roads The list of roads.
     * @param args The array of arguments, where args[1] is the file path.
     */
    public static void printRoads(List<Road> roads , String[] args) {
        for (Road road : roads) {
            Outputwriter.writeToFile(args[1], road.getStarting_point() + "\t" + road.getEnding_point() + "\t" + road.getLength() + "\t" + road.getId(), true, true);
        }
    }

    /**
     * Sorts the given list of roads by length and ID, and writes the details of each road to a file.
     *
     * @param roads The list of roads.
     * @param args The array of arguments, where args[1] is the file path.
     */
    public static void roadsToString(List<Road> roads , String[] args) {
        // Custom sort implementation
        for (int i = 0; i < roads.size() - 1; i++) {
            for (int j = 0; j < roads.size() - i - 1; j++) {
                Road road1 = roads.get(j);
                Road road2 = roads.get(j + 1);

                // Compare by length
                if (road1.getLength() > road2.getLength()) {
                    roads.set(j, road2);
                    roads.set(j + 1, road1);
                }
                // If lengths are equal, compare by ID
                else if (road1.getLength() == road2.getLength() && road1.getId() > road2.getId()) {
                    roads.set(j, road2);
                    roads.set(j + 1, road1);
                }
            }
        }

        for (Road road : roads) {
            String trmline =  (String.format("%s\t%s\t%d\t%d", road.getStarting_point(), road.getEnding_point(), road.getLength(), road.getId()));
            Outputwriter.writeToFile(args[1], trmline, true, true);
        }
    }
}