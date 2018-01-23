package ru.net.arh.service.impl.menu_item;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.test.context.support.WithMockUser;
import ru.net.arh.service.AbstractServiceTest;
import ru.net.arh.service.MenuItemService;
import ru.net.arh.to.menu.MenuItemTo;
import ru.net.arh.utils.MenuUtil;
import ru.net.arh.utils.validation.exception.ValidationException;

import java.time.LocalDate;
import java.util.List;

import static ru.net.arh.testdata.GenericTestClass.assertMatch;
import static ru.net.arh.testdata.MenuItemTestData.*;
import static ru.net.arh.testdata.TestDBData.*;

public class MenuItemServiceTest extends AbstractServiceTest {

    @Autowired
    private MenuItemService menuItemService;

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void create() throws Exception {
        MenuItemTo menuItemTo = MenuUtil.convertToMenuItemTo(NEW_MENU_ITEM);
        menuItemService.save(NEW_MENU_ITEM.getDate(), NEW_MENU_ITEM.getRestaurant().getId(), menuItemTo);
        NEW_MENU_ITEM.setId(NEXT_ID);
        List menuItems = menuItemService.getAllForRestorantInDay(LocalDate.now(), NEW_MENU_ITEM.getRestaurant().getId());
        assertMatch(menuItems
                , MenuUtil.convertToMenuItemTos(NEW_MENU_ITEM)
        );
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void createForOldDate() throws Exception {
        MenuItemTo menuItemTo = MenuUtil.convertToMenuItemTo(NEW_MENU_ITEM);
        thrown.expect(ValidationException.class);
        menuItemService.save(LocalDate.now().minusDays(1), NEW_MENU_ITEM.getRestaurant().getId(), menuItemTo);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void createWithDuplicateKey() throws Exception {
        thrown.expect(ValidationException.class);
        menuItemService.save(DUPLICATE_MENU_ITEM.getDate(), DUPLICATE_MENU_ITEM.getRestaurant().getId(), MenuUtil.convertToMenuItemTo(DUPLICATE_MENU_ITEM));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void update() throws Exception {
        MenuItemTo menuItemTo = MenuUtil.convertToMenuItemTo(UPDATE_MENU_ITEM);
        menuItemService.save(LocalDate.now(), UPDATE_MENU_ITEM.getRestaurant().getId(), menuItemTo);
        List menuItems = menuItemService.getAllForRestorantInDay(LocalDate.now(), RESTAURANT1.getId());
        assertMatch(menuItems, MenuUtil.convertToMenuItemTos(UPDATE_MENU_ITEM, MENU_ITEM_2, MENU_ITEM_3)
        );
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateWithWrongKey() throws Exception {
        MenuItemTo menuItemTo = MenuUtil.convertToMenuItemTo(UPDATE_MENU_ITEM);
        thrown.expect(ValidationException.class);
        menuItemService.save(LocalDate.now(), RESTAURANT4.getId(), menuItemTo);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateForOldDate() throws Exception {
        MenuItemTo menuItemTo = MenuUtil.convertToMenuItemTo(MENU_ITEM_7);
        thrown.expect(ValidationException.class);
        menuItemService.save(LocalDate.now().minusDays(1), MENU_ITEM_7.getRestaurant().getId(), menuItemTo);
    }


    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void delete() throws Exception {
        menuItemService.delete(LocalDate.now(), MENU_ITEM_1.getRestaurant().getId(), MENU_ITEM_1.getId());
        List menuItems = menuItemService.getAllForRestorantInDay(LocalDate.now(), MENU_ITEM_1.getRestaurant().getId());
        assertMatch(menuItems, MenuUtil.convertToMenuItemTos(MENU_ITEM_2, MENU_ITEM_3));
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteForOldDate() throws Exception {
        thrown.expect(ValidationException.class);
        menuItemService.delete(LocalDate.now().minusDays(1), RESTAURANT1.getId(), START_YESTERDAY_PRICE_ID);
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteWithWrongKey() throws Exception {
        thrown.expect(ValidationException.class);
        menuItemService.delete(LocalDate.now(), RESTAURANT1.getId(), -1);
    }

    @Test
    public void get() throws Exception {
        MenuItemTo menuItemTo = menuItemService.get(LocalDate.now(), MENU_ITEM_1.getRestaurant().getId(), MENU_ITEM_1.getId());
        assertMatch(MenuUtil.convertToMenuItemTo(MENU_ITEM_1), menuItemTo);
    }

    @Test
    public void getWithWrongRestaurant() throws Exception {
        thrown.expect(ValidationException.class);
        menuItemService.get(LocalDate.now(), RESTAURANT4.getId(), MENU_ITEM_1.getId());
    }

    @Test
    public void getWithWrongId() throws Exception {
        thrown.expect(ValidationException.class);
        menuItemService.get(LocalDate.now(), MENU_ITEM_1.getRestaurant().getId(), -1);
    }

    @Test
    public void getRestaurantMenuForDay() throws Exception {
        List menuItems = menuItemService.getAllForRestorantInDay(LocalDate.now(), RESTAURANT1.getId());
        assertMatch(menuItems, MenuUtil.convertToMenuItemTos(MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3));
    }

}