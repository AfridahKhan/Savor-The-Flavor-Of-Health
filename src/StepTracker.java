import java.io.*;

class StepTracker extends HealthFeature {
    private int steps;

    public StepTracker(User user, int steps) {
        super(user);
        this.steps = steps;
    }

    public void logData() {
        FileManager.appendToFile("steps.csv", user.getName() + "," + steps);
    }

    public String getFeedback() {
        return steps >= 8000 ? "Great job! You met your daily steps goal!" : "Try to walk a bit more today.";
    }
}

