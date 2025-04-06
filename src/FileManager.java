import java.io.*;
import java.util.*;

public class FileManager {
    public static void appendToFile(String filename, String data) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(data + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static int getStreak(String username, String feature) {
        File file = new File("streaks.csv");
        if (!file.exists()) return 0;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(username) && parts[1].equalsIgnoreCase(feature)) {
                    return Integer.parseInt(parts[2]);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }

    public static void updateStreak(String username, String feature, boolean achieved) {
        File file = new File("streaks.csv");
        List<String> lines = new ArrayList<>();
        boolean found = false;

        int newStreak = achieved ? 1 : 0;

        try {
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equalsIgnoreCase(username) && parts[1].equalsIgnoreCase(feature)) {
                        int current = Integer.parseInt(parts[2]);
                        newStreak = achieved ? current + 1 : 0;
                        lines.add(username + "," + feature + "," + newStreak);
                        found = true;
                    } else {
                        lines.add(line);
                    }
                }
                br.close();
            }

            if (!found) {
                lines.add(username + "," + feature + "," + newStreak);
            }

            FileWriter fw = new FileWriter(file);
            for (String l : lines) {
                fw.write(l + "\n");
            }
            fw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

