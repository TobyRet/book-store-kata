package com.codurance.solid.discounts;

import java.util.List;
import com.codurance.solid.product.Book;

public class DiscountCalculator {

    private Discounts discounts;

    public DiscountCalculator(Discounts discounts) {
        this.discounts = discounts;
    }

    public Double calculateDiscountFor(List<Book> books) {
        Double discountedPrice = 0.0;
        if(!books.isEmpty()) {
            discountedPrice = discounts.getList().stream()
                    .mapToDouble(discountOffer -> discountOffer.calculateDiscountedPriceFor(books))
                    .sum();
        }
        return discountedPrice;
    }
}
