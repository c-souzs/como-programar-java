package course.generics.entites;

import java.util.Objects;

public class Product implements Comparable<Product>{
    private static int globalId = 0;
    private int id;
    private String name;
    private Double price;

    public Product(String name, double price) {
        this.name = name;
        this.price = price;

        globalId++;
        this.id = globalId;
    }

    public static boolean staticProductPredicate(Product product) {
        return product.getPrice() >= 100;
    }

    public boolean noStaticProductPredicate() {
        return getPrice() >= 100;
    }

    public static void staticPriceUpdate(Product product) {
        product.setPrice(product.getPrice() * 1.1);
    }

    public static String staticUpperCaseName(Product product) {
        return product.getName().toUpperCase();
    }

    public String noStaticUpperCaseName() {
        return name.toUpperCase();
    }

    public void noStaticPriceUpdate() {
        price = price * 1.1;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", price=" + price +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Product product = (Product) o;
        return Objects.equals(name, product.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public int compareTo(Product other) {
        return price.compareTo(other.getPrice());
    }

    public String getName() {
        return name;
    }

    public Double getPrice() {
        return price;
    }

    public int getId() {
        return id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
}
