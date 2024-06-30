package course.poly.entites;

public class ImportedProduct extends Product {
    private double customsFee;

    public ImportedProduct(String name, double price, double customsFee) {
        super(name, price);
        this.customsFee = customsFee;
    }

    public double totalPrice() {
        return super.getPrice() + customsFee;
    }

    @Override
    public String priceTag() {
        return String.format("%s R$ %.2f (Customs fee: R$ %.2f)", super.getName(), totalPrice(), customsFee);
    }
}
