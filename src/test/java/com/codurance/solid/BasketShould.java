package com.codurance.solid;

import com.codurance.solid.discounts.DiscountCalculator;
import com.codurance.solid.payment.Basket;
import com.codurance.solid.product.Book;
import com.codurance.solid.product.Books;
import org.junit.Test;

import static java.util.Arrays.asList;
import static com.codurance.builders.BookBuilder.aCookingBook;
import static com.codurance.builders.BookBuilder.aTravelBook;
import static com.codurance.builders.BookBuilder.anITBook;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;

public class BasketShould {

	private Books books = mock(Books.class);
	private DiscountCalculator discountCalculator = mock(DiscountCalculator.class);
	private Basket basket = new Basket(books, discountCalculator);


	@Test public void
	return_total_price_of_zero_when_empty() {
		assertThat(emptyBasket().fullPrice(), is(0.0));
	}

	@Test public void
	return_zero_discount_when_empty() {
		Basket emptyBasket = emptyBasket();

		given(discountCalculator.calculateDiscountFor(emptyBasket.getContents())).willReturn(1.0);

		assertThat(emptyBasket.priceWithDiscount(), is(0.0));
	}

//	@Ignore
//	@Test(expected = UnsupportedOperationException.class) public void
//	return_an_unmodifiable_list_of_books() {
//		given(books.get()).willReturn(unmodifiableList(new ArrayList<>()));
//
//		aBasket().build().getContents().add(aCookingBook().build());
//	}

	@Test public void
	give_no_discount_when_book_is_not_eligible_for_a_discount() {
		Book aBookWithNoDiscount = aCookingBook().costing(10.00).build();

		given(books.get()).willReturn(asList(aBookWithNoDiscount));
		given(discountCalculator.calculateDiscountFor(basket.getContents())).willReturn(1.0);

	    assertThat(basket.priceWithDiscount(), is(10.0));
	}

	@Test public void
	calculate_the_total_price_with_no_discount_when_containing_multiple_books() {
		given(books.get()).willReturn(asList(
				aCookingBook().costing(10.0).build(),
				anITBook().costing(30.0).build(),
				anITBook().costing(20.0).build(),
				aTravelBook().costing(20.0).build()));

		assertThat(basket.fullPrice(), is(80.0));
	}


	@Test public void
	give_30_percent_discount_for_IT_books_when_containing_more_than_two_of_them() {
		given(books.get()).willReturn(asList(
				anITBook().costing(30.0).build(),
				anITBook().costing(10.0).build(),
				anITBook().costing(20.0).build()));

		given(discountCalculator.calculateDiscountFor(books.get())).willReturn(0.7);

	    assertThat(basket.priceWithDiscount(), is(42.0));
	}

	@Test public void
	give_10_percent_discount_for_IT_books_when_containing_one_of_them() {
		given(books.get()).willReturn(asList(anITBook().costing(10.0).build()));

		given(discountCalculator.calculateDiscountFor(books.get())).willReturn(0.9);

		assertThat(basket.priceWithDiscount(), is(9.0));
	}

	@Test public void
	give_10_percent_discount_for_IT_books_when_containing_two_of_them() {
		given(books.get()).willReturn(asList(
				anITBook().costing(30.0).build(),
				anITBook().costing(10.0).build()));

		given(discountCalculator.calculateDiscountFor(books.get())).willReturn(0.9);

	    assertThat(basket.priceWithDiscount(), is(36.0));
	}

	@Test public void
	not_give_discounts_for_Travel_books_when_containing_less_than_four_of_them() {
		given(books.get()).willReturn(asList(
									aTravelBook().costing(30.0).build(),
									aTravelBook().costing(10.0).build(),
									aTravelBook().costing(20.0).build()));

		given(discountCalculator.calculateDiscountFor(books.get())).willReturn(1.0);

	    assertThat(basket.priceWithDiscount(), is(60.0));
	}

	@Test public void
	give_40_percent_discount_for_Travel_books_when_containing_more_than_three_of_them() {
		given(books.get()).willReturn(asList(
									aTravelBook().costing(30.0).build(),
									aTravelBook().costing(10.0).build(),
									aTravelBook().costing(20.0).build(),
									aTravelBook().costing(10.0).build()));

		given(discountCalculator.calculateDiscountFor(books.get())).willReturn(0.6);

	    assertThat(basket.priceWithDiscount(), is(42.0));
	}

	@Test public void
	combine_10_percent_discount_for_1_IT_book_and_40_percent_discount_for_4_Travel_books() {
		given(books.get()).willReturn(asList(
									anITBook().costing(10.0).build(),
									aTravelBook().costing(30.0).build(),
									aTravelBook().costing(10.0).build(),
									aTravelBook().costing(20.0).build(),
									aTravelBook().costing(10.0).build()));

		given(discountCalculator.calculateDiscountFor(books.get())).willReturn(0.6375);

	    assertThat(basket.priceWithDiscount(), is(51.0));

	}

	private Basket emptyBasket() {
		Books books = new Books();
		return new Basket(books, discountCalculator);
	}
}
