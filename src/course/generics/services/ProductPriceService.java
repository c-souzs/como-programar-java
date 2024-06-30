package course.generics.services;

import course.generics.entites.Product;

import java.util.List;
import java.util.function.Predicate;

public class ProductPriceService {
    public static double filteredSum(List<Product> products, Predicate<Product> conditional) {
        double sum = 0.0;

        for (Product product: products) {
            if(conditional.test(product)) {
                sum += product.getPrice();
            }
        }

        return sum;
    }
}
