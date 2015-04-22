package com.codurance.solid.discounts;

import java.util.Arrays;
import java.util.List;

public class Discounts {
    private List<Discount> list;

    public Discounts() {
        list = Arrays.asList(new NoDiscount(), new TravelDiscount(), new ItDiscount());
    }

    public List<Discount> getList() {
        return list;
    }
}
