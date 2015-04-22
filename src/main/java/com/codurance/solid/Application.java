package com.codurance.solid;

import com.codurance.solid.discounts.DiscountCalculator;
import com.codurance.solid.discounts.Discounts;
import com.codurance.solid.payment.Basket;
import com.codurance.solid.product.Book;
import com.codurance.solid.product.Books;

import static com.codurance.solid.product.BookType.COOKING;
import static com.codurance.solid.product.BookType.FANTASY;
import static com.codurance.solid.product.BookType.IT;
import static com.codurance.solid.product.BookType.TRAVEL;

public class Application {


	public static void main(String[] args) {
		Book fantasy_book_1 = new Book("The Hobbit", FANTASY, 20.00);
		Book fantasy_book_2 = new Book("Game of Thrones", FANTASY, 15.00);
		Book it_book_1      = new Book("Software Craftsmanship", IT, 18.00);
		Book it_book_2      = new Book("GOOS", IT, 25.00);
		Book it_book_3      = new Book("Clean Code", IT, 28.00);
		Book travel_book_1  = new Book("Notes from a Small Island", TRAVEL, 10.00);
		Book cooking_book_1 = new Book("Brazilian Flavours", COOKING, 10.00);

		Books books = new Books();
		Discounts discounts = new Discounts();
		DiscountCalculator discountCalculator = new DiscountCalculator(discounts);

		Basket basket = new Basket(books, discountCalculator);
		basket.add(fantasy_book_1);
		basket.add(fantasy_book_2);
		basket.add(it_book_1);
		basket.add(it_book_2);
		basket.add(it_book_3);
		basket.add(travel_book_1);
		basket.add(cooking_book_1);

		basket.getContents().stream().forEach(b -> println(b));
		println("Full price: " + basket.fullPrice());
		println("Price with discount: " + basket.priceWithDiscount());
	}

	private static void println(String text) {
		System.out.println(text);
	}

	private static void println(Book book) {
		println(book.toString());
	}

}
