import java.io.*;

class BMITracker extends HealthFeature {
    public BMITracker(User user) {
        super(user);
    }

    public void logData() {
        double bmi = user.getBMI();
        FileManager.appendToFile("bmi.csv", user.getName() + "," + bmi);
    }

    public String getFeedback() {
        double bmi = user.getBMI();
        if (bmi < 18.5) return "You're underweight. Consider a healthy diet plan.";
        else if (bmi < 24.9) return "You're at a healthy weight!";
        else return "Consider a fitness and nutrition plan.";
    }
}
