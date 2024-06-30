package course.generics.entites;

public class Employee {
    private String name;
    private String email;
    private double salary;

    public Employee(String name, String email, double salary) {
        this.name = name;
        this.email = email;
        this.salary = salary;
    }


    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("The employee ").append(getName());
        sb.append("and email").append(getEmail());
        sb.append(String.format("and salary R$ %.2f", getSalary())).append("\n");

        return sb.toString();
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public double getSalary() {
        return salary;
    }
}
