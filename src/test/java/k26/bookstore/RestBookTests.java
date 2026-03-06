package k26.bookstore;


import javax.sql.DataSource;

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
//addFilters configuration enables testing without autentication
@AutoConfigureMockMvc(addFilters = false)
//Käytetään h2-kantaa testauksessa
@org.springframework.test.context.ActiveProfiles("test") 
public class RestBookTests {

    @Autowired
    private MockMvc mockMvc;


    //If you want to print which db is used
    @Autowired
    DataSource dataSource;
    @Test
    void printDatasource() throws Exception {
        System.out.println("Testidatan lähde: " + dataSource.getConnection().getMetaData().getURL());
    } 


    // Get all books
    @Test
    public void testGetBooksWithoutAuth() throws Exception {
        mockMvc.perform(get("/books"))
                .andExpect(status().isOk());
    }

    //Create new book
    @Test
    public void testPostBook() throws Exception {
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
