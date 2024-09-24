/**
 * The PointDistance class represents a point in a city with a specific distance from a starting point.
 * It also contains the road that leads to this point from the starting point.
 */
public class PointDistance {
    private final String city; // The city this point is located in
    private final Road road; // The road leading to this point from the starting point
    private final int distance; // The distance from the starting point to this point

    /**
     * Constructs a new PointDistance with the given city, road, and distance.
     *
     * @param city The city this point is located in.
     * @param road The road leading to this point from the starting point.
     * @param distance The distance from the starting point to this point.
     */
    PointDistance(String city,  Road road, int distance) {
        this.city = city;
        this.road = road;
        this.distance = distance;
    }

    /**
     * Returns the city this point is located in.
     *
     * @return The city this point is located in.
     */
    public String getCity() {
        return city;
    }

    /**
     * Returns the road leading to this point from the starting point.
     *
     * @return The road leading to this point from the starting point.
     */
    public Road getRoad() {
        return road;
    }

    /**
     * Returns the distance from the starting point to this point.
     *
     * @return The distance from the starting point to this point.
     */
    public int getDistance() {
        return distance;
    }
}