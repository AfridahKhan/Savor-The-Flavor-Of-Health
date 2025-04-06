import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class HydrationTracker extends HealthFeature {
    private int glasses;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public HydrationTracker(User user, int glasses) {
        super(user);
        this.glasses = glasses;
    }

    @Override
    public void logData() {
        String line = user.getName() + "," + LocalDate.now().format(FORMATTER) + "," + glasses;
        FileManager.appendToFile("water.csv", line);
    }

    @Override
    public String getFeedback() {
        int goal = GoalManager.getGoal(user.getName(), "water");
        return glasses >= goal ? "You're well hydrated!" : "Drink more water to stay healthy!";
    }
}
