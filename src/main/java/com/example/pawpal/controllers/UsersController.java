package com.example.pawpal.controllers;

import com.example.pawpal.dto.UserDto;
import com.example.pawpal.entities.UserEntity;
import com.example.pawpal.repositories.UserRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users/")
public class UsersController {

    private UserRepository userRepository;

    @GetMapping("/get-all")
    public List<UserEntity> getAllUsers(@RequestParam(value = "username", required = false) String username) {
        if (username == null) {
            return userRepository.findAllByUsernameContainingIgnoreCase(username);
        } else {
            return userRepository.findAll();
        }
    }

    @PostMapping("/create")
    public UserEntity create(@RequestBody UserEntity user) {
        return userRepository.save(user);
    }

    @PutMapping("update/{id}")
    public UserEntity update(@RequestBody UserDto user, @PathVariable Long id){
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
        return userRepository.save(toUpdate);
    }

}
