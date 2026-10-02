package k26.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.validation.Valid;
import k26.bookstore.domain.Book;
import k26.bookstore.domain.BookRepository;
import k26.bookstore.domain.CategoryRepository;

@Controller
public class BookController {
    private static final Logger log = LoggerFactory.getLogger(BookController.class);

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookController(BookRepository bookRepository,
            CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    @GetMapping(value = { "/", "/booklist" })
    public String getBooks(Model model) {

        // bookRepository.findAll = Sql select * from book
        model.addAttribute("books", bookRepository.findAll());
        return "books";
    }

    @GetMapping("/addBook")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String addBook(Model model) {
        model.addAttribute("book", new Book());
        model.addAttribute("categories", categoryRepository.findAll());
        return "addBook";
    }

    @PostMapping("/saveBook")
    // note. hasRole('ADMIN') works only with H2 db
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveBook(@Valid Book book, BindingResult bindingResult, Model model) {
        log.info("Kirja " + book.toString());
        if (bindingResult.hasErrors()) {
            log.info("validation error tapahtui: " + book.toString());
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryRepository.findAll());
            return "addBook";

        }
        bookRepository.save(book);
        return "redirect:/booklist";
    }

    @GetMapping("/deleteBook/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String deleteBook(@PathVariable("id") Long bookId, Model model) {
        log.info("Kirjan id " + bookId);
        // sql delete from book where id =?
        bookRepository.deleteById(bookId);
        return "redirect:/booklist";
    }

    @GetMapping("/editBook/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String editBook(@PathVariable("id") Long bookId, Model model) {
        log.info("Kirjan id " + bookId);

        model.addAttribute("book", bookRepository.findById(bookId));
        model.addAttribute("categories", categoryRepository.findAll());
        return "editBook";
    }

    @PostMapping("/saveEditedBook")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String saveEditedBook(@Valid @ModelAttribute Book book, BindingResult bindingResult, Model model) {
        log.info("EDITED BOOK: " + book.toString());
        if (bindingResult.hasErrors()) {
            log.info("validation error tapahtui: " + book.toString());
            model.addAttribute("book", book);
            model.addAttribute("categories", categoryRepository.findAll());
            return "editBook";
        }
        bookRepository.save(book);
        return "redirect:/booklist";
    }

}
