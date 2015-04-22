package com.codurance.solid.discounts;

import java.util.List;
import com.codurance.solid.product.Book;

import static com.codurance.solid.product.BookType.TRAVEL;

public class TravelDiscount implements Discount {
    private Double discountRate;

    @Override
    public Double calculateDiscountedPriceFor(List<Book> books) {
        calculateDiscountRateFor(books);

        return books.stream()
                .filter(book -> book.type().equals(TRAVEL))
                .mapToDouble(book -> book.price() * discountRate)
                .sum();
    }

    private void calculateDiscountRateFor(List<Book> books) {
        discountRate = 1.0;

        int numberOfItBooks = (int) books.stream()
                .filter(book -> book.type().equals(TRAVEL))
                .count();

        if(numberOfItBooks > 3) {
            discountRate = 0.6;
        }
    }
}
