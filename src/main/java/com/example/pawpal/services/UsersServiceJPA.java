package com.example.pawpal.services;

import com.example.pawpal.dto.SuccessDto;
import com.example.pawpal.dto.UserDto;
import com.example.pawpal.entities.UserEntity;
import com.example.pawpal.exceptions.ApiException;
import com.example.pawpal.mappers.UserMapper;
import com.example.pawpal.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsersServiceJPA implements UsersService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;

    @Override
    public Optional<UserDto> getUserById(Long id) {
        return Optional.ofNullable(
                userMapper.userEntityToUserDto(
                        userRepository.findById(id)
                                .orElse(null)
                )
        );
    }

    @Override
    public UserDto saveUser(UserDto userDto) {
        UserEntity user = userMapper.userDtoToUserEntity(userDto);
        UserEntity savedUser = userRepository.save(user);
        return userMapper.userEntityToUserDto(savedUser);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::userEntityToUserDto)
                .toList();
    }

    @Override
    public UserDto updateUser(Long id, UserDto user) {
        UserEntity toUpdate = userRepository.findById(id).get();
        if (toUpdate.getUsername() != null) {
            toUpdate.setUsername(user.getUsername());
        }
        if (toUpdate.getName() != null) {
            toUpdate.setName(user.getName());
        }
        if (toUpdate.getPassword() != null) {
            toUpdate.setPassword(user.getPassword());
        }
        if (toUpdate.getEmail() != null) {
            toUpdate.setEmail(user.getEmail());
        }
        if (toUpdate.getPhone() != null) {
            toUpdate.setPhone(user.getPhone());
        }
        return userMapper.userEntityToUserDto(userRepository.save(toUpdate));
    }

    @Override
    public SuccessDto deleteUser(Long id) {
        UserEntity toDelete = userRepository.findById(id).orElse(null);
        if (toDelete != null) {
            userRepository.delete(toDelete);
        } else {
            throw new ApiException("User not found with id: " + id, HttpStatusCode.valueOf(404));
        }
        return new SuccessDto();
    }
}
