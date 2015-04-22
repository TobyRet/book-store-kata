package com.codurance.builders;

import com.codurance.solid.Basket;
import com.codurance.solid.Book;
import com.codurance.solid.Books;
import com.codurance.solid.DiscountCalculator;

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
		DiscountCalculator discountCalculator = new DiscountCalculator();
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
