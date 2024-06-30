package chapterTen.entites;

public class SalariedEmployeePoly extends EmployeePoly {
    private double weeklySalary;

    public SalariedEmployeePoly(String firstName, String lastName, String socialSecurityNumber, double weeklySalary) {
        super(firstName, lastName, socialSecurityNumber);

        if(weeklySalary < 0.0) {
            throw new IllegalArgumentException("O salario semanal deve ser maior que 0.0");
        }

        this.weeklySalary = weeklySalary;
    }

    @Override
    public double earnings() {
        return getWeeklySalary();
    }

    @Override
    public String toString() {
        return "SalariedEmployeePoly{" +
                super.toString() + " " +
                "weeklySalary=" + weeklySalary +
                '}';
    }

    public double getWeeklySalary() {
        return weeklySalary;
    }

    public void setWeeklySalary(double weeklySalary) {
        if(weeklySalary < 0.0) {
            throw new IllegalArgumentException("O salario semanal deve ser maior que 0.0");
        }

        this.weeklySalary = weeklySalary;
    }
}
