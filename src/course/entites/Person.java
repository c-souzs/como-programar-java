package course.entites;

public class Person {
    private String name;
    private int age;
    private double height;
    private String email;

    public Person(String name, int age, double height) {
        this.name = name;
        this.age = age;
        this.height = height;
    }

    public Person(String name, int age, double height, String email) {
        this(name, age, height); // Chama o construtor de cima

        this.email = email;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public double getHeight() {
        return height;
    }

    public String getEmail() {
        return email;
    }

    @Override
    public String toString() {
        return "Person{" +
                "name='" + name + '\'' +
                ", age=" + age +
                ", height=" + height +
                ", email='" + email + '\'' +
                '}';
    }
}
