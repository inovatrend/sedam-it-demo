package hr.sedamit.demo.dao;

import hr.sedamit.demo.model.Author;
import hr.sedamit.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;


public interface AuthorRepository extends JpaRepository<Author, Long>, JpaSpecificationExecutor<Author> {

    @Query("from Author u join fetch u.books")
    List<Author> findAllWithBooks();
}
