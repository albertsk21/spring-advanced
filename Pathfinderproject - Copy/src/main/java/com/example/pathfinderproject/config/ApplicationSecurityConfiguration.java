package com.example.pathfinderproject.config;

import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
public class ApplicationSecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService userDetailsService;
    private final PasswordEncoder passwordEncoder;

    public ApplicationSecurityConfiguration(
            UserDetailsService userDetailsService,
            PasswordEncoder passwordEncoder
    ) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests().
                requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll().
                antMatchers("/", "/users/login", "/users/register", "/users/login/error").permitAll().
                antMatchers("/routes/**").permitAll().
                antMatchers("/api/comments/**","/comments/**").permitAll().
                antMatchers("/**").authenticated().
                and().
                formLogin().
                loginPage("/users/login").
                usernameParameter("username").
                passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY).
                defaultSuccessUrl("/").
                failureForwardUrl("/users/login/error").
                and().
                logout().
                logoutUrl("/users/logout").
                logoutRequestMatcher(new AntPathRequestMatcher("/users/logout", "GET")).
                logoutSuccessUrl("/").
                invalidateHttpSession(true).
                deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder(passwordEncoder);
    }
}
