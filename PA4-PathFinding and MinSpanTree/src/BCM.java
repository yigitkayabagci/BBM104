import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.HashMap;
import java.util.Set;


public class BCM {

    /**
     * This method calculates the Barely Connected Map (BCM) from a given route map.
     *
     * @param bcm_route The original route map. It is a map where the keys are the points (cities) and the values are lists of roads that start from the key point.
     * @return A list of roads that forms the Barely Connected Map.
     */
    public static List<Road> calculateBarelyConnectedMap(Map<String, List<Road>> bcm_route) {
        // Get all points from the route map and sort them
        List<String> allPoints = new ArrayList<>(bcm_route.keySet());
        Collections.sort(allPoints);

        // Initialize the set of connected points and the list of roads for the BCM
        Set<String> allConnectedPoints = new HashSet<>();
        List<Road> bcmRoute = new ArrayList<>();

        // Start from the first point
        String startPoint = allPoints.get(0);
        allConnectedPoints.add(startPoint);
        // Get all roads that start from the start point
        List<Road> availableRoads = new ArrayList<>(bcm_route.get(startPoint));

        // While the BCM does not include all points
        while (bcmRoute.size() < allPoints.size() - 1) {
            // Sort available roads by length and ID using selection sort
            for (int i = 0; i < availableRoads.size() - 1; i++) {
                int minIndex = i;
                for (int j = i + 1; j < availableRoads.size(); j++) {
                    Road r1 = availableRoads.get(j);
                    Road r2 = availableRoads.get(minIndex);
                    // If the length of r1 is less than r2 or they have the same length but r1's ID is less than r2's, update minIndex
                    if (r1.getLength() < r2.getLength() || (r1.getLength() == r2.getLength() && r1.getId() < r2.getId())) {
                        minIndex = j;
                    }
                }
                // Swap the found minimum element with the first element
                Road temp = availableRoads.get(minIndex);
                availableRoads.set(minIndex, availableRoads.get(i));
                availableRoads.set(i, temp);
            }

            // Get the road with the smallest length (or smallest ID in case of a tie)
            Road smallestRoad = availableRoads.remove(0);
            // Determine the next point to connect
            String nextLocation = allConnectedPoints.contains(smallestRoad.getStarting_point()) ? smallestRoad.getEnding_point() : smallestRoad.getStarting_point();

            // If the next point is already connected, skip this iteration to avoid cycles
            if (allConnectedPoints.contains(nextLocation)) {
                continue;
            }

            // Add the smallest road to the BCM and the next location to the set of connected cities.
            bcmRoute.add(smallestRoad);
            allConnectedPoints.add(nextLocation);
            // Add all roads that start from the next point to the list of available roads, unless both points of the road are already connected
            for (Road road : bcm_route.get(nextLocation)) {
                if (!allConnectedPoints.contains(road.getStarting_point()) || !allConnectedPoints.contains(road.getEnding_point())) {
                    availableRoads.add(road);
                }
            }
        }

        // Return the list of roads that forms the Barely Connected Map
        return bcmRoute;
    }

    /**
     * This method finds the fastest route on the Barely Connected Map (BCM) from a given start point to an end point.
     *
     * @param bcm The Barely Connected Map, which is a list of roads.
     * @param start The starting point of the route.
     * @param end The ending point of the route.
     * @return A list of roads that forms the fastest route on the BCM.
     */
    public static List<Road> findFastestRouteOnBCM(List<Road> bcm, String start, String end) {
        // Create a hashmap from the BCM
        Map<String, List<Road>> roadNetworkMap = new HashMap<>();
        for (Road road : bcm) {
            // Add the road to the list of roads that start from the starting point of the road
            List<Road> roadsAtStart = roadNetworkMap.get(road.getStarting_point());
            if (roadsAtStart == null) {
                roadsAtStart = new ArrayList<>();
                roadNetworkMap.put(road.getStarting_point(), roadsAtStart);
            }
            roadsAtStart.add(road);

            // Add the road to the list of roads that start from the ending point of the road
            List<Road> roadsAtEnd = roadNetworkMap.get(road.getEnding_point());
            if (roadsAtEnd == null) {
                roadsAtEnd = new ArrayList<>();
                roadNetworkMap.put(road.getEnding_point(), roadsAtEnd);
            }
            roadsAtEnd.add(road);
        }

        // Initialize the shortest distances and the previous roads
        Map<String, Integer> shortestDistances = new HashMap<>();
        Map<String, Road> precedingRoads = new HashMap<>();
        List<PointDistance> shortestPathPoints = new ArrayList<>();
        shortestPathPoints.add(new PointDistance(start, null, 0));
        shortestDistances.put(start, 0);

        // While there are points to process
        while (!shortestPathPoints.isEmpty()) {
            // Sort the list of points to process by distance
            shortestPathPoints.sort(Comparator.comparingInt(PointDistance::getDistance));
            // Get the point with the shortest distance and remove it from the list
            PointDistance current = shortestPathPoints.get(0);
            shortestPathPoints.remove(0);
            String currentPoint = current.getCity();
            int currentDistance = current.getDistance();

            // If the destination is reached, stop processing
            if (currentPoint.equals(end)) {
                break;
            }

            // For each road that starts from the current point
            for (Road road : roadNetworkMap.get(currentPoint)) {
                // Determine the neighbor point
                String neighbor = road.getStarting_point().equals(currentPoint) ? road.getEnding_point() : road.getStarting_point();
                // Calculate the updated distance to the neighbor point
                int trimDistance = currentDistance + road.getLength();

                // If the newly calculated distance is shorter, update the shortest distance and the preceding road.
                int currentNeighborDistance;
                if (shortestDistances.containsKey(neighbor)) {
                    currentNeighborDistance = shortestDistances.get(neighbor);
                } else {
                    currentNeighborDistance = Integer.MAX_VALUE;
                }

                if (trimDistance < currentNeighborDistance) {
                    shortestDistances.put(neighbor, trimDistance);
                    precedingRoads.put(neighbor, road);

                    // Remove the current neighbor from the list of points to process
                    for (int i = 0; i < shortestPathPoints.size(); i++) {
                        if (shortestPathPoints.get(i).getCity().equals(neighbor)) {
                            shortestPathPoints.remove(i);
                            break;
                        }
                    }
                    // Add the neighbor point with the new distance to the list of points to process
                    shortestPathPoints.add(new PointDistance(neighbor, road, trimDistance));
                }
            }
        }

        // Reconstruct the path from start to end
        Set<Road> pathSet = new LinkedHashSet<>();
        String currCity = end;
        while (precedingRoads.containsKey(currCity)) {
            // Add the previous road to the path
            Road road = precedingRoads.get(currCity);
            pathSet.add(road);
            // Move to the previous point
            currCity = road.getStarting_point().equals(currCity) ? road.getEnding_point() : road.getStarting_point();
        }
        // Convert the path set to a list and reverse it to get the path from start to end
        List<Road> allPath = new ArrayList<>(pathSet);
        Collections.reverse(allPath);
        // Return the allPath.
        return allPath;
    }


}
