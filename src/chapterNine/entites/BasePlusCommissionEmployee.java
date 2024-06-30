package chapterNine.entites;

public class BasePlusCommissionEmployee extends  CommissionEmployee{
    private double baseSalary;

    public BasePlusCommissionEmployee(String firstName, String lastName, String socialSecurityNumber, double grossSales, double commissionRate, double baseSalary) {
        super(firstName, lastName, socialSecurityNumber, grossSales, commissionRate);

        if(baseSalary <= 0.0) {
            throw new IllegalArgumentException("O salario base deve ser maior que 0.0");
        }

        this.baseSalary = baseSalary;
    }

    @Override
    public double earnings() {
        // return baseSalary + (commissionRate * grossSales); Isso seria valido, se por exemplo, commissionRate e grossSales tivesse o modificador de acesso protected
        return baseSalary + super.earnings();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(super.toString());
        sb.append("Base salary: ").append(baseSalary).append("\n");

        return sb.toString();
    }
}
