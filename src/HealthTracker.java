import java.util.*;

public class HealthTracker {

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        ConsoleUI ui = new ConsoleUI();
        UserService userService = new UserService();

        ui.showWelcomeMessage();
        String name = ui.promptUserInput("Enter your name: ", scanner);
        User user = userService.loginOrRegister(name, scanner);

        boolean running = true;
        while (running) {
            ui.printMainMenu();
            String choice = ui.promptUserInput("Choose an option: ", scanner);

            switch (choice) {
                case "1" -> logFeature(scanner, user, ui);
                case "2" -> GoalManager.changeGoal(scanner, user.getName());
                case "3" -> SummaryManager.displayWeeklySummary(user.getName());
                case "4" -> {
                    ui.printExitMessage(user.getName());
                    running = false;
                }
                default -> ui.printInvalidOption();
            }
        }

        scanner.close();
    }

    private static void logFeature(Scanner scanner, User user, ConsoleUI ui) {
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
