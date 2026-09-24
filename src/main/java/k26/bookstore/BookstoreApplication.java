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
import k26.bookstore.domain.User;
import k26.bookstore.domain.UserRepository;

@SpringBootApplication
public class BookstoreApplication {

	private static final Logger log = LoggerFactory.getLogger(BookstoreApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(BookstoreApplication.class, args);
	}

	@Bean
	public CommandLineRunner bookDemo(BookRepository bookRepository,
			CategoryRepository categoryRepository, UserRepository userRepository) {
		return (args) -> {

			log.info("kirjoja yhteensä " + bookRepository.count());
			log.info("Check if there is already data in the database");
			if (userRepository.count() == 0) {
				log.info("Create some users");
				// Create users: admin/admin user/user
				User user1 = new User("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
				User user12 = new User("Minna", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6",
						"ADMIN");
				User user2 = new User("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C", "ADMIN");
				userRepository.save(user1);
				userRepository.save(user2);
				userRepository.save(user12);
			}

			if (categoryRepository.count() == 0) {
				log.info("TODO: save some categories");
				Category category1 = new Category("sarjakuva");
				categoryRepository.save(category1);
				categoryRepository.save(new Category("dokkari"));
				log.info("save a couple of books");
				// save vastaa sql insert lausetta
				bookRepository.save(new Book("Aku Ankka", "Carl B", 1945, category1));
				bookRepository.save(new Book("Kalle Ankka", "Carl B", 1946, category1));
			}

			log.info("fetch all books");
			// findAll vastaa select * komentoa
			for (Book kirja : bookRepository.findAll()) {
				log.info(kirja.toString());
			}

		};

	}
}
