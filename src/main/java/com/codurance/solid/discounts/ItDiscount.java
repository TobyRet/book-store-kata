package com.codurance.solid.discounts;

import java.util.List;
import com.codurance.solid.product.Book;

import static com.codurance.solid.product.BookType.IT;

public class ItDiscount implements Discount {
    @Override
    public Double calculateFor(List<Book> books) {
        Double discount = 0.0;

        int numberOfItBooks = (int) books.stream()
                .filter(book -> book.type().equals(IT))
                .count();

        if(numberOfItBooks > 2) {
            discount = 0.7;
        }

        if(numberOfItBooks <= 2 && numberOfItBooks > 0) {
            discount = 0.9;
        }
        return discount;
    }
}
