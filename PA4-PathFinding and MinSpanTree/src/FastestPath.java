import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
/**
 * The FastestPath class provides methods to find and print the fastest route between two points.
 */
public class FastestPath {
    /**
     * This method finds the fastest route from a starting point to all other points.
     *
     * @param start The starting point.
     * @param activePoints The list of active points.
     * @param roads The list of all roads.
     * @param shortestPaths The map of shortest paths to each point.
     */
    public static void findFastestRoute(String start,ArrayList<PointDistance> activePoints, List<Road> roads, Map<String, PointDistance> shortestPaths) {
        // Initialize the starting point with zero distance and add it to the active points and shortest paths
        PointDistance startPoint = new PointDistance(start, null, 0);
        activePoints.add(startPoint);
        shortestPaths.put(start, startPoint);

        // While there are still active points to process
        while (!activePoints.isEmpty()) {
            // Sort the active points by distance (and road ID as a tie-breaker) to always work with the shortest current path
            activePoints.sort((a, b) -> {
                if (a.getDistance() != b.getDistance()){
                    if (a.getDistance() < b.getDistance()) {
                        return -1;
                    } else {
                        return 1;
                    }
                }
                // Use road ID as a tie-breaker
                return Integer.compare(a.getRoad().getId(), b.getRoad().getId());
            });
            // Get the active point with the shortest distance and remove it from the list of active points
            PointDistance currentPoint = activePoints.get(0);
            activePoints.remove(0);

            // If this point has already been processed with a shorter path, continue with the next point
            if (shortestPaths.containsKey(currentPoint.getCity()) && shortestPaths.get(currentPoint.getCity()).getDistance() < currentPoint.getDistance()) {
                continue;
            }

            // Explore the roads starting from the current point
            for (Road road : roads) {
                if (road.getStarting_point().equals(currentPoint.getCity()) || road.getEnding_point().equals(currentPoint.getCity())) {
                    // Determine the next point
                    String nextPoint;
                    if (road.getStarting_point().equals(currentPoint.getCity())) {
                        nextPoint = road.getEnding_point();
                    }
                    else {nextPoint = road.getStarting_point();}
                    // Calculate the new distance to the next point
                    int newDistance = currentPoint.getDistance() + road.getLength();

                    // If the next point hasn't been reached yet or a shorter path to it is found, update the shortest path and continue processing
                    if (!shortestPaths.containsKey(nextPoint) || newDistance < shortestPaths.get(nextPoint).getDistance()) {
                        PointDistance nextPointDistance = new PointDistance(nextPoint, road, newDistance);
                        activePoints.add(nextPointDistance);
                        shortestPaths.put(nextPoint, nextPointDistance);  // Update the shortest path to this point
                    }
                }
            }
        }
    }


    /**
     * Returns the fastest route to calculating the shortest path to the ending point distances.
     *
     * @param end The ending point.
     * @param shortestPaths The map of shortest paths to each point.
     * @return The fastest route as a list of roads.
     */
    public static List<Road> getFastestRouteList(String end, Map<String, PointDistance> shortestPaths) {
        List<Road> distancepath = new ArrayList<>();
        PointDistance current = shortestPaths.get(end);

        // Build the path by following the shortest paths from the end point to the start point
        while (current.getRoad() != null) {
            distancepath.add(current.getRoad());
            // Move to the other point
            if (current.getRoad().getStarting_point() == (current.getCity())) {
                current = shortestPaths.get(current.getRoad().getEnding_point());
            } else {
                current = shortestPaths.get(current.getRoad().getStarting_point());
            }
        }

        return distancepath;
    }



    /**
     * Prints the fastest route from the start point to the end point.
     *
     * @param start The starting point.
     * @param end The ending point.
     * @param shortestPaths The map of shortest paths to each point.
     * @param args The array of arguments, where args[1] is the file path.
     */
    public static void printPath(String start , String end, Map<String, PointDistance> shortestPaths, String[] args) {
        ArrayList<Road> path = new ArrayList<>();
        int totalDistance = 0;
        PointDistance currPoint = shortestPaths.get(end);

        // Build the path by following the shortest paths from the end point to the start point
        while (currPoint.getRoad() != null) {
            path.add(0, currPoint.getRoad());  // Insert at the beginning to reverse the order
            totalDistance += currPoint.getRoad().getLength();  // Update the total distance
            // Move to the next point
            if (currPoint.getRoad().getStarting_point() == (currPoint.getCity())) {
                currPoint = shortestPaths.get(currPoint.getRoad().getEnding_point());
            } else {
                currPoint = shortestPaths.get(currPoint.getRoad().getStarting_point());
            }
        }

        // Print the path and total distance
        Outputwriter.writeToFile(args[1],"Fastest Route from " +  start + " to " + end + " (" + totalDistance + " KM):", true, true);
        for (Road road : path) {
            Outputwriter.writeToFile(args[1], road.getStarting_point() + "\t" + road.getEnding_point() + "\t" + road.getLength() + "\t" + road.getId(), true, true);
        }
    }
}
