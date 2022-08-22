package bg.softuni.security.service.impl;

import bg.softuni.security.model.dto.UserRegistrationDTO;
import bg.softuni.security.model.entity.UserEntity;
import bg.softuni.security.model.entity.UserRoleEntity;
import bg.softuni.security.model.enums.UserRoleEnum;
import bg.softuni.security.repository.UserRepository;
import bg.softuni.security.repository.UserRoleRepository;
import bg.softuni.security.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    private UserDetailsService userDetailsService;
    private UserRoleRepository userRoleRepository;
    private UserRepository userRepository;
    private String defaultPassword;
    private PasswordEncoder passwordEncoder;


    public UserServiceImpl(UserDetailsService userDetailsService,
                           UserRoleRepository userRoleRepository,
                           UserRepository userRepository, @Value("${app.default.password}") String defaultPassword,
                           PasswordEncoder passwordEncoder) {
        this.userDetailsService = userDetailsService;
        this.userRoleRepository = userRoleRepository;
        this.userRepository = userRepository;
        this.defaultPassword = defaultPassword;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void initUserRoles() {

        if (this.userRoleRepository.count() == 0){
            UserRoleEntity userRoleModerator = new UserRoleEntity();
            userRoleModerator.setRole(UserRoleEnum.MODERATOR);

            UserRoleEntity userRoleAdmin = new UserRoleEntity();
            userRoleAdmin.setRole(UserRoleEnum.ADMIN);

            this.userRoleRepository.save(userRoleModerator);
            this.userRoleRepository.save(userRoleAdmin);
        }

    }

    @Override
    public void initUsers() {

        if(this.userRepository.count() == 0){

            UserEntity adminUser = new UserEntity()
                    .setFirstName("Admin")
                    .setLastName("Admin")
                    .setEmail("admin@example.com")
                    .setPassword(this.passwordEncoder.encode(this.defaultPassword))
                    .setRoles(this.userRoleRepository.findAll());
            UserEntity moderatorUser = new UserEntity()
                    .setFirstName("Moderator")
                    .setLastName("Moderator")
                    .setEmail("moderator@example.com")
                    .setPassword(this.passwordEncoder.encode(this.defaultPassword))
                    .setRoles(Arrays.asList(this.userRoleRepository.findByRole(UserRoleEnum.MODERATOR)
                            .orElseThrow(() -> new IllegalArgumentException("Roles are not initialized properly"))));



            this.userRepository.saveAll(List.of(adminUser,moderatorUser));
        }
    }

    @Override
    public void createAccount(UserRegistrationDTO userRegistrationDTO) {

        UserEntity user = this.map(userRegistrationDTO);
        this.userRepository.save(user);

        var userDetails = this.userDetailsService.loadUserByUsername(userRegistrationDTO.getEmail());

        Authentication authentication = new UsernamePasswordAuthenticationToken(
                userDetails,
                userDetails.getPassword(),
                userDetails.getAuthorities()
        );

        SecurityContextHolder
                .getContext()
                .setAuthentication(authentication);

    }



    private UserEntity map(UserRegistrationDTO userRegistrationDTO){
        return new UserEntity()
                .setFirstName(userRegistrationDTO.getFirstName())
                .setLastName(userRegistrationDTO.getLastName())
                .setEmail(userRegistrationDTO.getEmail())
                .setPassword(this.passwordEncoder.encode(userRegistrationDTO.getPassword()));


    }

}
