package com.codurance.solid;

import java.util.ArrayList;
import java.util.List;

import static java.util.Collections.unmodifiableList;

public class Books {
    private List<Book> books = new ArrayList<>();

    public void add(Book item) {
        books.add(item);
    }

    public List<Book> get() {
        return unmodifiableList(books);
    }
}
