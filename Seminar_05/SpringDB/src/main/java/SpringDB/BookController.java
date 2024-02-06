package SpringDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/books")
public class BookController {
    private final BookService service;
    @Autowired
    public BookController(BookService service) {
        this.service = service;
    }
    @GetMapping
    public List<Book> getAllBooks(){
        return service.getAllBooks();
    }
    @GetMapping("/sort")
    public List<Book> sortBookById(){
        return service.sortBooksById();
    }
    @GetMapping("/{id}")
    public Book getBookById(@PathVariable Long id){
        return service.getBookById(id);
    }

    @PostMapping
    public Book createBook(@RequestBody Book book){
        return service.createBook(book);
    }

    @PutMapping("/{id}")
    public Book updateBookById(@PathVariable Long id, Book book){
        return service.updateBookById(book, id);
    }

    @DeleteMapping("/{id}")
    public void deleteBookById(@PathVariable Long id){
        service.deleteBookById(id);
    }
}
