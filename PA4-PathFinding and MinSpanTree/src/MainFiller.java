import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * The MainFiller class is responsible for running the main logic of the application.
 * It reads the input file, calculates the fastest route and the Barely Connected Map (BCM),
 * and writes the results to the output file.
 */
public class MainFiller {

    /**
     * The main method of the application.
     *
     * @param args The command line arguments. args[0] is the input file path and args[1] is the output file path.
     * @param roads A list to store the roads read from the input file.
     * @param shortestPaths A map to store the shortest path to each point.
     * @param activePoints A list to store the points that have been reached but not processed.
     * @param bcm_route A map to store the roads for each point for the calculation of the BCM.
     */
    public static void runMain(String[] args, List<Road> roads, Map<String, PointDistance> shortestPaths, ArrayList<PointDistance> activePoints, Map<String, List<Road>> bcm_route) {
        String[] allContent = inputreader.readFile(args[0], true, true);
        // Clear the output file
        Outputwriter.writeToFile(args[1], "", false, false);

        // The first line of the input file contains the start and end points
        String[] points = allContent[0].split("\t");
        String start = points[0];
        String end = points[1];

        // Process the subsequent lines of the input file for road content
        for (int i = 1; i < allContent.length; i++) {
            String[] content = allContent[i].split("\t");
            String startPoint = content[0];
            String endPoint = content[1];
            int length = Integer.parseInt(content[2]);
            int id = Integer.parseInt(content[3]);

            // Create a new road and add it to the list of roads
            Road road = new Road(startPoint, endPoint, length, id);
            roads.add(road);

            // Populate the bcm_route map for the calculation of the BCM
            List<Road> startPointList = bcm_route.get(startPoint);
            List<Road> endPointList = bcm_route.get(endPoint);
            if (startPointList == null) {
                startPointList = new ArrayList<>();
                bcm_route.put(startPoint, startPointList);
            }

            startPointList.add(road);

            if (endPointList == null) {
                endPointList = new ArrayList<>();
                bcm_route.put(endPoint, endPointList);
            }
            endPointList.add(road);
        }

        // Calculate the fastest route
        FastestPath.findFastestRoute(start, activePoints, roads, shortestPaths);
        // Print the fastest route to the output file
        FastestPath.printPath(start, end, shortestPaths, args);

        // Calculate the BCM
        List<Road> bcm = BCM.calculateBarelyConnectedMap(bcm_route);
        // Print the BCM to the output file
        Outputwriter.writeToFile(args[1], "Roads of Barely Connected Map is:", true, true);
        Road.roadsToString(bcm, args);

        // Calculate the fastest route on the BCM
        List<Road> fastestRouteOnBCM = BCM.findFastestRouteOnBCM(bcm, start, end);
        int intdistance = (int)(Road.getTotalDistance(fastestRouteOnBCM));
        // Print the fastest route on the BCM to the output file
        Outputwriter.writeToFile(args[1], "Fastest Route from " + start + " to " + end + " on Barely Connected Map (" + intdistance + " KM):", true, true);
        Road.printRoads(fastestRouteOnBCM, args);

        // getFastestRoutelist method return to the  list of fastest route to calculate the shortest path to the ending point distances.
        List<Road> fastestRoute = FastestPath.getFastestRouteList(end, shortestPaths);
        // Perform the analysis and print the results to the output file
        makeAnalysis(fastestRouteOnBCM, fastestRoute, roads, bcm, args);
    }

    /**
     * Performs the analysis of the results and writes the results to the output file.
     *
     * @param fastestRouteOnBCM The fastest route on the BCM.
     * @param fastestRoute The fastest route on the original map.
     * @param roads The list of all roads.
     * @param bcm The BCM.
     * @param args The command line arguments. args[1] is the output file path.
     */
    private static void makeAnalysis(List<Road> fastestRouteOnBCM, List<Road> fastestRoute, List<Road> roads, List<Road> bcm, String[] args){
        // Calculate the ratio of the total distances of the fastest routes on the BCM and the original map
        double rateofFastRoute = Road.getTotalDistance(fastestRouteOnBCM) / Road.getTotalDistance(fastestRoute);
        // Calculate the ratio of the total distances of the BCM and the original map
        double rateofConstruction = Road.getTotalDistance(bcm) / Road.getTotalDistance(roads);

        // Write the analysis results to the output file
        Outputwriter.writeToFile(args[1], "Analysis:", true, true);
        String formattedString = String.format("Ratio of Construction Material Usage Between Barely Connected and Original Map: %.2f%n", rateofConstruction);
        Outputwriter.writeToFile(args[1], formattedString, true, false);
        String formattedString2 = String.format("Ratio of Fastest Route Between Barely Connected and Original Map: %.2f", rateofFastRoute);
        Outputwriter.writeToFile(args[1], formattedString2, true, false);
    }
}