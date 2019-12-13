package hr.sedamit.demo.dao;

import hr.sedamit.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;


public interface UserRepository extends JpaRepository<User, Long>, JpaSpecificationExecutor {


    @Query("from User u where u.active = true")
    List<User> findOnlyActiveUsers();

    Optional<User> findByUsername(String username);


}
