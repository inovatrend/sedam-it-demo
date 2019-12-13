package hr.sedamit.demo.web;

import hr.sedamit.demo.model.Role;
import hr.sedamit.demo.model.User;
import hr.sedamit.demo.service.RoleManager;
import hr.sedamit.demo.service.UserManager;
import hr.sedamit.demo.web.commands.UpdateUserCommand;
import hr.sedamit.demo.web.dto.DTOFactory;
import hr.sedamit.demo.web.dto.UserDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.annotation.PostConstruct;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/user")
@Slf4j
public class UserController {

    private UserManager userManager;

    private RoleManager roleManager;

    private PasswordEncoder passwordEncoder;

    @Value("${user.list.allowed:true}")
    private boolean allowListUsers;

    public UserController(UserManager userManager, RoleManager roleManager, PasswordEncoder passwordEncoder) {
        this.userManager = userManager;
        this.roleManager = roleManager;
        this.passwordEncoder = passwordEncoder;
    }

    @GetMapping(value = "/list")
    public List<UserDTO> listAllUsers(){

        if (allowListUsers) {
            List<User> allUsers = userManager.getAllUsers();
            List<UserDTO> dtos = allUsers.stream()
                    .map(DTOFactory::toUserDTO)
                    .collect(Collectors.toList());
            return dtos;
        }
        else {
            log.error("User listing not allowed!");
            throw new ResponseStatusException(HttpStatus.FORBIDDEN, "User listing not allowed!");
        }
    }

    @GetMapping("/{userId}")
    public UserDTO showUserDetails(@PathVariable Long userId) {
        Optional<User> optionalUser = userManager.getUser(userId);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();
            return DTOFactory.toUserDTO(user);
        } else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User with such id not found: " + userId);
    }

    @PostMapping("/add")
    public UserDTO addUser(@RequestBody UpdateUserCommand userData) {
        User user = new User();
        updateUserData(user, userData, true);
        User savedUser = userManager.save(user);
        return DTOFactory.toUserDTO(savedUser);
    }

    @PutMapping("/{userId}/update")
    public UserDTO updateUser(@PathVariable Long userId,
                              @RequestBody UpdateUserCommand userData) {
        Optional<User> optionalUser = userManager.getUser(userId);
        if (!optionalUser.isPresent())
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "No optionalUser with such id: " + userId);
        User user = optionalUser.get();
        updateUserData(user, userData, false);
        User savedUser = userManager.save(user);
        return DTOFactory.toUserDTO(savedUser);
    }

    @DeleteMapping("/delete/{userId}")
    public Boolean deleteUser(@PathVariable Long userId) {
        try {
            userManager.delete(userId);
            return Boolean.TRUE;
        } catch (Exception e) {
            return Boolean.FALSE;
        }
    }

    @PostConstruct
    public void init(){
        log.info("User controller ready");
    }

    private void updateUserData(User user, UpdateUserCommand command, boolean isNew){
        user.setUsername(command.getUsername());
        if (isNew) {
            String plainPassword = command.getPassword();
            user.setPassword( passwordEncoder.encode(plainPassword)  );
        }
        user.setFullName(command.getFullName());
        user.setAge(command.getAge());
        user.setActive(command.isActive());
        Optional<Role> role = roleManager.getRole(command.getRoleId());
        user.setRole(role.orElseThrow(() -> new RuntimeException("No role with such id: " + command.getRoleId())));
    }

}
