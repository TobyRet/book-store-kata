package com.codurance.solid.discounts;

import java.util.List;
import com.codurance.solid.product.Book;

import static com.codurance.solid.product.BookType.IT;

public class ItDiscount implements Discount {
    private double discountRate;

    @Override
    public Double calculateDiscountedPriceFor(List<Book> books) {

        calculateDiscountRateFor(books);

        return books.stream()
                .filter(book -> book.type().equals(IT))
                .mapToDouble(book -> book.price() * discountRate)
                .sum();
    }

    private Double calculateDiscountRateFor(List<Book> books) {
        discountRate = 1.0;

        int numberOfItBooks = (int) books.stream()
                .filter(book -> book.type().equals(IT))
                .count();

        if(numberOfItBooks > 2) {
            discountRate = 0.7;
        }

        if(numberOfItBooks <= 2 && numberOfItBooks > 0) {
            discountRate = 0.9;
        }
        return discountRate;
    }
}
