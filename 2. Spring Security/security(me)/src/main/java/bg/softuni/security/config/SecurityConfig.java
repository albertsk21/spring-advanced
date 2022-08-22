package bg.softuni.security.config;

import bg.softuni.security.model.enums.UserRoleEnum;
import bg.softuni.security.service.impl.AppUserDetailsService;
import org.springframework.boot.autoconfigure.security.servlet.PathRequest;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private AppUserDetailsService userDetailsService;
    private PasswordEncoder passwordEncoder;

    public SecurityConfig(AppUserDetailsService userDetailsService, PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {

        // http exposes api that allows us configure the web security
        http
                // which pages will be authorized?
                .authorizeRequests()
                //allow CSS at "common" static location (static/css)
                .requestMatchers(PathRequest.toStaticResources().atCommonLocations()).permitAll()
                // permit home page, login page and register page to be visible for all users
                .antMatchers("/","/users/login","/users/register").permitAll()
                // permit moderator page to be visible just for a moderator users
                .antMatchers("/pages/moderators").hasRole(UserRoleEnum.MODERATOR.name())
                // permit moderator page to be visible just for a admin users
                .antMatchers("/pages/admins").hasRole(UserRoleEnum.ADMIN.name())
                // any request should be authenticated
                .anyRequest()
                .authenticated()
                .and()
                .formLogin()
                    .loginPage("/users/login")
                    .usernameParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_USERNAME_KEY)
                    .passwordParameter(UsernamePasswordAuthenticationFilter.SPRING_SECURITY_FORM_PASSWORD_KEY)
                    .defaultSuccessUrl("/")
                    .failureForwardUrl("/users/login-error")
                    .and()
                .logout()
                    .logoutUrl("/users/logout")
                    .logoutSuccessUrl("/")
                    .invalidateHttpSession(true)
                    .deleteCookies("JSESSIONID");
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(this.userDetailsService)
                .passwordEncoder(this.passwordEncoder);
    }
}
