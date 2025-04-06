import java.util.Scanner;

public class ConsoleUI {

    public void showWelcomeMessage() {
        System.out.println("=================================================");
        System.out.println("        WELCOME TO SAVOR THE FLAVOR OF HEALTH     ");
        System.out.println("=================================================");
        System.out.println("A wellness tracker for both physical and mental health.");
        System.out.println("Track your steps, sleep, water intake, mood, meditation, and more!");
        System.out.println();
    }

    public void printMainMenu() {
        System.out.println("\n------------------- MAIN MENU -------------------");
        System.out.println("1. Log Health Data");
        System.out.println("2. Change Health Goals");
        System.out.println("3. View Weekly Summary");
        System.out.println("4. Exit");
        System.out.println("--------------------------------------------------");
    }

    public void printFeatureMenu() {
        System.out.println("\nSelect feature to log:");
        System.out.println("1. Steps");
        System.out.println("2. Sleep (hours)");
        System.out.println("3. Water (glasses)");
        System.out.println("4. Meditation (minutes)");
        System.out.println("5. Mood");
        System.out.println("6. BMI (auto-calculated)");
    }

    public String promptUserInput(String prompt, Scanner scanner) {
        System.out.print(prompt);
        return scanner.nextLine().trim();
    }

    public void printFeedback(String message) {
        System.out.println("Feedback: " + message);
    }

    public void printExitMessage(String userName) {
        System.out.println("\nExiting... Stay healthy, " + userName + "!\n");
    }

    public void printInvalidOption() {
        System.out.println("Invalid option. Please try again.");
    }

    public void printInvalidInput() {
        System.out.println("Invalid input. Please try again.");
    }
}
