import java.util.Scanner;


public class HealthTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.println("====== Welcome to Savor the Flavor of Health ======");
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        User user = User.loginOrRegister(name, scanner);

        boolean running = true;
        while (running) {
            System.out.println("\nSelect an option to track:");
            System.out.println("1. Steps");
            System.out.println("2. Sleep");
            System.out.println("3. Water");
            System.out.println("4. BMI");
            System.out.println("5. Mood");
            System.out.println("6. Meditation");
            System.out.println("7. Exit");

            System.out.print("Your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            Trackable feature = null;

            switch (choice) {
                case 1 -> {
                    System.out.print("Enter number of steps walked: ");
                    int steps = scanner.nextInt();
                    scanner.nextLine();
                    feature = FeatureFactory.createFeature("steps", user, steps);
                }
                case 2 -> {
                    System.out.print("Enter hours of sleep: ");
                    double sleep = scanner.nextDouble();
                    scanner.nextLine();
                    feature = FeatureFactory.createFeature("sleep", user, sleep);
                }
                case 3 -> {
                    System.out.print("Enter number of water glasses: ");
                    int glasses = scanner.nextInt();
                    scanner.nextLine();
                    feature = FeatureFactory.createFeature("water", user, glasses);
                }
                case 4 -> feature = FeatureFactory.createFeature("bmi", user, null);
                case 5 -> {
                    System.out.print("Enter your mood (happy/sad/stressed/etc): ");
                    String mood = scanner.nextLine();
                    feature = FeatureFactory.createFeature("mood", user, mood);
                }
                case 6 -> {
                    System.out.print("Enter minutes meditated: ");
                    int mins = scanner.nextInt();
                    scanner.nextLine();
                    feature = FeatureFactory.createFeature("meditation", user, mins);
                }
                case 7 -> {
                    System.out.println("Goodbye! Stay healthy and mindful!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Try again.");
            }

            if (feature != null) {
                feature.logData();
                System.out.println(feature.getFeedback());
            }
        }

        scanner.close();
    }
}
