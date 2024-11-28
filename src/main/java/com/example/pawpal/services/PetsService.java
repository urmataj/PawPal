package com.example.pawpal.services;

import com.example.pawpal.dto.PetUpdateDto;
import com.example.pawpal.dto.SuccessDto;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public interface PetsService {

    Optional<PetUpdateDto> getPetById(Long id);

    PetUpdateDto savePet(PetUpdateDto pet);

    List<PetUpdateDto> getAllPets();

    PetUpdateDto updatePet(Long id, PetUpdateDto pet);

    SuccessDto deletePet(Long id);
}
