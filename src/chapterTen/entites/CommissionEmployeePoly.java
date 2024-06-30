package chapterTen.entites;

public class CommissionEmployeePoly extends EmployeePoly {
    private double grossSales, commissionRate;


    public CommissionEmployeePoly(String firstName, String lastName, String socialSecurityNumber, double grossSales, double commissionRate) {
        super(firstName, lastName, socialSecurityNumber);

        if(commissionRate <= 0.0 || commissionRate >=1.0) {
            throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
        }


        if(grossSales < 0.0) {
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        }

        this.grossSales = grossSales;
        this.commissionRate = commissionRate;
    }

    @Override
    public double earnings() {
        return getCommissionRate() * getGrossSales();
    }

    @Override
    public String toString() {
        return "CommissionEmployeePoly{" +
                super.toString() + " " +
                "grossSales=" + grossSales +
                ", commissionRate=" + commissionRate +
                '}';
    }

    public void setGrossSales(double grossSales) {
        if(grossSales < 0.0) {
            throw new IllegalArgumentException("Gross sales must be >= 0.0");
        }

        this.grossSales = grossSales;
    }

    public void setCommissionRate(double commissionRate) {
        if(commissionRate <= 0.0 || commissionRate >=1.0) {
            throw new IllegalArgumentException("Commission rate must be > 0.0 and < 1.0");
        }

        this.commissionRate = commissionRate;
    }

    public double getGrossSales() {
        return grossSales;
    }

    public double getCommissionRate() {
        return commissionRate;
    }
}
