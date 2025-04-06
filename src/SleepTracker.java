import java.io.*;

class SleepTracker extends HealthFeature {
    private double hours;

    public SleepTracker(User user, double hours) {
        super(user);
        this.hours = hours;
    }

    public void logData() {
        FileManager.appendToFile("sleep.csv", user.getName() + "," + hours);
    }

    public String getFeedback() {
        return hours >= 7 ? "You had a good night's sleep!" : "Try to sleep at least 7 hours.";
    }
}
