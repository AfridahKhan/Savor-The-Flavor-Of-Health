public class MeditationTracker extends HealthFeature {
    private int minutes;
    private final int goal = 10; // Daily meditation goal in minutes

    public MeditationTracker(User user, int minutes) {
        super(user);
        this.minutes = minutes;
    }

    @Override
    public void logData() {

        FileManager.appendToFile("meditation.csv", user.getName() + "," + minutes);
        StreakManager.updateStreak(user.getName(), "meditation", minutes >= goal);
    }

    @Override
    public String getFeedback() {
        String base = (minutes >= goal)
                ? "Nice work! You're cultivating calmness."
                : "Try to meditate a bit longer for full benefit.";

        int streak = StreakManager.getStreak(user.getName(), "meditation");
        return base + " Current meditation streak: " + streak + " day(s).";
    }
}
