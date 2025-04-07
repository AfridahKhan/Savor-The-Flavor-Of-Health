import java.io.*;
import java.util.*;

public class GoalManager {

    private static final String GOAL_DIR = "data/goals/";
    private static final Map<String, Map<String, Integer>> userGoals = new HashMap<>();

    public static int getGoal(String userName, String feature) {
        loadGoalsIfNeeded(userName);
        return userGoals
                .getOrDefault(userName, new HashMap<>())
                .getOrDefault(feature.toLowerCase(), getDefaultGoal(feature));
    }

    public static void setGoal(String userName, String feature, int goal) {
        loadGoalsIfNeeded(userName);
        userGoals.putIfAbsent(userName, new HashMap<>());
        userGoals.get(userName).put(feature.toLowerCase(), goal);
        saveGoals(userName);
    }

    public static void changeGoal(Scanner scanner, String userName) {
        loadGoalsIfNeeded(userName);

        System.out.println("\nWhich goal would you like to change?");
        System.out.println("1. Steps");
        System.out.println("2. Water (glasses)");
        System.out.println("3. Sleep (hours)");
        System.out.println("4. Meditation (minutes)");
        System.out.print("Enter your choice: ");
        String choice = scanner.nextLine();
        String feature = null;
        switch (choice) {
            case "1":
                feature = "steps";
                break;
            case "2":
                feature = "water";
                break;
            case "3":
                feature = "sleep";
                break;
            case "4":
                feature = "meditation";
                break;
            default:
                System.out.println("Invalid option.");
                return;
        }

        int currentGoal = getGoal(userName, feature);
        System.out.println("Current goal for " + feature + ": " + currentGoal);

        System.out.print("Enter new goal (or press Enter to keep current): ");
        String input = scanner.nextLine().trim();

        if (!input.isEmpty()) {
            try {
                int newGoal = Integer.parseInt(input);
                setGoal(userName, feature, newGoal);
                System.out.println("Goal updated for " + feature + ": " + newGoal);
            } catch (NumberFormatException e) {
                System.out.println("Invalid number format. Goal not changed.");
            }
        } else {
            System.out.println("Goal unchanged.");
        }
    }

    private static int getDefaultGoal(String feature) {
        return switch (feature.toLowerCase()) {
            case "steps" -> 10000;
            case "water" -> 8;
            case "sleep" -> 7;
            case "meditation" -> 10;
            default -> 0;
        };
    }

    private static void loadGoalsIfNeeded(String userName) {
        if (userGoals.containsKey(userName)) return;

        File file = new File(GOAL_DIR + userName + ".txt");
        Map<String, Integer> goals = new HashMap<>();

        if (file.exists()) {
            try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                String line;

                while ((line = reader.readLine()) != null) {
                    String[] parts = line.split(":");
                    if (parts.length == 2) {
                        goals.put(parts[0].trim().toLowerCase(), Integer.parseInt(parts[1].trim()));
                    }
                }
            } catch (IOException | NumberFormatException e) {
                System.out.println("Failed to load goals for user: " + userName);
            }
        }

        userGoals.put(userName, goals);
    }

    private static void saveGoals(String userName) {
        File dir = new File(GOAL_DIR);
        if (!dir.exists()) dir.mkdirs();

        File file = new File(GOAL_DIR + userName + ".txt");

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            Map<String, Integer> goals = userGoals.get(userName);
            if (goals != null) {
                for (Map.Entry<String, Integer> entry : goals.entrySet()) {
                    writer.write(entry.getKey() + ":" + entry.getValue());
                    writer.newLine();
                }
            }
        } catch (IOException e) {
            System.out.println("Failed to save goals for user: " + userName);
        }
    }
}
