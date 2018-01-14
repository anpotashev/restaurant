package ru.net.arh.testdata;

import ru.net.arh.model.Price;

import java.time.LocalDate;

import static ru.net.arh.testdata.TestDBData.*;

public class PriceTestData {

    public static final Price NEW_PRICE = new Price(RESTAURANT3, DISH1, LocalDate.now(), 10.00);
    public static final Price DUPLICATE_PRICE = new Price(RESTAURANT1, DISH3, LocalDate.now(), 10.00);
    public static final Price UPDATE_PRICE = new Price(START_PRICE_ID, RESTAURANT1, DISH2, LocalDate.now(), 22.33);

}
