class StepTracker extends HealthFeature {
    private int steps;
    private final int goal = 8000;

    public StepTracker(User user, int steps) {
        super(user);
        this.steps = steps;
    }

    public void logData() {
        FileManager.appendToFile("steps.csv", user.getName() + "," + steps);
        StreakManager.updateStreak(user.getName(), "steps", steps >= goal);
    }

    public String getFeedback() {
        int streak = StreakManager.getStreak(user.getName(), "steps");
        return (steps >= goal ?
                "You crushed your step goal! 🚶‍♀️ Streak: " + streak + " days." :
                "Walk a bit more! Your streak has been reset.");
    }
}


