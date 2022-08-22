package bg.softuni.unittesting.service;

import bg.softuni.unittesting.model.entity.User;
import bg.softuni.unittesting.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;
import java.util.UUID;

@SpringBootTest
public class UserServiceTests {

    private User userTest;
    @Mock
    private UserRepository userRepository;

    @BeforeEach
    public void init() {
        this.userTest = new User()
                .setId(UUID.randomUUID().toString())
                .setPassword("security")
                .setUsername("Peter");


//        this.userRepository = Mockito.mock(this.userRepository.getClass());

    }

    @Test
    public void userService_GetUserWithCorrectUsername_ShouldReturnCorrect(){
        Mockito.when(this.userRepository.findByUsername(this.userTest.getUsername()))
                .thenReturn(Optional.of(this.userTest));
        UserService userService = new UserServiceImpl(this.userRepository);

        User user = userService.getUserByUsername("Peter");

        Assertions.assertEquals(user,this.userTest);

    }
}

