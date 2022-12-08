package com.example.booklibrary;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class BookControllerTest {

    @Autowired
    private MockMvc mvc;
    @Test
    void getAllBooks() throws Exception {
        String expectedJson= """
             [
                 {
                     "title": "Basic Java",
                     "author": "Tom",
                     "art": "E_BOOK",
                     "isbn": "123456"
                 },
                 {
                     "title": "Advanced Java",
                     "author": "Max",
                     "art": "HARD_COVER",
                     "isbn": "123344"
                 }
             ]
        """;
        //when & then
        mvc.perform(get("/books"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson,true));
    }

    @Test
    void getBookByISBN() throws Exception {
        //given
        String expectedJson= """
                {
                     "title": "Basic Java",
                     "author": "Tom",
                     "art": "E_BOOK",
                     "isbn": "123456"
                 }
                """;
        //when & then
        mvc.perform(get("/books/123456"))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson,true));
    }

    @Test
    void putBook() throws Exception {
        //given
        String requestBody="""
                {
                     "title": "Basic Java",
                     "author": "Tom",
                     "art": "E_BOOK",
                     "isbn": "123123"
                 }
                """;
        String expectedJson= """
                {
                     "title": "Basic Java",
                     "author": "Tom",
                     "art": "E_BOOK",
                     "isbn": "123123"
                 }
                """;
        //when & then
        mvc.perform(MockMvcRequestBuilders.put("/books/123123")
                .contentType(MediaType.APPLICATION_JSON)
                .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson,true));
    }


    @Test
    void deleteBook() throws Exception {
        //given
        String expectedJson= """
         [
             {
                 "title": "Advanced Java",
                 "author": "Max",
                 "art": "HARD_COVER",
                 "isbn": "123344"
             }
         ]
        """;

        //when & then
        mvc.perform(MockMvcRequestBuilders.delete("/books/{ISBN}","123456")
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(content().json(expectedJson,true));
    }


}