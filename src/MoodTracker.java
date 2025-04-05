import java.io.*;

class MoodTracker extends HealthFeature {
    private String mood;

    public MoodTracker(User user, String mood) {
        super(user);
        this.mood = mood;
    }

    public void logData() {
        writeToFile("mood.csv", user.getName() + "," + mood);
    }

    public String getFeedback() {
        return switch (mood.toLowerCase()) {
            case "happy" -> "Awesome! Keep spreading positivity!";
            case "sad" -> "Take a moment to relax. Try meditation or a walk.";
            case "stressed" -> "Consider journaling or breathing exercises.";
            default -> "Mood logged. Keep tracking for better insights.";
        };
    }
    private void writeToFile(String filename, String data) {
        try (FileWriter fw = new FileWriter(filename, true)) {
            fw.write(data + "\n");
        } catch (IOException e) { e.printStackTrace(); }
    }
}