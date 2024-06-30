package course.poly.entites;

public class OutsourceEmployee extends Employee {
    private double additionalCharge;

    public OutsourceEmployee(String name, int hours, double valuePerHours, double additionalCharge) {
        super(name, hours, valuePerHours);
        this.additionalCharge = additionalCharge;
    }

    @Override
    public double payment() {
        return super.payment() + (additionalCharge * 1.1);
    }
}
