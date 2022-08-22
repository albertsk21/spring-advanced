package com.example.springsecurityapplication.auth;

import com.example.springsecurityapplication.security.ApplicationUserRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Repository;
import java.util.*;

@Repository("fake")
public class FakeApplicationUserDaoService implements ApplicationUserDao{
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder(10);


    @Override
    public Optional<ApplicationUser> selectApplicationUserByUsername(String username) {
        return this.applicationUserList
                .stream()
                .filter(user -> user.getUsername().equals(username))
                .findFirst();
    }

    private final List<ApplicationUser> applicationUserList = new ArrayList<>(List.of(

            new ApplicationUser(
                    "annasmith",
                    this.passwordEncoder.encode("password"),
                    ApplicationUserRole.STUDENT.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true
            ),
            new ApplicationUser(
                    "linda",
                    this.passwordEncoder.encode("password"),
                    ApplicationUserRole.ADMIN.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true
            ),
            new ApplicationUser(
                    "tom",
                    this.passwordEncoder.encode("password"),
                    ApplicationUserRole.ADMIN_TRAINEE.getGrantedAuthorities(),
                    true,
                    true,
                    true,
                    true
            )

    ));
}
