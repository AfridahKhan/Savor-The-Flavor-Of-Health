import java.io.*;

class SleepTracker extends HealthFeature {
    private double hours;

    public SleepTracker(User user, double hours) {
        super(user);
        this.hours = hours;
    }

    public void logData() {
        writeToFile("sleep.csv", user.getName() + "," + hours);
    }

    public String getFeedback() {
        if (hours >= 7) return "You had a good night's sleep!";
        return "Try to sleep at least 7 hours for better health.";
    }

    private void writeToFile(String filename, String data) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(data + "\n");
        } catch (IOException e) { e.printStackTrace(); }
    }
}
