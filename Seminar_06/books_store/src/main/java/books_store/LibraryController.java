package books_store;

import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@Tag(name = "Library API")
public class LibraryController {
    @Autowired
    private BookService bookService;
    @Autowired
    private ReaderService readerService;

    @GetMapping("/books")
    public List<Book> findAllBooks() {
        return bookService.findAll();
    }

    @GetMapping("/books/{id}")
    public Book findBookById(@PathVariable @Parameter(description = "Product id", example = "1'") Long id) {
        return bookService.findBookById(id);
    }

    @PostMapping("/books")
    public Book saveBook(@RequestBody Book book) {
        return bookService.save(book);
    }

    @PutMapping("/books/{id}")
    public Book updateBook(@PathVariable Long id, @RequestBody Book book) {
        return bookService.updateBook(book, id);
    }

    @PutMapping("/books/{id}/reader/{readerId}")
    public Book assignReaderToBook(@PathVariable Long id, @PathVariable Long readerId) {
        return bookService.assignReaderToBook(id, readerId);
    }

    @DeleteMapping("/books/{id}")
    public void deleteBook(@PathVariable Long id) {
        bookService.deleteBookById(id);
    }

    @GetMapping("/readers")
    public List<Reader> findAllReaders() {
        return readerService.findAll();
    }

    @GetMapping("/readers/{id}")
    public Reader findReaderBuId(@PathVariable Long id) {
        return readerService.findReaderById(id);
    }

    @PostMapping("/readers")
    public Reader saveReader(@RequestBody Reader reader) {
        return readerService.save(reader);
    }
}
