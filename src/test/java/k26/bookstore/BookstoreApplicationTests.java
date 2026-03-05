package k26.bookstore;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import k26.bookstore.web.BookController;
import k26.bookstore.web.RestBookController;

@SpringBootTest
class BookstoreApplicationTests {
	
	@Autowired 
	private BookController bookController;

	@Autowired 
	private RestBookController restBookController;
	@Test
	void contextLoads() {
	}

	//Some smoke test cases
    @Test
    public void bookControllerLoad() throws Exception {
      assertThat(bookController).isNotNull();
    }

	@Test
    public void restBookControllerLoad() throws Exception {
      assertThat(restBookController).isNotNull();
    }


}
