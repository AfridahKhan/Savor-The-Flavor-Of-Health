import java.io.*;
import java.util.*;

public class GoalManager {
    private static final String FILE_NAME = "goals.csv";

    // Default goals for features
    private static final Map<String, Integer> DEFAULT_GOALS = Map.of(
            "steps", 8000,
            "sleep", 7,
            "water", 8,
            "meditation", 15
    );

    // Get the goal for a specific user and feature
    public static int getGoal(String username, String feature) {
        try (BufferedReader br = new BufferedReader(new FileReader(FILE_NAME))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equalsIgnoreCase(username) && parts[1].equalsIgnoreCase(feature)) {
                    return Integer.parseInt(parts[2]);
                }
            }
        } catch (IOException e) {
            // File may not exist yet — fallback to default
        }

        return DEFAULT_GOALS.getOrDefault(feature.toLowerCase(), 0);
    }

    // Set or update goal for a user and feature
    public static void setGoal(String username, String feature, int value) {
        File file = new File(FILE_NAME);
        List<String> updatedLines = new ArrayList<>();
        boolean updated = false;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts.length == 3 && parts[0].equalsIgnoreCase(username) && parts[1].equalsIgnoreCase(feature)) {
                    updatedLines.add(username + "," + feature + "," + value);
                    updated = true;
                } else {
                    updatedLines.add(line);
                }
            }
        } catch (IOException e) {
            // If file doesn't exist, start fresh
        }

        if (!updated) {
            updatedLines.add(username + "," + feature + "," + value);
        }

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            for (String updatedLine : updatedLines) {
                bw.write(updatedLine);
                bw.newLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Show current goal (used before asking user to change)
    public static void showCurrentGoal(String username, String feature) {
        int currentGoal = getGoal(username, feature);
        System.out.println("Current goal for " + feature + ": " + currentGoal);
    }
}
