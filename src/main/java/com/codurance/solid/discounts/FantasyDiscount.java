package com.codurance.solid.discounts;

import java.util.List;
import com.codurance.solid.product.Book;

public class FantasyDiscount implements Discount {
    @Override
    public Double calculateFor(List<Book> books) {
        return null;
    }
}
