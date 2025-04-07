import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class MeditationTracker extends HealthFeature {
    private int minutes;
    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    public MeditationTracker(User user, int minutes) {
        super(user);
        this.minutes = minutes;
    }

    @Override
    public void logData() {
        String line = user.getName() + "," + LocalDate.now().format(FORMATTER) + "," + minutes;
        FileManager.appendToFile("meditation.csv", line);
    }

    @Override
    public String getFeedback() {
        int goal = GoalManager.getGoal(user.getName(), "meditation");
        return minutes >= goal ? "You're calm and focused!" : "Try meditating more to reduce stress.";
    }
}

