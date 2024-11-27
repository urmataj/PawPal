package com.example.pawpal.repositories;

import com.example.pawpal.Type;
import com.example.pawpal.entities.PostPetEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PostRepository extends JpaRepository<PostPetEntity, Long> {

    List<PostPetEntity> findByType(Type type); // Example: Find posts by type
}

