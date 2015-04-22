package com.codurance.solid.payment;

import java.util.List;

import com.codurance.solid.discounts.DiscountCalculator;
import com.codurance.solid.product.Book;
import com.codurance.solid.product.Books;

import static java.lang.Math.round;
import static java.util.Collections.unmodifiableList;

public class Basket {
	private Books books;
	private DiscountCalculator discountCalculator;

	public Basket(Books books, DiscountCalculator discountCalculator) {
		this.books = books;
		this.discountCalculator = discountCalculator;
	}

	public void add(Book item) {
		books.add(item);
	}

	public List<Book> getContents() {
		return unmodifiableList(books.get());
	}

	public double priceWithDiscount() {
		return toDecimal((
				fullPrice() * discountCalculator.calculateDiscountFor(getContents())));
	}

	public double fullPrice() {
		return getContents().stream()
				.mapToDouble(Book::price)
				.sum();
	}

	private double toDecimal(double number) {
		return round(number * 100) / 100.0;
	}

}
