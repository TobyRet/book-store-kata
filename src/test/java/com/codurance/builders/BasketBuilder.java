package com.codurance.builders;

import com.codurance.solid.discounts.DiscountCalculator;
import com.codurance.solid.discounts.Discounts;
import com.codurance.solid.payment.Basket;
import com.codurance.solid.product.Book;
import com.codurance.solid.product.Books;

public class BasketBuilder {

	private Book[] booksToBeAdded = new Book[] {};

	public static BasketBuilder aBasket() {
		return new BasketBuilder();
	}

	public BasketBuilder with(Book... booksToBeAdded) {
		this.booksToBeAdded = booksToBeAdded;
		return this;
	}

	public Basket build() {
		Books books = new Books();
		Discounts discounts = new Discounts();
		DiscountCalculator discountCalculator = new DiscountCalculator(discounts);
		Basket basket = new Basket(books, discountCalculator);
		addBooksTo(basket);
		return basket;
	}

	private void addBooksTo(Basket basket) {
		for(Book book : booksToBeAdded) {
			basket.add(book);
		}
	}
}
