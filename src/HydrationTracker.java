public class HydrationTracker extends HealthFeature{
    private int glasses;
    private final int goal = 8;

    public HydrationTracker(User user, int glasses) {
        super(user);
        this.glasses = glasses;
    }

    public void logData() {
        FileManager.appendToFile("hydration.csv", user.getName() + "," + glasses);
        StreakManager.updateStreak(user.getName(), "hydration", glasses >= goal);
    }

    public String getFeedback() {
        int streak = StreakManager.getStreak(user.getName(), "hydration");
        return (glasses >= goal ?
                "Great hydration today! 💧 Current streak: " + streak + " days." :
                "Try to reach 8 glasses! Current streak reset.") ;
    }
}
