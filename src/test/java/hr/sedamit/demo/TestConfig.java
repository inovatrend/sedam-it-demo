package hr.sedamit.demo;

import hr.sedamit.demo.model.User;
import hr.sedamit.demo.service.UserManager;
import org.mockito.Mockito;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;

import java.util.Optional;


@Configuration
public class TestConfig {


    @Bean
    @Primary
    public UserManager userManager() {
        UserManager userManager = Mockito.mock(UserManager.class);
        Mockito.when(userManager.getUser(1L)).thenReturn(Optional.of(new User(1L, "maja", "maja", "Maja M.", 26, true)));
        return userManager;
    }


}
