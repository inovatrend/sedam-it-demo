package hr.sedamit.demo;

import hr.sedamit.demo.model.User;
import hr.sedamit.demo.service.UserManager;
import io.jsonwebtoken.lang.Collections;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

import java.util.Arrays;
import java.util.List;

@SpringBootTest
@ActiveProfiles("test")
class DemoApplicationTests {

    @Autowired
    private UserManager userManager;


    @Test
    void contextLoads() {
        List<User> users = userManager.getAllUsers();
        System.out.println(Arrays.toString(users.toArray()));
    }
}
