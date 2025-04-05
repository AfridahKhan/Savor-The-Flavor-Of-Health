import java.util.Scanner;

public class HealthTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Enter your name: ");
        String name = scanner.nextLine();

        User user = User.loginOrRegister(name, scanner);

        while (true) {
            System.out.println("\nWhich health parameter do you want to log?");
            System.out.println("1. Steps");
            System.out.println("2. Sleep");
            System.out.println("3. Water Intake");
            System.out.println("4. BMI");
            System.out.println("5. Mood");
            System.out.println("6. Meal");
            System.out.println("7. Exit");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); // consume newline

            if (choice == 7) {
                System.out.println("Logging out. Stay healthy!");
                break;
            }

            Trackable feature = null;

            try {
                switch (choice) {
                    case 1 -> {
                        System.out.print("Enter number of steps: ");
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
                        int water = scanner.nextInt();
                        scanner.nextLine();
                        feature = FeatureFactory.createFeature("water", user, water);
                    }
                    case 4 -> feature = FeatureFactory.createFeature("bmi", user, 0);
                    case 5 -> {
                        System.out.print("Enter your mood (happy/sad/stressed/etc.): ");
                        String mood = scanner.nextLine();
                        feature = FeatureFactory.createFeature("mood", user, mood);
                    }
                    case 6 -> {
                        System.out.print("Enter your meal: ");
                        String meal = scanner.nextLine();
                        feature = FeatureFactory.createFeature("meal", user, meal);
                    }
                    default -> System.out.println("Invalid choice.");
                }

                if (feature != null) {
                    feature.logData();
                    System.out.println("Feedback: " + feature.getFeedback());
                }

            } catch (Exception e) {
                System.out.println("Error: " + e.getMessage());
            }
        }

        scanner.close();
    }
}
