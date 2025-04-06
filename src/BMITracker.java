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
        String advice;

        if (bmi < 16.0) {
            advice = "Severely underweight. Seek professional medical advice.";
        } else if (bmi < 18.5) {
            advice = "Underweight. Increase nutritious calorie intake.";
        } else if (bmi < 25.0) {
            advice = "You're at a healthy weight. Keep up your healthy lifestyle!";
        } else if (bmi < 30.0) {
            advice = "Overweight. Start light exercise and monitor your diet.";
        } else if (bmi < 35.0) {
            advice = "Obese (Class I). Consider a structured weight loss plan.";
        } else if (bmi < 40.0) {
            advice = "Obese (Class II). Consult a health professional.";
        } else {
            advice = "Obese (Class III). Immediate medical attention is recommended.";
        }

        return String.format("Your BMI is %.2f. %s", bmi, advice);
    }
}
