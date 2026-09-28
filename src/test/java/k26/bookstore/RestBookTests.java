package k26.bookstore;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import k26.bookstore.domain.Book;
import tools.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
// Käytetään h2-kantaa testauksessa
// @org.springframework.test.context.ActiveProfiles("test")
public class RestBookTests {

    @Autowired
    private MockMvc mockMvc;

    // Smoke test case
    @Test
    void getBooksWorks() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    // Smoke test case
    @Test
    void getApiCategoriesWorks() throws Exception {
        mockMvc.perform(get("/rest/categories"))
                .andExpect(status().isOk());
    }

    // Get all books
    @Test
    public void testGetBooksWithoutAuth() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    // Create new book
    @Test
    public void testPostBook() throws Exception {
        String newBookJson = """
                {
                    "title":"Test Book",
                    "author":"Test Author",
                    "year":2024
                }
                """;

        String response = mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newBookJson))
                .andExpect(status().isOk()).andReturn()
                .getResponse()
                .getContentAsString();

        System.out.println("UUDEN KIRJAN TIEDOT OVAT: " + response);
        ObjectMapper mapper = new ObjectMapper();
        Book createdBook = mapper.readValue(response, Book.class);
        System.out.println("ID = " + createdBook.getId());

        // jA POISTA KIRJA, ETTEI SE JÄÄ KANTAAN
        Long id = createdBook.getId();
        mockMvc.perform(delete("/books/" + id))
                .andExpect(status().isOk());

    }

}
