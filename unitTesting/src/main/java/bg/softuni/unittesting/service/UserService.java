package bg.softuni.unittesting.service;

import bg.softuni.unittesting.model.entity.User;

public interface UserService {
    User getUserByUsername(String username);
}
