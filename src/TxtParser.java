import java.io.*;
import java.util.Scanner;

/**
 * Created by ggladko97 on 03.11.16.
 */
public class TxtParser {
    public TxtParser(){}
    public String parse(String filePath) throws IOException {
        File file = new File(filePath);
        StringBuilder fileContents = new StringBuilder((int)file.length());
        Scanner scanner = new Scanner(file);
        String lineSeparator = System.getProperty("line.separator");
        try {
            while(scanner.hasNextLine()) {
                fileContents.append(scanner.nextLine() + lineSeparator);
            }
            return fileContents.toString();
        } finally {
            scanner.close();
        }
    }
}
