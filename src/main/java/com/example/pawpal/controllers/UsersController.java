package com.example.pawpal.controllers;

import com.example.pawpal.dto.SuccessDto;
import com.example.pawpal.dto.UserDto;
import com.example.pawpal.entities.UserEntity;
import com.example.pawpal.exceptions.ApiException;
import com.example.pawpal.repositories.UserRepository;
import com.example.pawpal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/users/")
public class UsersController {

    @Autowired
    private UsersService usersService;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("get-all")
    public ResponseEntity<List<UserDto>> getAllUsers() {
        return ResponseEntity.ok(usersService.getAllUsers());
    }

    @GetMapping("get/{username}")
    public List<UserEntity> getUserByUsername(@PathVariable(value = "username") String username) {
        if (username != null && !username.isEmpty()) {
            return userRepository.findAllByUsernameContainingIgnoreCase(username);
        } else {
            return userRepository.findAll();
        }
    }

    @PostMapping("create")
    public ResponseEntity<UserDto> create(@Validated @RequestBody UserDto user) {
        UserDto savedUser = usersService.saveUser(user);
        return ResponseEntity
                .created(URI.create("/pets/create" + savedUser.getUsername()))
                .body(savedUser);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<UserDto> update(@Validated @RequestBody UserDto user, @PathVariable Long id){
        usersService.getUserById(id)
                .orElseThrow(() -> new ApiException(
                        String.format("User with id %d not found", id),
                        HttpStatusCode.valueOf(404)
                ));
        user.setId(id);
        UserDto updatedUser = usersService.saveUser(user);
        return ResponseEntity.ok(updatedUser);
    }

    @DeleteMapping("delete/{id}")
    public SuccessDto delete(@PathVariable("id") Long id){
        usersService.getUserById(id)
                .orElseThrow(() -> new ApiException(
                        String.format("Pet with id %d not found", id), HttpStatusCode.valueOf(404)
                ));
        usersService.deleteUser(id);
        return new SuccessDto();
    }

}
