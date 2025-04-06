import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class StepTracker extends HealthFeature {
    private int steps;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public StepTracker(User user, int steps) {
        super(user);
        this.steps = steps;
    }

    @Override
    public void logData() {
        String line = user.getName() + "," + LocalDate.now().format(FORMATTER) + "," + steps;
        FileManager.appendToFile("steps.csv", line);
    }

    @Override
    public String getFeedback() {
        int goal = GoalManager.getGoal(user.getName(), "steps");
        return steps >= goal ? "Great job! You met your step goal!" : "Try to take more steps tomorrow!";
    }
}
