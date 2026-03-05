package k26.bookstore;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;

import k26.bookstore.domain.Book;
import k26.bookstore.domain.BookRepository;
import k26.bookstore.domain.Category;
import k26.bookstore.domain.CategoryRepository;

//for fysical db
//@SpringBootTest
//@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
//for h2 db
@DataJpaTest
public class BookRepositoryTests {

    @Autowired
    private BookRepository bookRepository;


    @Autowired
    private CategoryRepository categoryRepository;

    @Test
    public void findByTitleShouldReturnBook() {
        List<Book> books = bookRepository.findByTitle("Aku Ankka");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Carl B");
    }

    @Test
    public void findByTitleShouldReturnNothing() {
        List<Book> books = bookRepository.findByTitle("Mökki");
        assertThat(books).hasSize(1);
        assertThat(books.get(0).getAuthor()).isEqualTo("Carl B");
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
        List<Book> books = bookRepository.findByAuthor("Minna");
        Book book = books.get(0);
        bookRepository.delete(book);
        List<Book> newBooks = bookRepository.findByAuthor("Minna");
        assertThat(newBooks).hasSize(0);
    }

}
