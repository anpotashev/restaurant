package ru.net.arh.web.rest;

import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import ru.net.arh.to.menu.MenuItem;
import ru.net.arh.utils.MenuUtil;
import ru.net.arh.web.AbstractRestTest;

import java.time.LocalDate;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.net.arh.testdata.PriceTestData.NEW_PRICE;
import static ru.net.arh.testdata.PriceTestData.UPDATE_PRICE;
import static ru.net.arh.testdata.TestDBData.*;
import static ru.net.arh.utils.MenuUtil.convertToMenuItem;
import static ru.net.arh.web.JsonUtil.assertThat;
import static ru.net.arh.web.JsonUtil.writeValue;
import static ru.net.arh.web.rest.RootRest.ROOT_URL;

@Slf4j
public class PriceRestControllerTest extends AbstractRestTest {
    @Override
    protected String getUri() {
        return ROOT_URL + "/restaurants/" + RESTAURANT1.getId() + "/prices";
    }

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void getTodayRestaurantsMenu() throws Exception {
        mockMvc.perform(get(getUri()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(MenuUtil.convertToMenuItems(PRICE1, PRICE2, PRICE3)))

        ;
    }

    @Test
    public void getYesterdayRestaurantsMenu() throws Exception {
        mockMvc.perform(get(getUri() + "/" + LocalDate.now().minusDays(1).toString()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(MenuUtil.convertToMenuItems(PRICE7, PRICE8, PRICE9)))

        ;
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(get(getUri() + "/" + LocalDate.now().toString() + "/" + PRICE1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(convertToMenuItem(PRICE1)))

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void update() throws Exception {
        mockMvc.perform(put(getUri() + "/" + LocalDate.now().toString() + "/" + UPDATE_PRICE.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(convertToMenuItem(UPDATE_PRICE)))
        )
                .andDo(print())
                .andExpect(status().isNoContent())

        ;

        mockMvc.perform(get(getUri() + "/" + LocalDate.now().toString() + "/" + PRICE1.getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(convertToMenuItem(UPDATE_PRICE)))

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void create() throws Exception {
        MenuItem menuItem = convertToMenuItem(NEW_PRICE);

        mockMvc.perform(post(getUri() + "/" + LocalDate.now().toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(menuItem))
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string(
                        "Location", "http://localhost" + getUri() + "/" + LocalDate.now().toString() + "/" + NEXT_ID
                ))

        ;
        menuItem.setId(NEXT_ID);
        mockMvc.perform(get(getUri() + "/" + LocalDate.now().toString() + "/" + NEXT_ID))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(menuItem))

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteTest() throws Exception {

        mockMvc.perform(delete(getUri() + "/" + LocalDate.now().toString() + "/" + PRICE1.getId()))
                .andDo(print())
                .andExpect(status().isNoContent())

        ;

        mockMvc.perform(get(getUri() + "/" + LocalDate.now().toString() + "/" + PRICE1.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateInThePast() throws Exception {
        mockMvc.perform(put(getUri() + "/" + LocalDate.now().minusDays(1).toString() + "/" + UPDATE_PRICE.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(convertToMenuItem(UPDATE_PRICE)))
        )
                .andDo(print())
                .andExpect(status().isNotAcceptable())

        ;
    }

    @Test
    public void updateWithoutAdminRole() throws Exception {
        mockMvc.perform(put(getUri() + "/" + LocalDate.now().toString() + "/" + UPDATE_PRICE.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(convertToMenuItem(UPDATE_PRICE)))
        )
                .andDo(print())
                .andExpect(status().isUnauthorized())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateWithWrongDate() throws Exception {
        mockMvc.perform(put(getUri() + "/" + LocalDate.now().toString() + "/" + PRICE7.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(convertToMenuItem(UPDATE_PRICE)))
        )
                .andDo(print())
                .andExpect(status().isNotModified())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateWithWrongRestaurantId() throws Exception {
        mockMvc.perform(put(getUri() + "/" + LocalDate.now().toString() + "/" + PRICE6.getId())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(convertToMenuItem(UPDATE_PRICE)))
        )
                .andDo(print())
                .andExpect(status().isNotModified())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void createInThePast() throws Exception {
        MenuItem menuItem = convertToMenuItem(NEW_PRICE);

        mockMvc.perform(post(getUri() + "/" + LocalDate.now().minusDays(1).toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(menuItem))
        )
                .andDo(print())
                .andExpect(status().isNotAcceptable())

        ;
    }

    @Test
    public void createWithoutAdminRole() throws Exception {
        MenuItem menuItem = convertToMenuItem(NEW_PRICE);

        mockMvc.perform(post(getUri() + "/" + LocalDate.now().toString())
                .contentType(MediaType.APPLICATION_JSON_VALUE)
                .content(writeValue(menuItem))
        )
                .andDo(print())
                .andExpect(status().isUnauthorized())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteInThePast() throws Exception {
        mockMvc.perform(delete(getUri() + "/" + LocalDate.now().minusDays(1).toString() + "/" + PRICE7.getId()))
                .andDo(print())
                .andExpect(status().isNotAcceptable())

        ;
    }

    @Test
    public void deleteWithoutAdminRole() throws Exception {
        mockMvc.perform(delete(getUri() + "/" + LocalDate.now().toString() + "/" + PRICE1.getId()))
                .andDo(print())
                .andExpect(status().isUnauthorized())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteWithWrongDate() throws Exception {
        mockMvc.perform(delete(getUri() + "/" + LocalDate.now().toString() + "/" + PRICE7.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())

        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteWithRestaurantId() throws Exception {
        mockMvc.perform(delete(getUri() + "/" + LocalDate.now().toString() + "/" + PRICE4.getId()))
                .andDo(print())
                .andExpect(status().isNotFound())

        ;
    }


}
