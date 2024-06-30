package course.entites;

public class Triangle {
    private double a, b, c;

    public Triangle(double a, double b, double c) {
        this.a = a;
        this.b = b;
        this.c = c;
    }

    public double getArea() {
        double pValue = (a + b + c) / 2.0;
        return Math.sqrt(pValue * (pValue - a) * (pValue - b) * (pValue - c));
    }

    public boolean isLargeArea(Triangle triangleCompare) {
        return  getArea() > triangleCompare.getArea();
    }
}
