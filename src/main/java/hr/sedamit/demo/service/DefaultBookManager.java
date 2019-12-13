package hr.sedamit.demo.service;

import hr.sedamit.demo.dao.BookRepository;
import hr.sedamit.demo.model.Book;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;

@Service
@Profile("!test")
@Slf4j
public class DefaultBookManager implements BookManager {

    private BookRepository repository;

    public DefaultBookManager(BookRepository repository) {
        this.repository = repository;
    }

    @Override
    public List<Book> getAllBooks() {
        List<Book> books = repository.findAllByAuthorId(6L);
        return repository.findAll();
    }

    @Override
    public Optional<Book> getBook(Long bookId) {
        return repository.findById(bookId);
    }

    @Override
    public Book save(Book book) {
        Book savedBook = repository.save(book);
        return savedBook;
    }

    @Override
    public void delete(Long bookId) {
        repository.deleteById(bookId);
    }

    @PostConstruct
    public void init(){
        log.info("Default book manager ready");
    }
}
