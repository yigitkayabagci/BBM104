import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class BNF {
    // This recursive method generates possible combinations based on the given grammar rules
    public static void createPossibles(Map<Character, ArrayList<String>> allCharLetters, char key, StringBuilder line) {
        // Check if the key exists in the map
        if (allCharLetters.containsKey(key)) {
            line.append("(");
            // Iterate over the array associated with the key
            for (int i = 0; i < allCharLetters.get(key).size(); i++) {
                String allLetters = allCharLetters.get(key).get(i);
                // For each character in the value, recursively call createPossibles
                for (char c : allLetters.toCharArray()) {
                    createPossibles(allCharLetters, c, line);
                }
                // Add a "|" character between options, but not after the last option
                if (i < allCharLetters.get(key).size() - 1) {
                    line.append("|");
                }
            }
            line.append(")");
        } else {
            // If the key does not exist in the map, append it directly to the line
            line.append(key);
        }
    }

    public static void main(String[] args) {
        // Read the input file
        String[] allcontent = inputreader.readFile(args[0], true, true);
        // Create a map to store the grammar rules
        Map<Character, ArrayList<String>> allCharLetters = new HashMap<>();
        String[] contentholder;
        // Create a StringBuilder to build the output
        StringBuilder output = new StringBuilder();

        // Parse the grammar rules from the input file
        for (String line : allcontent) {
            if (line.contains("->")) {
                char keyval = line.charAt(0);
                String structures = line.substring(3);
                contentholder = structures.split("\\|");

                ArrayList<String> contentList = new ArrayList<>();
                Collections.addAll(contentList, contentholder);
                allCharLetters.put(keyval, contentList);
            }
        }
        // Generate the possible combinations
        createPossibles(allCharLetters, 'S', output);
        // Write the output to the output file
        Outputwriter.writeToFile(args[1], output.toString(), false, false);
    }
}
