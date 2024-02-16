package books_store;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * GET /books
 * GET /products/{id}
 * POST /books
 * DELETE /books/{id}
 *
 * GET /readers
 * POST /readers
 * DELETE /products/{id}
 *
 * PUT /books/{id}/reader/{readerId}
 */
@SpringBootApplication
public class BooksStoreApplication {

	public static void main(String[] args) {
		SpringApplication.run(BooksStoreApplication.class, args);
	}

}
