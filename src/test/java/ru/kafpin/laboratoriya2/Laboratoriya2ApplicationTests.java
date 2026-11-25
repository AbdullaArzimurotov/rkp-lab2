package ru.kafpin.laboratoriya2;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
class Laboratoriya2ApplicationTests {
    @Autowired
    private MockMvc mvc;
    @Autowired
    private SchoolboyRepository schoolboys;

    @Test
    void createsEditsAndDeletesSchoolboy() throws Exception {
        mvc.perform(get("/schoolboys/main")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Соколов")));
        mvc.perform(form("Денис")).andExpect(redirectedUrl("/schoolboys/main"));
        Schoolboy created = java.util.stream.StreamSupport.stream(schoolboys.findAll().spliterator(), false).filter(pupil -> pupil.getName().equals("Денис")).findFirst().orElseThrow();
        long id = created.getId();
        mvc.perform(get("/schoolboys/details/{id}", id)).andExpect(content().string(containsString("Денис")));
        mvc.perform(form("Иван").param("id", String.valueOf(id))).andExpect(redirectedUrl("/schoolboys/main"));
        mvc.perform(get("/schoolboys/details/{id}", id)).andExpect(content().string(containsString("Иван")));
        mvc.perform(post("/schoolboys/delete/{id}", id)).andExpect(redirectedUrl("/schoolboys/main"));
        assertThat(schoolboys.findById(id)).isEmpty();
    }

    @Test
    void checksExistenceAndRendersTheStudentExample() throws Exception {
        mvc.perform(get("/schoolboys/details/999")).andExpect(status().isNotFound());
        mvc.perform(get("/schoolboys/update/999")).andExpect(status().isNotFound());
        mvc.perform(form("Иван").param("id", "999")).andExpect(status().isNotFound());
        mvc.perform(post("/schoolboys/delete/999")).andExpect(status().isNotFound());
        mvc.perform(get("/students")).andExpect(status().isOk())
                .andExpect(content().string(containsString("Информационные технологии")));
    }

    private MockHttpServletRequestBuilder form(String name) {
        return post("/schoolboys/save").param("surname", "Новиков").param("name", name)
                .param("gender", "Мужской").param("nationality", "Русский")
                .param("height", "152.5").param("weight", "44.5")
                .param("birthDate", "2013-06-12").param("phoneNumber", "+7 900 000-10-21");
    }
}
