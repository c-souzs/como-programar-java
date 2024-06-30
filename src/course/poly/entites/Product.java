package course.poly.entites;

public class Product {
    private String name;
    private double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public double getPrice() {
        return price;
    }

    public String getName() {
        return name;
    }

    public String priceTag() {
        return String.format("%s R$ %.2f", name, price);
    }
}
