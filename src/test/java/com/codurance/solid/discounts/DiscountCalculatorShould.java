package com.codurance.solid.discounts;

import java.util.List;
import com.codurance.solid.product.Book;
import org.junit.Test;

import static java.util.Arrays.asList;
import static com.codurance.builders.BookBuilder.aCookingBook;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.MatcherAssert.assertThat;

public class DiscountCalculatorShould {

    @Test
    public void
    return_zero_discount_when_empty() {
        Discounts discounts = new Discounts();
        DiscountCalculator discountCalculator = new DiscountCalculator(discounts);

        List<Book> zeroBooks = asList();

        assertThat(discountCalculator.calculateDiscountFor(zeroBooks), is(0.0));
    }

    @Test public void
    give_no_discount_when_book_is_not_eligible_for_a_discount() {
        Book aBookWithNoDiscount = aCookingBook().costing(10.00).build();
        Discounts discounts = new Discounts();
        DiscountCalculator discountCalculator = new DiscountCalculator(discounts);

        List<Book> books = asList(aBookWithNoDiscount);

        assertThat(discountCalculator.calculateDiscountFor(books), is(10.0));
    }
}
