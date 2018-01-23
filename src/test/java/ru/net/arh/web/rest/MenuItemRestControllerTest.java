package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import ru.net.arh.to.menu.MenuItemTo;
import ru.net.arh.utils.MenuUtil;
import ru.net.arh.web.AbstractRestTest;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.net.arh.testdata.MenuItemTestData.NEW_MENU_ITEM;
import static ru.net.arh.testdata.MenuItemTestData.UPDATE_MENU_ITEM;
import static ru.net.arh.testdata.TestDBData.*;
import static ru.net.arh.utils.MenuUtil.convertToMenuItemTo;
import static ru.net.arh.web.JsonUtil.assertThat;
import static ru.net.arh.web.JsonUtil.writeValue;
import static ru.net.arh.web.rest.RootRest.ROOT_URL;

@Slf4j
public class MenuItemRestControllerTest extends AbstractRestTest {
    @Override
    protected String getUri() {
        return ROOT_URL + "/restaurants/" + RESTAURANT1.getId() + "/menu_items";
    }

    @Test
    public void getTodayRestaurantsMenu() throws Exception {
        mockMvc.perform(get(getUri()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(MenuUtil.convertToMenuItemTos(MENU_ITEM_1, MENU_ITEM_2, MENU_ITEM_3)))

        ;
    }

    @Test
    public void getYesterdayRestaurantsMenu() throws Exception {
        mockMvc.perform(get(getUri() + "/" + LocalDate.now().minusDays(1).toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(MenuUtil.convertToMenuItemTos(MENU_ITEM_7, MENU_ITEM_8, MENU_ITEM_9)))

        ;
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(get(getUri() + "/" + LocalDate.now().toString() + "/" + MENU_ITEM_1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(convertToMenuItemTo(MENU_ITEM_1)))

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void update() throws Exception {
        mockMvc.perform(put(getUri() + "/" + LocalDate.now().toString() + "/" + UPDATE_MENU_ITEM.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(convertToMenuItemTo(UPDATE_MENU_ITEM)))
        )
                .andDo(print())
                .andExpect(status().isNoContent())

        ;

        mockMvc.perform(get(getUri() + "/" + LocalDate.now().toString() + "/" + MENU_ITEM_1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(convertToMenuItemTo(UPDATE_MENU_ITEM)))

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void create() throws Exception {
        MenuItemTo menuItemTo = convertToMenuItemTo(NEW_MENU_ITEM);

        mockMvc.perform(post(getUri() + "/" + LocalDate.now().toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(menuItemTo))
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string(
                        "Location", "http://localhost" + getUri() + "/" + LocalDate.now().toString() + "/" + NEXT_ID
                ))

        ;
        menuItemTo.setId(NEXT_ID);
        mockMvc.perform(get(getUri() + "/" + LocalDate.now().toString() + "/" + NEXT_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(menuItemTo))

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteTest() throws Exception {

        mockMvc.perform(delete(getUri() + "/" + LocalDate.now().toString() + "/" + MENU_ITEM_1.getId()))
                .andDo(print())
                .andExpect(status().isNoContent())

        ;

        mockMvc.perform(get(getUri() + "/" + LocalDate.now().toString() + "/" + MENU_ITEM_1.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateInThePast() throws Exception {
        mockMvc.perform(put(getUri() + "/" + LocalDate.now().minusDays(1).toString() + "/" + UPDATE_MENU_ITEM.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(convertToMenuItemTo(UPDATE_MENU_ITEM)))
        )
                .andDo(print())
                .andExpect(status().isNotAcceptable())

        ;
    }

    @Test
    public void updateWithoutAdminRole() throws Exception {
        mockMvc.perform(put(getUri() + "/" + LocalDate.now().toString() + "/" + UPDATE_MENU_ITEM.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(convertToMenuItemTo(UPDATE_MENU_ITEM)))
        )
                .andDo(print())
                .andExpect(status().isUnauthorized())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateWithWrongDate() throws Exception {
        mockMvc.perform(put(getUri() + "/" + LocalDate.now().toString() + "/" + MENU_ITEM_7.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(convertToMenuItemTo(UPDATE_MENU_ITEM)))
        )
                .andDo(print())
                .andExpect(status().isNotModified())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateWithWrongRestaurantId() throws Exception {
        mockMvc.perform(put(getUri() + "/" + LocalDate.now().toString() + "/" + MENU_ITEM_6.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(convertToMenuItemTo(UPDATE_MENU_ITEM)))
        )
                .andDo(print())
                .andExpect(status().isNotModified())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void createInThePast() throws Exception {
        MenuItemTo menuItemTo = convertToMenuItemTo(NEW_MENU_ITEM);

        mockMvc.perform(post(getUri() + "/" + LocalDate.now().minusDays(1).toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(menuItemTo))
        )
                .andDo(print())
                .andExpect(status().isNotAcceptable())

        ;
    }

    @Test
    public void createWithoutAdminRole() throws Exception {
        MenuItemTo menuItemTo = convertToMenuItemTo(NEW_MENU_ITEM);

        mockMvc.perform(post(getUri() + "/" + LocalDate.now().toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(menuItemTo))
        )
                .andDo(print())
                .andExpect(status().isUnauthorized())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteInThePast() throws Exception {
        mockMvc.perform(delete(getUri() + "/" + LocalDate.now().minusDays(1).toString() + "/" + MENU_ITEM_7.getId()))
                .andDo(print())
                .andExpect(status().isNotAcceptable())

        ;
    }

    @Test
    public void deleteWithoutAdminRole() throws Exception {
        mockMvc.perform(delete(getUri() + "/" + LocalDate.now().toString() + "/" + MENU_ITEM_1.getId()))
                .andDo(print())
                .andExpect(status().isUnauthorized())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteWithWrongDate() throws Exception {
        mockMvc.perform(delete(getUri() + "/" + LocalDate.now().toString() + "/" + MENU_ITEM_7.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteWithRestaurantId() throws Exception {
        mockMvc.perform(delete(getUri() + "/" + LocalDate.now().toString() + "/" + MENU_ITEM_4.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())

        ;
    }


}
