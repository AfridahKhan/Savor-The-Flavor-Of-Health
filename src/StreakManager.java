import java.io.*;
import java.util.*;

public class StreakManager {
    private static final String STREAK_FILE = "streaks.csv";

    public static int getStreak(String username, String feature) {
        File file = new File(STREAK_FILE);
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

    public static void updateStreak(String username, String feature, boolean goalAchieved) {
        File file = new File(STREAK_FILE);
        List<String> lines = new ArrayList<>();
        boolean found = false;
        int newStreak = goalAchieved ? 1 : 0;

        try {
            if (file.exists()) {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;
                while ((line = br.readLine()) != null) {
                    String[] parts = line.split(",");
                    if (parts[0].equalsIgnoreCase(username) && parts[1].equalsIgnoreCase(feature)) {
                        int current = Integer.parseInt(parts[2]);
                        newStreak = goalAchieved ? current + 1 : 0;
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

