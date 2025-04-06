import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class SleepTracker extends HealthFeature {
    private double hours;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public SleepTracker(User user, double hours) {
        super(user);
        this.hours = hours;
    }

    @Override
    public void logData() {
        String line = user.getName() + "," + LocalDate.now().format(FORMATTER) + "," + hours;
        FileManager.appendToFile("sleep.csv", line);
    }

    @Override
    public String getFeedback() {
        int goal = GoalManager.getGoal(user.getName(), "sleep");
        return hours >= goal ? "Well rested! Keep it up!" : "Try getting more sleep for better health.";
    }
}
