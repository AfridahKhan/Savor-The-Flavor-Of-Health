import java.io.*;

class StepTracker extends HealthFeature {
    private int steps;

    public StepTracker(User user, int steps) {
        super(user);
        this.steps = steps;
    }

    public void logData() {
        writeToFile("steps.csv", user.getName() + "," + steps);
    }

    public String getFeedback() {
        if (steps >= 8000) return "Great job! You met your daily steps goal!";
        return "Try to walk a bit more today.";
    }

    private void writeToFile(String filename, String data) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(data + "\n");
        } catch (IOException e) { e.printStackTrace(); }
    }
}

