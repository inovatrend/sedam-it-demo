package hr.sedamit.demo.dao;

import hr.sedamit.demo.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface BookRepository extends JpaRepository<Book, Long> {

    List<Book> findAllByAuthorId(Long id);
}
