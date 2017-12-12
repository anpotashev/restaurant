package ru.net.arh.service.price;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.net.arh.service.BaseTest;
import ru.net.arh.service.PriceService;
import ru.net.arh.utils.exception.CreateWithFoundKeyException;

import static ru.net.arh.testdata.PriceTestData.DUPLICATE_PRICE;
import static ru.net.arh.testdata.PriceTestData.NEW_PRICE;

public class PriceServiceTest extends BaseTest {

    @Autowired
    private PriceService priceService;

    @Test
    public void create() throws Exception {
        priceService.create(NEW_PRICE.getKey().getRestaurant().getId(), NEW_PRICE.getKey().getDish().getId(), NEW_PRICE.getKey().getDate(), NEW_PRICE.getPrice().doubleValue());
    }

    @Test
    public void createWithDuplicateKey() throws Exception {
        thrown.expect(CreateWithFoundKeyException.class);
        priceService.create(DUPLICATE_PRICE.getKey().getRestaurant().getId(), DUPLICATE_PRICE.getKey().getDish().getId(), DUPLICATE_PRICE.getKey().getDate(), DUPLICATE_PRICE.getPrice().doubleValue());
    }

    @Test
    public void delete() throws Exception {
    }

    @Test
    public void deleteWithWrongKey() throws Exception {
    }

    @Test
    public void update() throws Exception {
    }

    @Test
    public void updateWithWrongKey() throws Exception {
        thrown.expect(CreateWithFoundKeyException.class);
        priceService.update(NEW_PRICE.getKey().getRestaurant().getId(), NEW_PRICE.getKey().getDish().getId(), NEW_PRICE.getKey().getDate(), NEW_PRICE.getPrice().doubleValue());
    }

    @Test
    public void getRestaurantMenuForDay() throws Exception {
    }

}