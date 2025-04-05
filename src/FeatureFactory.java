class FeatureFactory {
    public static Trackable createFeature(String type, User user, Object value) {
        return switch (type.toLowerCase()) {
            case "steps" -> new StepTracker(user, (int) value);
            case "water" -> new HydrationTracker(user, (int) value);
            case "bmi" -> new BMITracker(user);
            case "meal" -> new MealTracker(user, (String) value);
            default -> throw new IllegalArgumentException("Invalid feature type");
        };
    }
}

