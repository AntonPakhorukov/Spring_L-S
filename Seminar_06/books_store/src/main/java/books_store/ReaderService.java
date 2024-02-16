package books_store;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ReaderService {
    @Autowired
    private ReaderRepository readerRepository;

    public List<Reader> findAll() {
        return readerRepository.findAll();
    }

    public Reader findReaderById(Long id) {
        return readerRepository.findById(id).orElseThrow(() -> new RuntimeException("Reader with id: " + id + " not found."));
    }

    public Reader save(Reader reader) {
        return readerRepository.save(reader);
    }
}
