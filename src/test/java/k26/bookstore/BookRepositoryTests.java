package k26.bookstore;

import java.util.List;

import javax.sql.DataSource;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;

import k26.bookstore.domain.Book;
import k26.bookstore.domain.BookRepository;
import k26.bookstore.domain.Category;
import k26.bookstore.domain.CategoryRepository;

@DataJpaTest
@ActiveProfiles("test")   // käyttää testiasetuksia → H2
public class BookRepositoryTests {

    //Field injection can be used in test cases
    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private CategoryRepository categoryRepository;



    //Check which db is used
    @Autowired
    DataSource dataSource;
    @Test
    void printDatasource() throws Exception {
        System.out.println("Database URL: " + dataSource.getConnection().getMetaData().getURL());
         Iterable<Book> books = bookRepository.findAll();
         assertThat(books).isNotEmpty();
    } 


    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = bookRepository.findByTitle("Puutarha");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Minni Hiiri");
    }


    @Test
    public void createNewBook() {
        Category category = new Category("Sarjis");
        categoryRepository.save(category);
        Book book = new Book("Mikki Hiiri", "Minni Hiiri", 2026, category);
        bookRepository.save(book);
        assertThat(book.getId()).isNotNull();
    }

    @Test
    public void deleteBook() {
        List<Book> books = bookRepository.findByAuthor("Minni Hiiri");
        Book book = books.get(0);
        bookRepository.delete(book);
        List<Book> newBooks = bookRepository.findByAuthor("Minni Hiiri");
        assertThat(newBooks).hasSize(0);
    }

}
