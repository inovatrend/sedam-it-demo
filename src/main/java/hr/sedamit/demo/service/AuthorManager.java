package hr.sedamit.demo.service;

import hr.sedamit.demo.model.Author;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface AuthorManager {

    Page<Author> getAllAuthors(Pageable pageable);

    Optional<Author> getAuthor(Long authorId);

    Author save(Author author);

    void delete(Long authorId);
}
