import java.io.*;

public class FileManager {
    public static void appendToFile(String filename, String data) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User getUserFromCSV(String name) {
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(name)) {
                    return new User(
                            parts[0],
                            Integer.parseInt(parts[1]),
                            Double.parseDouble(parts[2]),
                            Double.parseDouble(parts[3])
                    );
                }
            }
        } catch (IOException e) {
            // file may not exist yet
        }
        return null;
    }

    public static void saveUser(User user) {
        appendToFile("users.csv", user.toCSV());
    }
}

