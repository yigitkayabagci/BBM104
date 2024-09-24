//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class inputreader {
    public inputreader() {
    }

    public static String[] readFile(String path, boolean discardEmptyLines, boolean trim) {
        try {
            List<String> lines = Files.readAllLines(Paths.get(path));
            if (discardEmptyLines) {
                lines.removeIf((line) -> {
                    return line.trim().equals("");
                });
            }

            if (trim) {
                lines.replaceAll(String::trim);
            }

            return (String[])lines.toArray(new String[0]);
        } catch (IOException var4) {
            var4.printStackTrace();
            return null;
        }
    }
}
