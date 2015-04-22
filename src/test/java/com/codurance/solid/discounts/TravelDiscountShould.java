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

public class TravelDiscountShould {

    @Test
    public void return_zero_if_there_are_no_Travel_books() {
        List<Book> books = asList(aCookingBook().costing(10.0).build(), anITBook().costing(20.0).build());

        TravelDiscount travelDiscount = new TravelDiscount();

        assertThat(travelDiscount.calculateDiscountedPriceFor(books), is(0.0));
    }

    @Test
    public void return_forty_percent_discount_if_there_are_four_or_more_books() {
        List<Book> books = asList(
                aTravelBook().costing(10.0).build(),
                aTravelBook().costing(10.0).build(),
                aTravelBook().costing(10.0).build(),
                aTravelBook().costing(10.0).build());

        TravelDiscount travelDiscount = new TravelDiscount();

        assertThat(travelDiscount.calculateDiscountedPriceFor(books), is(24.0));
    }

}
