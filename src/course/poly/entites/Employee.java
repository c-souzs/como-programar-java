package course.poly.entites;

public class Employee {
    private String name;
    private int hours;
    private double valuePerHours;

    public Employee(String name, int hours, double valuePerHours) {
        this.name = name;
        this.hours = hours;
        this.valuePerHours = valuePerHours;
    }

    public double payment() {
        return  hours * valuePerHours;
    }

    @Override
    public String toString() {
        return String.format("%s - R$ %.2f", name, payment());
    }
}
