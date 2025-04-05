import java.io.*;

class MealTracker extends HealthFeature {
    private String meal;

    public MealTracker(User user, String meal) {
        super(user);
        this.meal = meal;
    }

    public void logData() {
        writeToFile("meals.csv", user.getName() + "," + meal);
    }

    public String getFeedback() {
        return meal.contains("salad") ? "Great choice! Greens are powerful." : "Try to include more veggies next time.";
    }

    private void writeToFile(String filename, String data) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(data + "\n");
        } catch (IOException e) { e.printStackTrace(); }
    }
}
