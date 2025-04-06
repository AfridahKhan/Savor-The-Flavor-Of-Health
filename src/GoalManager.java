import java.io.*;
import java.util.*;

public class GoalManager {
    private static final String GOAL_FILE = "goals.csv";
    private static final Map<String, Integer> defaultGoals = Map.of(
            "steps", 8000,
            "sleep", 7,
            "water", 8,
            "meditation", 15
    );

    // Load saved goals or return default
    public static int getGoal(String username, String type) {
        try (BufferedReader br = new BufferedReader(new FileReader(GOAL_FILE))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equalsIgnoreCase(username) && parts[1].equalsIgnoreCase(type)) {
                    return Integer.parseInt(parts[2]);
                }
            }
        } catch (IOException ignored) {}
        return defaultGoals.getOrDefault(type, 0);
    }

    // Update a specific goal for a user
    public static void setGoal(String username, String type, int newGoal) {
        File tempFile = new File("temp_goals.csv");
        File originalFile = new File(GOAL_FILE);
        boolean found = false;

        try (BufferedReader br = new BufferedReader(new FileReader(GOAL_FILE));
             FileWriter fw = new FileWriter(tempFile)) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(username) && parts[1].equalsIgnoreCase(type)) {
                    fw.write(username + "," + type + "," + newGoal + "\n");
                    found = true;
                } else {
                    fw.write(line + "\n");
                }
            }
        } catch (IOException e) {
            // File may not exist yet; skip
        }

        if (!found) {
            try (FileWriter fw = new FileWriter(tempFile, true)) {
                fw.write(username + "," + type + "," + newGoal + "\n");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        // Replace old file with new
        if (originalFile.exists()) originalFile.delete();
        tempFile.renameTo(originalFile);
    }
}
