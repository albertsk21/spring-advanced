package com.example.pathfinderproject.service;

import com.example.pathfinderproject.model.entity.Role;
import com.example.pathfinderproject.model.entity.User;
import com.example.pathfinderproject.model.entity.enums.RoleNameEnum;
import com.example.pathfinderproject.repository.UserRepository;
import com.example.pathfinderproject.service.impl.PathfinderUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.core.GrantedAuthority;

import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@SpringBootTest
@AutoConfigureMockMvc
public class PathfinderUserDetailsServiceTest {

    private Role adminRole, userRole;
    private PathfinderUserDetailsService serviceToTest;
    private User testUser;

    @Mock
    private UserRepository mockUserRepository;


    @BeforeEach
    void init() {

        //ARRANGE
        serviceToTest = new PathfinderUserDetailsService(mockUserRepository);

        adminRole = new Role();
        adminRole.setRole(RoleNameEnum.ADMIN);

        userRole = new Role();
        userRole.setRole(RoleNameEnum.USER);

        testUser = new User();
        testUser.setUsername("test");
        testUser.setPassword("topsecret");
        testUser.setRoles(Set.of(adminRole, userRole));
    }


    @Test
    void testUserFound(){
        //Arrange
        Mockito.when(mockUserRepository.findByUsername(testUser.getUsername()))
                .thenReturn(Optional.of(testUser));

        // Act

        var actual = serviceToTest.loadUserByUsername(testUser.getUsername());
        //Assert

        String expectedRoles = "ROLE_ADMIN, ROLE_USER";
        String actualRoles = actual.getAuthorities()
                .stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.joining(", "));

        Assertions.assertEquals(actual.getUsername(),testUser.getUsername());
        Assertions.assertEquals(expectedRoles,actualRoles);
    }

}
