package bg.softuni.security.service;

import bg.softuni.security.model.entity.UserEntity;
import bg.softuni.security.model.entity.UserRoleEntity;
import bg.softuni.security.model.enums.UserRoleEnum;
import bg.softuni.security.repository.UserRepository;
import bg.softuni.security.service.impl.AppUserDetailsService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
class AppUserDetailsServiceTests {

    private AppUserDetailsService toTest;
    @Mock
    private UserRepository mockUserRepository;

    private UserEntity testUserEntity;

    @BeforeEach
    void setUp(){
        this.toTest = new AppUserDetailsService(this.mockUserRepository);

        this.testUserEntity = new UserEntity(){{
            setFirstName("Test");
            setLastName("Testing");
            setPassword("myPassword");
            setEmail("test@example.com");
            setId(Long.parseLong("2"));
            setRoles(List.of(new UserRoleEntity().setRole(UserRoleEnum.MODERATOR)));
        }};
    }

    @Test
    void loadUserByUsername_UserFound(){

        var testEmail = this.testUserEntity.getEmail();
        //Arrange
        Mockito.when(this.mockUserRepository.findUserEntityByEmail(testEmail))
                        .thenReturn(Optional.of(testUserEntity));

        //Act
        UserDetails actual =  toTest.loadUserByUsername(testUserEntity.getEmail());


        //Assert
        Assertions.assertEquals(testEmail,actual.getUsername());
        Assertions.assertEquals(testUserEntity.getPassword(),actual.getPassword());
        Assertions.assertEquals(testUserEntity.getRoles().size(), actual.getAuthorities().size());


        for (UserRoleEntity userRole : this.testUserEntity.getRoles()) {

            Assertions.assertTrue(actual
                    .getAuthorities()
                    .stream()
                    .filter(a ->("ROLE_" + userRole.getRole().name()).equals(a.getAuthority()))
                    .findAny()
                    .isPresent(),"is mapped correctly"
            );
        }

    }
    @Test
    void loadUserByUsername_UserNotFound() {


        var testEmail = "none@example.com";
        var exceptionMessage = String.format("USER with this email %s not exist",testEmail);

        //Arrange
        Mockito.when(this.mockUserRepository.findUserEntityByEmail(testEmail))
                .thenReturn(Optional.empty());

//        Act:  nothing to do

        Exception exception = Assertions.assertThrows(UsernameNotFoundException.class, () ->{
            toTest.loadUserByUsername(testUserEntity.getEmail());
        });
        Assertions.assertEquals(exceptionMessage,exception.getMessage());


    }
}