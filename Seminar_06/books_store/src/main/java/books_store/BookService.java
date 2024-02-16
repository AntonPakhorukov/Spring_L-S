package books_store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Service
public class BookService {
    @Autowired
    private BookRepository bookRepository;
    @Autowired
    private ReaderService readerService;

    public List<Book> findAll (){
        return bookRepository.findAll();
    }
    public Book findBookById(Long id) {
        return bookRepository.findById(id).orElseThrow(() -> new RuntimeException("Book with id: " + id + " not found."));
    }
    public Book save (Book book) {
        return bookRepository.save(book);
    }
    public void deleteBookById (Long id) {
        findBookById(id);
        bookRepository.deleteById(id);
    }
    public Book updateBook(Book book, Long id) {
        Book newBook = findBookById(id);
        newBook.setAuthor(book.getAuthor());
        newBook.setTitle(book.getTitle());
        return bookRepository.save(newBook);
    }
    public Book assignReaderToBook(Long id, Long readerId){ // назначить читателя
        Book existingBook = findBookById(id); // находим книгу
        Reader reader = readerService.findReaderById(readerId); // находим читателя
        existingBook.setReader(reader); // добавляем читателя к книге
        return bookRepository.save(existingBook); // сохраняем книгу
    }

}
