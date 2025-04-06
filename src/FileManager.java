import java.io.FileWriter;
import java.io.IOException;

public class FileManager {
    public static void appendToFile(String filename, String data) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
