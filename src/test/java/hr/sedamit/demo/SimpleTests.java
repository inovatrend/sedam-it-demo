package hr.sedamit.demo;

import hr.sedamit.demo.model.User;
import hr.sedamit.demo.security.JwtTokenUtil;
import org.junit.Assert;
import org.junit.Test;

public class SimpleTests {

    @Test
    public void testTokenNotReturningNull() {
        JwtTokenUtil jwtUtil = new JwtTokenUtil();
        jwtUtil.setSecret("password");
        User user = new User(1L, "maja", "maja", "Maja M.", 26, true);
        String token  = jwtUtil.generateToken(user);
        Assert.assertNotNull(token);
    }
}
