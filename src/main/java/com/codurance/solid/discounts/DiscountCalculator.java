package com.codurance.solid.discounts;

import java.util.List;
import com.codurance.solid.product.Book;

public class DiscountCalculator {

    private Discounts discounts;

    public DiscountCalculator(Discounts discounts) {
        this.discounts = discounts;
    }

    //		if (totalNumberOfBooksOfType(IT) > 2) {
//			it_books_discount = 0.7; // 30% discount when buying more than 2 IT getContents
//		} else if (totalNumberOfBooksOfType(IT) > 0) {
//			it_books_discount = 0.9; // 10% discount when buying up to 2 IT getContents
//		}
//		if (totalNumberOfBooksOfType(TRAVEL) > 3) {
//			travel_books_discount = 0.6; // 40% discount when buying more than 3 travel getContents
//		}
//
//		if (travel_books_discount > 0) {
//			total_price_for_travel_books *= travel_books_discount;
//		}

    public Double calculateDiscountFor(List<Book> books) {
        Double discount = 0.0;
        if(!books.isEmpty()) {
            discount = discounts.getList().stream()
                    .mapToDouble(discountOffer -> discountOffer.calculateFor(books))
                    .sum();
        }
        return discount;
    }
}
