package k26.bookstore;


import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
//@AutoConfigureMockMvc
@AutoConfigureMockMvc(addFilters = false)
public class RestBookTests {
 @Autowired
    
 private MockMvc mockMvc;

   
    // Get all books
    @Test
    public void testGetBooksWithoutAuth() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    @Test
    public void testPostBookAsAdmin() throws Exception {
        String newBookJson = """
        {
            "title":"Test Book",
            "author":"Test Author",
            "year":2024
        }
        """;

        mockMvc.perform(post("/books")
                .contentType(MediaType.APPLICATION_JSON)
                .content(newBookJson))
                .andExpect(status().isOk());
    }

   }
