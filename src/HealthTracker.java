import java.util.*;

public class HealthTracker {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter your name: ");
        String name = scanner.nextLine();
        User user = UserService.loginOrRegister(name, scanner);


        System.out.print("Do you want to update any goals? (yes/no): ");
        if (scanner.nextLine().equalsIgnoreCase("yes")) {
            List<String> goalTypes = List.of("steps", "sleep", "water", "meditation");

            for (String goalType : goalTypes) {
                GoalManager.showCurrentGoal(user.getName(), goalType);

                System.out.print("Do you want to update your " + goalType + " goal? (yes/no): ");
                if (scanner.nextLine().equalsIgnoreCase("yes")) {
                    System.out.print("Enter new goal for " + goalType + ": ");
                    try {
                        int newGoal = Integer.parseInt(scanner.nextLine());
                        if (newGoal > 0) {
                            GoalManager.setGoal(user.getName(), goalType, newGoal);
                            System.out.println(goalType + " goal updated to " + newGoal);
                        } else {
                            System.out.println("Goal must be a positive number.");
                        }
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number input.");
                    }
                }
                System.out.println();
            }
        }


        List<Trackable> features = new ArrayList<>();


        System.out.print("\nEnter how many steps you walked today: ");
        int steps = Integer.parseInt(scanner.nextLine());
        features.add(FeatureFactory.createFeature("steps", user, steps));


        System.out.print("Enter how many hours you slept: ");
        double sleep = Double.parseDouble(scanner.nextLine());
        features.add(FeatureFactory.createFeature("sleep", user, sleep));


        System.out.print("How many glasses of water did you drink?: ");
        int water = Integer.parseInt(scanner.nextLine());
        features.add(FeatureFactory.createFeature("water", user, water));


        System.out.print("How are you feeling? (happy/sad/stressed/other): ");
        String mood = scanner.nextLine();
        features.add(FeatureFactory.createFeature("mood", user, mood));


        System.out.print("How many minutes did you meditate today?: ");
        int meditation = Integer.parseInt(scanner.nextLine());
        features.add(FeatureFactory.createFeature("meditation", user, meditation));


        features.add(FeatureFactory.createFeature("bmi", user, null));

     
        System.out.println("\n--- Health Feedback ---");
        for (Trackable feature : features) {
            feature.logData();
            System.out.println(feature.getFeedback());
        }

        scanner.close();
    }
}
