package com.codurance.solid.discounts;

import java.util.List;
import com.codurance.solid.product.Book;
import org.junit.Test;

import static java.util.Arrays.asList;
import static com.codurance.builders.BookBuilder.aCookingBook;
import static com.codurance.builders.BookBuilder.aTravelBook;
import static com.codurance.builders.BookBuilder.anITBook;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class ItDiscountShould {

    @Test
    public void return_zero_discount_if_there_are_no_IT_books() {
        List<Book> books = asList(aCookingBook().costing(10.0).build(), aTravelBook().costing(20.0).build());

        ItDiscount itDiscount = new ItDiscount();

        assertThat(itDiscount.calculateFor(books), is(0.0));
    }

    @Test
    public void return_ten_percent_discount_if_there_are_one_or_two_books() {
        List<Book> books = asList(
                anITBook().costing(10.0).build(),
                anITBook().costing(20.0).build());

        ItDiscount itDiscount = new ItDiscount();

        assertThat(itDiscount.calculateFor(books), is(0.9));
    }

    @Test
    public void return_thirty_percent_discount_if_there_are_three_or_more_books() {
        List<Book> books = asList(
                anITBook().costing(10.0).build(),
                anITBook().costing(20.0).build(),
                anITBook().costing(15.0).build());

        ItDiscount itDiscount = new ItDiscount();

        assertThat(itDiscount.calculateFor(books), is(0.7));
    }
}
