class User {
    private String name;
    private int age;
    private double height;
    private double weight;

    public User(String name, int age, double height, double weight) {
        this.name = name;
        this.age = age;
        this.height = height;
        this.weight = weight;
    }

    public String getName() { return name; }
    public double getBMI() { return weight / (height * height); }
}