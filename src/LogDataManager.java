import java.util.Scanner;

public class LogDataManager {

    public static void logData(Scanner scanner, User user, ConsoleUI ui) {
        ui.printFeatureMenu();
        String input = ui.promptUserInput("Your choice: ", scanner);

        try {
            Trackable feature = switch (input) {
                case "1" -> {
                        int steps = Integer.parseInt(ui.promptUserInput("Enter steps: ", scanner));
                    yield FeatureFactory.createFeature("steps", user, steps);
                }
                case "2" -> {
                    double sleep = Double.parseDouble(ui.promptUserInput("Enter sleep hours: ", scanner));
                    yield FeatureFactory.createFeature("sleep", user, sleep);
                }
                case "3" -> {
                    int water = Integer.parseInt(ui.promptUserInput("Enter glasses of water: ", scanner));
                    yield FeatureFactory.createFeature("water", user, water);
                }
                case "4" -> {
                    int minutes = Integer.parseInt(ui.promptUserInput("Enter meditation minutes: ", scanner));
                    yield FeatureFactory.createFeature("meditation", user, minutes);
                }
                case "5" -> {
                    String mood = ui.promptUserInput("Enter mood (happy/sad/stressed/other): ", scanner);
                    yield FeatureFactory.createFeature("mood", user, mood);
                }
                case "6" -> FeatureFactory.createFeature("bmi", user, null);
                default -> {
                    ui.printInvalidOption();
                    yield null;
                }
            };

            if (feature != null) {
                feature.logData();
                ui.printFeedback(feature.getFeedback());
            }
        } catch (Exception e) {
            ui.printInvalidInput();
        }
    }
}
