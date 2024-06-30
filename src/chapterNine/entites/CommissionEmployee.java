package chapterNine.entites;

public class CommissionEmployee {
    private String firstName;
    private String lastName;
    private String socialSecurityNumber;
    private double grossSales;
    private double commissionRate;

    public CommissionEmployee(String firstName, String lastName, String socialSecurityNumber, double grossSales, double commissionRate) {

        if(commissionRate <= 0.0 || commissionRate >=1.0) {
            throw new IllegalArgumentException("A porcentagem deve ser um valor maior que 0.0 e menor que 1.0");
        }

        if(grossSales < 0.0) {
            throw new IllegalArgumentException("Vendas semanais devem ser maior ou igual a 0.0");
        }

        this.firstName = firstName;
        this.lastName = lastName;
        this.socialSecurityNumber = socialSecurityNumber;
        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }

    public double earnings() {
        return grossSales * commissionRate;
    }

    public void setGrossSales(double grossSales) {
        if(grossSales < 0.0) {
            throw new IllegalArgumentException("Vendas semanais devem ser maior ou igual a 0.0");
        }

        this.grossSales = grossSales;
    }

    public void setCommissionRate(double commissionRate) {
        if(commissionRate <= 0.0 || commissionRate >=1.0) {
            throw new IllegalArgumentException("A porcentagem deve ser um valor maior que 0.0 e menor que 1.0");
        }

        this.commissionRate = commissionRate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public String getSocialSecurityNumber() {
        return socialSecurityNumber;
    }

    public double getGrossSales() {
        return grossSales;
    }

    public double getCommissionRate() {
        return commissionRate;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("Commission employee: ").append(firstName).append(" ").append(lastName).append(" ").append("\n");
        sb.append("Social security number: ").append(socialSecurityNumber).append("\n");
        sb.append("Gross sales: ").append(grossSales).append("\n");
        sb.append("Commission rate: ").append(commissionRate).append("\n");
        sb.append("Earnings: ").append(earnings()).append("\n");

        return sb.toString();
    }
}
