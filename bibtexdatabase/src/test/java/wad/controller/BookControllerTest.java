package wad.controller;

import java.util.List;
import javafx.application.Application;
import static org.junit.Assert.assertEquals;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import wad.domain.Book;
import wad.repository.BookRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = wad.Application.class)
@WebAppConfiguration
public class BookControllerTest {

    private final String API_URI = "/*";

    @Autowired
    private WebApplicationContext webAppContext;
    
    @Autowired
    private BookRepository bookRepository;

    private MockMvc mockMvc;

    @Before
    public void setUp() {
        this.mockMvc = MockMvcBuilders.webAppContextSetup(webAppContext).build();
        Book book = new Book();
        Book book2 = new Book();
        Book book3 = new Book();
        bookRepository.save(book);
        bookRepository.save(book2);
        bookRepository.save(book3);
    }

    @Test
    public void statusOk() throws Exception {
        mockMvc.perform(get(API_URI))
                .andExpect(status().isOk());
    }

    public void modelHasAttribute() throws Exception {
        MvcResult res = mockMvc.perform(get(API_URI))
                .andExpect(status().isOk())
                .andExpect(model().attributeExists("books"))
                .andReturn();

        List<Book> messages = (List) res.getModelAndView().getModel().get("books");
        assertEquals(3, messages.size());
        // tarkista lista
    }

}
