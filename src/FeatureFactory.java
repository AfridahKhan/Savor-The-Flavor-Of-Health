class FeatureFactory {
    public static Trackable createFeature(String type, User user, Object value) {
        switch (type.toLowerCase()) {
            case "steps":
                return new StepTracker(user, (int) value);
            case "sleep":
                return new SleepTracker(user, (double) value);
            case "water":
                return new HydrationTracker(user, (int) value);
            case "bmi":
                return new BMITracker(user);
            case "mood":
                return new MoodTracker(user, (String) value);
            case "meal":
                return new MealTracker(user, (String) value);
            default:
                return new BMITracker(user); // Default fallback
        }
    }
}

