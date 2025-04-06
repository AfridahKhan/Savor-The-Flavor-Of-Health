class StepTracker extends HealthFeature {
    private int steps;
    private int stepGoal;

    public StepTracker(User user, int steps) {
        super(user);
        this.steps = steps;
        this.stepGoal = GoalManager.getGoal(user.getName(), "steps");
    }

    public void logData() {
        FileManager.appendToFile("steps.csv", user.getName() + "," + steps);
    }

    public String getFeedback() {
        return steps >= stepGoal
                ? "Great job! You met your step goal of " + stepGoal + "!"
                : "You walked " + steps + " steps. Try to reach " + stepGoal + "!";
    }
}

