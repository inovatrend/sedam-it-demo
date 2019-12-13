package hr.sedamit.demo.service;

import hr.sedamit.demo.model.Member;
import hr.sedamit.demo.model.User;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;

public interface UserManager extends UserDetailsService {

    List<User> getAllUsers();

    Optional<User> getUser(Long userId);

    User save(User user);

    void delete(Long userId);

}
