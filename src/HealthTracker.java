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
                case "1" -> LogDataManager.logData(scanner, user, ui);
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
}
