package hr.sedamit.demo.service;

import hr.sedamit.demo.model.Book;

import java.util.List;
import java.util.Optional;

public interface BookManager {

    List<Book> getAllBooks();

    Optional<Book> getBook(Long bookId);

    Book save(Book book);

    void delete(Long bookId);
}
