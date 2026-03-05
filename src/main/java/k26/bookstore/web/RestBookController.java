package k26.bookstore.web;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import k26.bookstore.domain.Book;
import k26.bookstore.domain.BookRepository;

@RestController
public class RestBookController {

    private static final Logger log = LoggerFactory.getLogger(RestBookController.class);
    
    private final BookRepository bookRepository;
    
    public RestBookController(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    @GetMapping("/books")
    public Iterable<Book> findAllBooks() {
        return bookRepository.findAll();
    }
    
    @GetMapping("/books/{id}")
    public Optional<Book> findById(@PathVariable("id") Long bookId) {
        return bookRepository.findById(bookId);
    }

    @PostMapping("/books")
    public Book saveBook(@RequestBody Book book) {
        log.info("Save new book to db: " + book);
        return bookRepository.save(book);
    }

    @PutMapping("books/{id}")
    public Book saveEditedBook(@RequestBody Book editedBook, @PathVariable Long id) {
		log.info("Update the book information to db: " + editedBook + " and id is " + id);
        editedBook.setId(id);

		return bookRepository.save(editedBook);
	}
    @DeleteMapping("/books/{id}")
    // public void deleteBook(@PathVariable Long id) {
    
    public Iterable<Book> deleteBook(@PathVariable Long id) {
        System.out.println("poistettavan kirjan id " + id);
        bookRepository.deleteById(id);
        return bookRepository.findAll();
    }






    //TODO: korjaa
    @GetMapping("/booksByTitle")
    public List<Book> findByTitle(@RequestParam String title) {
        return bookRepository.findByAuthor(title);
    }

    //TODO: https://openlibrary.org/developers/api
    


}
