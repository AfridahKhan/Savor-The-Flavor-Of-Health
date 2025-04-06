class MeditationTracker extends HealthFeature {
    private int minutes;
    private final int goal = 10;

    public MeditationTracker(User user, int minutes) {
        super(user);
        this.minutes = minutes;
    }

    public void logData() {
        FileManager.appendToFile("meditation.csv", user.getName() + "," + minutes);
        FileManager.updateStreak(user.getName(), "meditation", minutes >= goal);
    }

    public String getFeedback() {
        int streak = FileManager.getStreak(user.getName(), "meditation");
        return (minutes >= goal ?
                "You stayed mindful today 🌿! Meditation streak: " + streak + " days." :
                "Try meditating at least 10 minutes for mental clarity.");
    }
}
