package com.example.pawpal.services;

import com.example.pawpal.dto.SignUpDto;
import com.example.pawpal.entities.UserEntity;
import com.example.pawpal.exceptions.ApiException;
import com.example.pawpal.model.UserModel;
import com.example.pawpal.repositories.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatusCode;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
public class UserService implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        List<UserEntity> users = userRepository.findAllByUsernameContainingIgnoreCase(username);

        if (users.isEmpty()) {
            throw new UsernameNotFoundException(username + " not found");
        }

        UserEntity userEntity = users.get(0); // Assumes only one match is needed.

        return new UserModel(
                userEntity.getId(),
                userEntity.getUsername(),
                userEntity.getPassword(),
                userEntity.getName(),
                userEntity.getCreatedAt()
        );
    }


    public void saveUser(SignUpDto signUpDto) {
        log.info("Sign up user: {}", signUpDto.getUsername());
        UserEntity userEntity = new UserEntity();
        userEntity.setUsername(signUpDto.getUsername());
        userEntity.setUsername(signUpDto.getUsername());
        userEntity.setPassword(passwordEncoder.encode(signUpDto.getPassword()));

        try {
            userRepository.save(userEntity);
        } catch (DataIntegrityViolationException e) {
            throw new ApiException("User " + signUpDto.getUsername() + " is already exists", HttpStatusCode.valueOf(409));
        } catch (Exception e) {
            log.error("Error ", e);
            throw new ApiException("Error while creating user " + signUpDto.getUsername(), HttpStatusCode.valueOf(400));
        }
    }
}
