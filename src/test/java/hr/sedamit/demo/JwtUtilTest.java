package hr.sedamit.demo;

import hr.sedamit.demo.model.User;
import hr.sedamit.demo.security.JwtTokenUtil;
import hr.sedamit.demo.service.UserManager;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.core.env.Environment;
import org.springframework.mock.env.MockEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Optional;


@RunWith(SpringRunner.class)
@ContextConfiguration(classes = { TestConfig.class})
@ActiveProfiles("test")
public class JwtUtilTest {


    @Autowired
    UserManager userManager;


    @Autowired
    ApplicationContext applicationContext;


    @Test
    public void testTokenGenerationWithMockUserManager() {

        JwtTokenUtil jwtUtil = new JwtTokenUtil();
        jwtUtil.setSecret("password");

        Optional<User> user = userManager.getUser(1L);
        String token = jwtUtil.generateToken(user.get());
        Assertions.assertNotNull(token);

        String nameFromToken = jwtUtil.getUsernameFromToken(token);
        Assert.assertEquals(nameFromToken, user.get().getUsername());

    }


}
