package ru.net.arh.web.rest;

import org.junit.Test;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import ru.net.arh.model.mapped.NamedBasedEntity;
import ru.net.arh.testdata.NamedBasedData;
import ru.net.arh.web.AbstractRestTestClass;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static ru.net.arh.web.JsonUtil.assertThat;
import static ru.net.arh.web.JsonUtil.writeValue;

public abstract class AbstractNamedRestControllerTest<T extends NamedBasedEntity> extends AbstractRestTestClass {

    protected abstract NamedBasedData<T> getTestData();

    @Test
    public void getAllTest() throws Exception {
        checkAll(getTestData().all())
        ;
    }

    @Test
    public void getById() throws Exception {
        mockMvc.perform(get(getUri() + "/" + getTestData().first().getId()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(getTestData().first()))
        ;
    }

    @Test
    public void getByFirstNamePart() throws Exception {
        mockMvc.perform(get(getUri() + "/filter").param("name", getTestData().firstPartOfName()))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_JSON))
                .andExpect(assertThat(getTestData().filtered()))
        ;
    }

    @Test
    public void getByWrongId() throws Exception {
        mockMvc.perform(get(getUri() + "/-1"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteTest() throws Exception {

        mockMvc.perform(delete(getUri() + "/" + getTestData().deleteId()))
                .andDo(print())
                .andExpect(status().isNoContent())
        ;

        checkAll(getTestData().allWithoutDeleted())
        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void deleteWithWrongId() throws Exception {

        mockMvc.perform(delete(getUri() + "/-1"))
                .andDo(print())
                .andExpect(status().isNotFound())
        ;

    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void create() throws Exception {
        mockMvc.perform(
                post(getUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValue(getTestData().create()))
        )
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(header().string("Location", "http://localhost" + getUri() + "/" + getTestData().created().getId()))
        ;

        checkAll(getTestData().allWithCreated())
        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void createWithNonEmptyId() throws Exception {
        mockMvc.perform(
                post(getUri())
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(writeValue(getTestData().changed()))
        )
                .andDo(print())
                .andExpect(status().isNotAcceptable());
        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void update() throws Exception {

        mockMvc.perform(put(getUri() + "/" + getTestData().changed().getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getTestData().changed()))
        )
                .andDo(print())
                .andExpect(status().isNoContent())
        ;


        checkAll(getTestData().allWithChanged())
        ;

    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateWithWrongId() throws Exception {

        mockMvc.perform(put(getUri() + "/" + getTestData().changed().getId() + 1)
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getTestData().changed()))
        )
                .andDo(print())
                .andExpect(status().isNotAcceptable())
        ;
    }

    @Test
    @WithMockUser(authorities = {"ROLE_ADMIN"})
    public void updateWithEmptyId() throws Exception {

        mockMvc.perform(put(getUri() + "/" + getTestData().changed().getId())
                .contentType(MediaType.APPLICATION_JSON)
                .content(writeValue(getTestData().create()))
        )
                .andDo(print())
                .andExpect(status().isNotAcceptable())
        ;
    }

}