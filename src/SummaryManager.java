import java.io.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

public class SummaryManager {
    private static final int DAYS_IN_WEEK = 7;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public static void displayWeeklySummary(String username) {
        List<String> features = List.of("steps", "sleep", "water", "meditation");

        for (String feature : features) {
            int goal = GoalManager.getGoal(username, feature);
            List<Integer> data = readFeatureData(username, feature);
            int total = data.stream().mapToInt(Integer::intValue).sum();
            double average = data.isEmpty() ? 0 : (double) total / data.size();
            long daysMetGoal = data.stream().filter(d -> d >= goal).count();

            System.out.println("\nFeature: " + feature.toUpperCase());
            System.out.println("Goal: " + goal);
            System.out.println("Weekly Average: " + String.format("%.2f", average));
            System.out.println("Days Met Goal: " + daysMetGoal + " out of " + DAYS_IN_WEEK);

            if (daysMetGoal >= 5) {
                System.out.println("✅ You're doing great! Keep up the streak!");
            } else if (daysMetGoal >= 3) {
                System.out.println("⚠️ Decent effort! Try to push a bit more!");
            } else {
                System.out.println("❌ Let's aim higher next week!");
            }
        }
    }

    private static List<Integer> readFeatureData(String username, String feature) {
        List<Integer> recentData = new ArrayList<>();
        File file = new File(feature + ".csv");
        LocalDate today = LocalDate.now();

        if (!file.exists()) return recentData;

        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;

            // Read all lines and check if they are within the last 7 days
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");

                if (parts.length >= 3 && parts[0].equalsIgnoreCase(username)) {
                    LocalDate date = LocalDate.parse(parts[1], FORMATTER);
                    if (!date.isBefore(today.minusDays(DAYS_IN_WEEK))) {
                        try {
                            int value = Integer.parseInt(parts[2]);
                            recentData.add(value);
                        } catch (NumberFormatException ignored) {
                        }
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return recentData;
    }
}
