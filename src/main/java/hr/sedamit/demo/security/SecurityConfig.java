package hr.sedamit.demo.security;


import hr.sedamit.demo.service.UserManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.BeanIds;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@EnableWebSecurity
public class SecurityConfig {

    private UserManager userManager;

    public SecurityConfig(UserManager userManager) {
        this.userManager = userManager;
    }


    @Autowired
    public void configureGlobal(AuthenticationManagerBuilder authBuilder) throws Exception {
        authBuilder.userDetailsService(userManager);
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }



    @Configuration
    public static class FormWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        protected void configure(HttpSecurity http) throws Exception {
            http
                    .csrf().disable()
                    .authorizeRequests()
                    .antMatchers("/gui/author/*/edit").hasAuthority("EDIT_AUTHORS")
                    .and()
                    .formLogin().permitAll();
        }


    }

    @Configuration
    @Order(1)
    public static class ApiWebSecurityConfigurationAdapter extends WebSecurityConfigurerAdapter {

        private JwtAuthenticationEntryPoint entryPoint;

        private JwtRequestFilter jwtRequestFilter;

        public ApiWebSecurityConfigurationAdapter(JwtAuthenticationEntryPoint entryPoint, JwtRequestFilter jwtRequestFilter) {
            this.entryPoint = entryPoint;
            this.jwtRequestFilter = jwtRequestFilter;
        }


        protected void configure(HttpSecurity http) throws Exception {

            http
                    .csrf().disable()
                    .regexMatcher("(/api/authenticate)|(/user/(.*))|(/author/(.*))")
                    .authorizeRequests(reg -> {
                        reg
                                .antMatchers("/user/*/update").hasAuthority("EDIT_USERS")
                                .antMatchers("/user/*/add").hasAuthority("EDIT_USERS")
                                .antMatchers("/author/*/add").hasAuthority("EDIT_AUTHORS")
                                .antMatchers("/author/*/update").hasAuthority("EDIT_AUTHORS")
                                .antMatchers("/api/authenticate").permitAll();
                    })

                    .exceptionHandling().authenticationEntryPoint(entryPoint)
                    .and().sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                    .and()
                    .addFilterBefore(jwtRequestFilter, UsernamePasswordAuthenticationFilter.class);

        }

        @Bean(name = BeanIds.AUTHENTICATION_MANAGER)
        public AuthenticationManager authenticationManagerBean() throws Exception {
            return super.authenticationManagerBean();
        }

    }

}
