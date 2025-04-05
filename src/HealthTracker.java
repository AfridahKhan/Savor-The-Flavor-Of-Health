import java.util.List;

public class HealthTracker {
    public static void main(String[] args) {
        User user = new User("Alice", 25, 1.65, 55);

        List<Trackable> features = List.of(
                FeatureFactory.createFeature("steps", user, 9500),
                FeatureFactory.createFeature("sleep", user, 6.5),
                FeatureFactory.createFeature("water", user, 7),
                FeatureFactory.createFeature("bmi", user, null),
                FeatureFactory.createFeature("mood", user, "stressed"),
                FeatureFactory.createFeature("meal", user, "burger and fries")
        );

        for (Trackable feature : features) {
            feature.logData();
            System.out.println(feature.getFeedback());
        }
    }
}