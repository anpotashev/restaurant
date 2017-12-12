package ru.net.arh.testdata;

import ru.net.arh.model.Price;

import java.math.BigDecimal;
import java.time.LocalDate;

import static ru.net.arh.testdata.TestDBData.*;

public class PriceTestData {

    public static final Price NEW_PRICE = new Price(RESTAURANT3, DISH1, LocalDate.now(), new BigDecimal(10.00));
    public static final Price DUPLICATE_PRICE = new Price(RESTAURANT1, DISH3, LocalDate.now(), new BigDecimal(10.00));
}
