package course.entites;

public class Product {
    private static int amountProducts = 0;
    private int id;
    private String name;
    private double price;
    private int amountStock;

    public Product(String name, double price, int amountStock) {
        this.name = name;
        this.price = price;
        this.amountStock = amountStock;

        id = amountProducts;
        amountProducts++;
    }

    public Product(String name, double price) {
        this.name = name;
        this.price = price;
    }

    public Product() {}

    public double totalValueInStock() {
        return amountStock * price;
    }

    public void removeStock(int amount) {
        if(amount > 0) amountStock-=amount;
    }

    public void addStock(int amount) {
        if(amount > 0) amountStock+=amount;
    }

    public String toString() {
        return  String.format("-Id: %d %n -Name: %s %n -Price: %.2f -Amount in stock: %d units, total (R$) %.2f",
                id, name, price, amountStock, totalValueInStock());
    }
}
