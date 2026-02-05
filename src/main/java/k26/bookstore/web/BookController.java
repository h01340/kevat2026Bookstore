package k26.bookstore.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import k26.bookstore.domain.Book;
import k26.bookstore.domain.BookRepository;


@Controller
public class BookController {
private static final Logger log = LoggerFactory.getLogger(BookController.class);


    private final BookRepository bookRepository;
	// constructor injection. Can only be one constructor then.
	public BookController(BookRepository bookRepository) {
		this.bookRepository = bookRepository;
	}

    @GetMapping("/books")
    public String getBooks(Model model) {

        //bookRepository.findAll = Sql select * from book
        model.addAttribute("books", bookRepository.findAll());
        return "/books";
    }

    @GetMapping("/addBook")
    public String addBook(Model model) {
  
        model.addAttribute("book", new Book());
        return "/addBook";
    }

    
    @PostMapping("/saveBook")
    public String addBook(Book book, Model model) {
        log.info("Kirja " + book.toString());
        bookRepository.save(book);
        return "redirect:/books";
    }
    
    
    

}
