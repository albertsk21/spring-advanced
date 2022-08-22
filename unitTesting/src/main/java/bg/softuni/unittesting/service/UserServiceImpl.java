package bg.softuni.unittesting.service;

import bg.softuni.unittesting.model.entity.User;
import bg.softuni.unittesting.repository.UserRepository;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User getUserByUsername(String username) {
        return this.userRepository.findByUsername(username).get() ;
    }
}
