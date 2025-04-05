import java.io.*;

public class HydrationTracker extends HealthFeature{
    private int glasses;

    public HydrationTracker(User user, int glasses) {
        super(user);
        this.glasses = glasses;
    }

    public void logData() {
        writeToFile("hydration.csv", user.getName() + "," + glasses);
    }

    public String getFeedback() {
        if (glasses >= 8) return "Well hydrated! Keep it up!";
        return "Try to drink at least 8 glasses of water daily.";
    }

    private void writeToFile(String filename, String data) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(data + "\n");
        } catch (IOException e) { e.printStackTrace(); }
    }
}
