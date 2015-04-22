package com.codurance.solid.discounts;

import java.util.List;
import com.codurance.solid.product.Book;

public interface Discount {
    Double calculateFor(List<Book> books);
}
