import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
public class reader {
    /**
     * Reads the file at the given path and returns contents of it in a string array.
     *
     * @param path              Path to the file that is going to be read.
     * @param discardEmptyLines If true, discards empty lines with respect to trim; else, it takes all the lines from the file.
     * @param trim              Trim status; if true, trims (strip in Python) each line; else, it leaves each line as-is.
     * @return Contents of the file as a string array, returns null if there is not such a file or this program does not have sufficient permissions to read that file.
     */
    public static String[] readFile(String path, boolean discardEmptyLines, boolean trim) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            if (discardEmptyLines) {
                lines.removeIf(line -> line.trim().equals(""));
            }
            if (trim) {
                lines.replaceAll(String::trim);
            }
            return lines.toArray(new String[0]);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}