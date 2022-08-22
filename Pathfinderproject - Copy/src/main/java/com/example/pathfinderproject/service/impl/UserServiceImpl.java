package com.example.pathfinderproject.service.impl;

import com.example.pathfinderproject.model.entity.User;
import com.example.pathfinderproject.model.entity.enums.UserLevelEnum;
import com.example.pathfinderproject.model.service.UserServiceModel;
import com.example.pathfinderproject.repository.UserRepository;
import com.example.pathfinderproject.service.UserService;
import com.example.pathfinderproject.util.CurrentUser;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final ModelMapper modelMapper;
    private final CurrentUser currentUser;

    public UserServiceImpl(UserRepository userRepository, ModelMapper modelMapper, CurrentUser currentUser) {
        this.userRepository = userRepository;
        this.modelMapper = modelMapper;
        this.currentUser = currentUser;
    }

    @Override
    public void registerUser(UserServiceModel userServiceModel){
        User user = modelMapper.map(userServiceModel, User.class);
        user.setLevel(UserLevelEnum.BEGINNER);

        userRepository.save(user);
    }

    @Override
    public UserServiceModel findByUsernameAndPassword(String username, String password){
        return userRepository
                .findByUsernameAndPassword(username, password)
                .map(u -> modelMapper.map(u, UserServiceModel.class))
                .orElse(null);
    }

    @Override
    public boolean isLogged()
    {
        return currentUser.getId() != null;
    }

    @Override
    public UserServiceModel findById(Long id)
    {
        return userRepository.findById(id).map(u -> modelMapper.map(u, UserServiceModel.class)).orElse(null);
    }

    @Override
    public User findUserEntity() {
        return userRepository.findById(currentUser.getId()).orElse(null);
    }
}
