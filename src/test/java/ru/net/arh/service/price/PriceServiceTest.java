package ru.net.arh.service.price;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import ru.net.arh.service.BaseTest;
import ru.net.arh.service.PriceService;

public class PriceServiceTest extends BaseTest {

    @Autowired
    private PriceService priceService;

//    @Test
//    public void create() throws Exception {
//        priceService.create(NEW_PRICE.getRestaurant().getId(), NEW_PRICE.getDish().getId(), NEW_PRICE.getDate(), NEW_PRICE.getPrice().doubleValue());
//    }
//
//    @Test
//    public void createWithDuplicateKey() throws Exception {
//        thrown.expect(DuplicateValueForUniqueIndexException.class);
//        priceService.create(DUPLICATE_PRICE.getRestaurant().getId(), DUPLICATE_PRICE.getDish().getId(), DUPLICATE_PRICE.getDate(), DUPLICATE_PRICE.getPrice().doubleValue());
//    }

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
//        thrown.expect(CreateWithFoundKeyException.class);
//        priceService.update(NEW_PRICE.getRestaurant().getId(), NEW_PRICE.getDish().getId(), NEW_PRICE.getDate(), NEW_PRICE.getPrice().doubleValue());
    }

    @Test
    public void getRestaurantMenuForDay() throws Exception {
    }

}