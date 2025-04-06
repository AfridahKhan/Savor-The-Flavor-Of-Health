import java.util.Scanner;

public class UserService {
    public static User loginOrRegister(String name, Scanner scanner) {
        User existingUser = FileManager.getUserFromCSV(name);
        if (existingUser != null) {
            System.out.println("Welcome back, " + existingUser.getName() + "!");
            return existingUser;
        }

        System.out.println("New user detected. Please provide your details.");
        int age = 0;
        double height = 0, weight = 0;

        while (true) {
            try {
                System.out.print("Age: ");
                age = Integer.parseInt(scanner.nextLine());
                if (age <= 0 || age > 120) throw new IllegalArgumentException("Invalid age.");
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid age (1–120).");
            }
        }

        while (true) {
            try {
                System.out.print("Height (in meters, or cm if unsure): ");
                height = Double.parseDouble(scanner.nextLine());
                if (height > 3) {
                    System.out.println("Converting height from cm to meters...");
                    height /= 100;
                }
                if (height < 0.5 || height > 2.5)
                    throw new IllegalArgumentException("Unrealistic height.");
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid height (0.5m – 2.5m).");
            }
        }

        while (true) {
            try {
                System.out.print("Weight (in kg): ");
                weight = Double.parseDouble(scanner.nextLine());
                if (weight < 10 || weight > 300)
                    throw new IllegalArgumentException("Unrealistic weight.");
                break;
            } catch (Exception e) {
                System.out.println("Please enter a valid weight (10kg – 300kg).");
            }
        }

        User newUser = new User(name, age, height, weight);
        FileManager.saveUser(newUser);
        return newUser;
    }
}
