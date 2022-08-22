package com.example.pathfinderproject.service;

import com.example.pathfinderproject.model.entity.User;
import com.example.pathfinderproject.model.service.UserServiceModel;

public interface UserService {
    void registerUser(UserServiceModel map);
    UserServiceModel findByUsernameAndPassword(String username, String password);
    UserServiceModel findById(Long id);
    boolean isLogged();
    User findUserEntity();
}
