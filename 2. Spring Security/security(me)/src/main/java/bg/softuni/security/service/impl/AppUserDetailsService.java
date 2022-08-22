package bg.softuni.security.service.impl;

import bg.softuni.security.model.entity.UserEntity;
import bg.softuni.security.model.entity.UserRoleEntity;
import bg.softuni.security.repository.UserRepository;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;


@Service
public class AppUserDetailsService implements UserDetailsService {

    private UserRepository userRepository;

    public AppUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {


        return this.userRepository.
                findUserEntityByEmail(email)
                .map(this::map)
                .orElseThrow(() -> new UsernameNotFoundException(String.format("USER with this email %s not exist",email)));
    }

    private UserDetails map(UserEntity userEntity){

        return new User(
                userEntity.getEmail(),
                userEntity.getPassword(),
                this.asGrantedAuthorities(userEntity.getRoles())
        );


    }

    private List<GrantedAuthority> asGrantedAuthorities(List<UserRoleEntity> roles){
        return roles.stream()
                .map( role -> new SimpleGrantedAuthority("ROLE_" + role.getRole().name()))
                .collect(Collectors.toList());
    }
}
