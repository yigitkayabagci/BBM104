import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;

/**
 * The MapAnalyzer class is the entry point of the application.
 * It initializes the data structures used by the application and calls the main method of the MainFiller class.
 */
public class MapAnalyzer {
    // A list to store the roads read from the input file
    private static List<Road> roads = new ArrayList<>();
    // A map to store the shortest path to each point
    private static Map<String, PointDistance> shortestPaths = new HashMap<>();
    // A list to store the points that have been reached but not processed
    private static ArrayList<PointDistance> activePoints = new ArrayList<>();
    // A map to store the roads for each point for the calculation of the Barely Connected Map (BCM)
    private static Map<String, List<Road>> bcm_route = new HashMap<>();

    /**
     * The main method of the application.
     *
     * @param args The command line arguments. args[0] is the input file path and args[1] is the output file path.
     */
    public static void main(String[] args) {
        // Set the default locale to US to ensure consistent number formatting
        Locale.setDefault(Locale.US);
        // Call the main method of the MainFiller class to run the main logic of the application
        MainFiller.runMain(args, roads , shortestPaths, activePoints, bcm_route);
    }
}