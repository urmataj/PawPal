package com.example.pawpal.repositories;

import com.example.pawpal.entities.PetEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PetRepository extends JpaRepository<PetEntity, Long> {
}
