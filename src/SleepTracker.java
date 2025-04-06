import java.io.*;

class SleepTracker extends HealthFeature {
    private double hours;
    private final double goal = 7.0;

    public SleepTracker(User user, double hours) {
        super(user);
        this.hours = hours;
    }

    public void logData() {
        FileManager.appendToFile("sleep.csv", user.getName() + "," + hours);
        FileManager.updateStreak(user.getName(), "sleep", hours >= goal);
    }

    public String getFeedback() {
        int streak = FileManager.getStreak(user.getName(), "sleep");
        return (hours >= goal ?
                "Well rested! 😴 Sleep streak: " + streak + " days." :
                "Try to sleep at least 7 hours. Sleep streak reset.");
    }
}
