package ru.net.arh.service.impl.price;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import ru.net.arh.service.AbstractServiceTest;
import ru.net.arh.service.PriceService;
import ru.net.arh.to.menu.MenuItem;
import ru.net.arh.utils.MenuUtil;
import ru.net.arh.utils.validation.exception.ValidationException;

import java.time.LocalDate;
import java.util.List;

import static ru.net.arh.testdata.GenericTestClass.assertMatch;
import static ru.net.arh.testdata.PriceTestData.*;
import static ru.net.arh.testdata.TestDBData.*;
import static ru.net.arh.utils.MenuUtil.convertToMenuItems;

public class PriceServiceTest extends AbstractServiceTest {

    @Autowired
    private PriceService priceService;

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void create() throws Exception {
        MenuItem menuItem = MenuUtil.convertToMenuItem(NEW_PRICE);
        priceService.save(NEW_PRICE.getDate(), NEW_PRICE.getRestaurant().getId(), menuItem);
        NEW_PRICE.setId(NEXT_ID);
        List menuItems = priceService.getAllForRestorantInDay(LocalDate.now(), NEW_PRICE.getRestaurant().getId());
        assertMatch(menuItems
                , convertToMenuItems(NEW_PRICE)
        );
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void createForOldDate() throws Exception {
        MenuItem menuItem = MenuUtil.convertToMenuItem(NEW_PRICE);
        thrown.expect(ValidationException.class);
        priceService.save(LocalDate.now().minusDays(1), NEW_PRICE.getRestaurant().getId(), menuItem);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void createWithDuplicateKey() throws Exception {
        thrown.expect(ValidationException.class);
        priceService.save(DUPLICATE_PRICE.getDate(), DUPLICATE_PRICE.getRestaurant().getId(), MenuUtil.convertToMenuItem(DUPLICATE_PRICE));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void update() throws Exception {
        MenuItem menuItem = MenuUtil.convertToMenuItem(UPDATE_PRICE);
        priceService.save(LocalDate.now(), UPDATE_PRICE.getRestaurant().getId(), menuItem);
        List menuItems = priceService.getAllForRestorantInDay(LocalDate.now(), RESTAURANT1.getId());
        assertMatch(menuItems, convertToMenuItems(UPDATE_PRICE, PRICE2, PRICE3)
        );
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateWithWrongKey() throws Exception {
        MenuItem menuItem = MenuUtil.convertToMenuItem(UPDATE_PRICE);
        thrown.expect(ValidationException.class);
        priceService.save(LocalDate.now(), RESTAURANT4.getId(), menuItem);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateForOldDate() throws Exception {
        MenuItem menuItem = MenuUtil.convertToMenuItem(PRICE7);
        thrown.expect(ValidationException.class);
        priceService.save(LocalDate.now().minusDays(1), PRICE7.getRestaurant().getId(), menuItem);
    }


    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void delete() throws Exception {
        priceService.delete(LocalDate.now(), PRICE1.getRestaurant().getId(), PRICE1.getId());
        List menuItems = priceService.getAllForRestorantInDay(LocalDate.now(), PRICE1.getRestaurant().getId());
        assertMatch(menuItems, convertToMenuItems(PRICE2, PRICE3));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteForOldDate() throws Exception {
        thrown.expect(ValidationException.class);
        priceService.delete(LocalDate.now().minusDays(1), RESTAURANT1.getId(), START_YESTERDAY_PRICE_ID);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteWithWrongKey() throws Exception {
        thrown.expect(ValidationException.class);
        priceService.delete(LocalDate.now(), RESTAURANT1.getId(), -1);
    }

    @Test
    public void get() throws Exception {
        MenuItem menuItem = priceService.get(LocalDate.now(), PRICE1.getRestaurant().getId(), PRICE1.getId());
        assertMatch(MenuUtil.convertToMenuItem(PRICE1), menuItem);
    }

    @Test
    public void getWithWrongRestaurant() throws Exception {
        thrown.expect(ValidationException.class);
        priceService.get(LocalDate.now(), RESTAURANT4.getId(), PRICE1.getId());
    }

    @Test
    public void getWithWrongId() throws Exception {
        thrown.expect(ValidationException.class);
        priceService.get(LocalDate.now(), PRICE1.getRestaurant().getId(), -1);
    }

    @Test
    public void getRestaurantMenuForDay() throws Exception {
        List menuItems = priceService.getAllForRestorantInDay(LocalDate.now(), RESTAURANT1.getId());
        assertMatch(menuItems, convertToMenuItems(PRICE1, PRICE2, PRICE3));
    }

}