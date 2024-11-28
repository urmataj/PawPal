package com.example.pawpal.services;

import com.example.pawpal.dto.SuccessDto;
import com.example.pawpal.dto.UserDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface UsersService {

    Optional<UserDto> getUserById(Long id);

    UserDto saveUser(UserDto userDto);

    List<UserDto> getAllUsers();

    UserDto updateUser(Long id, UserDto userDto);

    SuccessDto deleteUser(Long id);
}
