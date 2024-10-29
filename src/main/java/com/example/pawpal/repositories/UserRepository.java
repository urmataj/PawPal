package com.example.pawpal.repositories;

import com.example.pawpal.entities.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserRepository extends JpaRepository<UserEntity, Long> {
    List<UserEntity> findAllByUsernameContainingIgnoreCase(String username);
}
