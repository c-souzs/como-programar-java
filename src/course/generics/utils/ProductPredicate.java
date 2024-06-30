package course.generics.utils;

import course.generics.entites.Product;

import java.util.function.Predicate;

public class ProductPredicate implements Predicate<Product> {

    @Override
    public boolean test(Product product) {
        return product.getPrice() >= 100;
    }
}
