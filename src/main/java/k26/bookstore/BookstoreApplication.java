package k26.bookstore;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import k26.bookstore.domain.Book;
import k26.bookstore.domain.BookRepository;
import k26.bookstore.domain.Category;
import k26.bookstore.domain.CategoryRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository, 
		CategoryRepository categoryRepository) {
		return (args) -> {
			log.info("TODO: save some categories");
			Category category1 = new Category("sarjakuva");
			categoryRepository.save(category1);
			categoryRepository.save(new Category("dokkari"));
			

			log.info("save a couple of books");
			// save vastaa sql insert lausetta
			bookRepository.save(new Book("Aku Ankka", "Carl B", 1945,category1));
			bookRepository.save(new Book("Kalle Ankka", "Carl B", 1946, category1));

			log.info("fetch all books");
			// findAll vastaa select * komentoa
			for (Book kirja : bookRepository.findAll()) {
				log.info(kirja.toString());
			}

		};
	}

}
