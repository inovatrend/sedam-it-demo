package hr.sedamit.demo.service;

import hr.sedamit.demo.model.User;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Profile;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

@Service
@Slf4j
@Profile("test")
public class DummyUserManager implements UserManager {

    private Map<Long, User> allUsers = new HashMap<>();

    private AtomicLong sequence = new AtomicLong(1);

    @Override
    public List<User> getAllUsers() {

        log.info("Listing users from memory");
        return new ArrayList<>(allUsers.values());
    }

    @Override
    public Optional<User> getUser(Long userId) {
        if (allUsers.containsKey(userId))
            return Optional.of(allUsers.get(userId));
        else return Optional.empty();
    }

    @Override
    public User save(User user) {
        if (user.getId() == null || user.getId() == 0){
            Long id = sequence.getAndIncrement();
            User newUser = new User(
                    id,
                    user.getUsername(),
                    user.getPassword(),
                    user.getFullName(),
                    user.getAge(),
                    user.isActive()
            );
            allUsers.put(id, newUser);
            return newUser;
        }
        else {
            if (!allUsers.containsKey(user.getId()))
                throw new RuntimeException("User doesn't exits, id: " + user.getId());
            allUsers.put(user.getId(), user);
            return user;
        }
    }

    @Override
    public void delete(Long userId) {
        allUsers.remove(userId);
    }

    @PostConstruct
    public void init(){
        log.info("Dummy user manager ready");
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<User> userOptional = allUsers.values().stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
        if (userOptional.isPresent())
            return userOptional.get();
        else throw new UsernameNotFoundException("No user with such username: " + username);
    }
}
