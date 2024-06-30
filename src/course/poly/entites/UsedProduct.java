package course.poly.entites;

import java.util.Date;

public class UsedProduct extends Product {
    private String manufactureData;

    public UsedProduct(String name, double price, String manufactureData) {
        super(name, price);
        this.manufactureData = manufactureData;
    }

    @Override
    public String priceTag() {
        return String.format("%s (used) R$ %.2f (Manufacture date: %s)",
                super.getName(),
                super.getPrice(),
                manufactureData
        );
    }
}
