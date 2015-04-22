package com.codurance.solid.discounts;

import java.util.List;
import com.codurance.solid.product.Book;
import org.junit.Test;

import static java.util.Arrays.asList;
import static com.codurance.builders.BookBuilder.aCookingBook;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class NoDiscountShould {

    @Test
    public void return_zero_if_there_are_no_books() {
        List<Book> zeroBooks = asList();

        NoDiscount noDiscount = new NoDiscount();

        assertThat(noDiscount.calculateDiscountedPriceFor(zeroBooks), is(0.0));

    }

    @Test
    public void return_no_discount_if_book_does_not_qualify_for_one() {
        List<Book> books = asList(aCookingBook().costing(10.0).build());

        NoDiscount noDiscount = new NoDiscount();

        assertThat(noDiscount.calculateDiscountedPriceFor(books), is(10.0));
    }

}
