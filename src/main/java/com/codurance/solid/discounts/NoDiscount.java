package com.codurance.solid.discounts;

import java.util.List;
import com.codurance.solid.product.Book;

import static com.codurance.solid.product.BookType.IT;
import static com.codurance.solid.product.BookType.TRAVEL;

public class NoDiscount implements Discount {
    @Override
    public Double calculateDiscountedPriceFor(List<Book> books) {
        return books.stream()
                .filter(book -> !book.type().equals(TRAVEL) || !book.type().equals(IT))
                .mapToDouble(book -> book.price())
                .sum();
    }
}
