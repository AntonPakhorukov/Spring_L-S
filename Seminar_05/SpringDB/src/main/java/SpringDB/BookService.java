package SpringDB;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookService {
    @Autowired
    private BookRepository repository;


    public Book getBookById(Long id){
        return repository.findById(id).orElseThrow(() -> new RuntimeException("Book not found"));
    }
    public Book createBook(Book book){
        return repository.save(book);
    }
    public void deleteBookById(Long id){
        getBookById(id);
        repository.deleteById(id);
    }
    public List<Book> getAllBooks(){
        return repository.findAll();
    }
    public List<Book> sortBooksById(){
        return repository.findAll().stream().sorted(Comparator.comparing(Book::getId)).collect(Collectors.toList());
    }
    public Book updateBookById(Book book, Long id){
        Book newBook = getBookById(id);
        newBook.setTitle(book.getTitle());
        newBook.setAuthor(book.getAuthor());
        newBook.setPublicationYear(book.getPublicationYear());
        return repository.save(newBook);
    }
}
