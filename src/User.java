import java.io.*;
import java.util.Scanner;

class User {
    private String name;
    private int age;
    private double height;
    private double weight;

    public User(String name, int age, double height, double weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public String getName() { return name; }
    public double getBMI() { return weight / (height * height); }

    public String toCSV() {
        return name + "," + age + "," + height + "," + weight;
    }

    public static void saveUser(User user) {
        try (FileWriter fw = new FileWriter("users.csv", true)) {
            fw.write(user.toCSV() + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static User loginOrRegister(String name, Scanner scanner) {
        try (BufferedReader br = new BufferedReader(new FileReader("users.csv"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split(",");
                if (parts[0].equalsIgnoreCase(name)) {
                    return new User(parts[0], Integer.parseInt(parts[1]), Double.parseDouble(parts[2]), Double.parseDouble(parts[3]));
                }
            }
        } catch (IOException e) {
            // File might not exist yet
        }

        System.out.println("New user detected. Please provide your details.");
        System.out.print("Age: ");
        int age = scanner.nextInt();
        System.out.print("Height (in meters): ");
        double height = scanner.nextDouble();
        System.out.print("Weight (in kg): ");
        double weight = scanner.nextDouble();
        scanner.nextLine(); // consume newline

        User newUser = new User(name, age, height, weight);
        saveUser(newUser);
        return newUser;
    }
   }