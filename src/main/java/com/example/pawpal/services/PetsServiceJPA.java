package com.example.pawpal.services;

import com.example.pawpal.dto.PetUpdateDto;
import com.example.pawpal.dto.SuccessDto;
import com.example.pawpal.entities.PetEntity;
import com.example.pawpal.exceptions.ApiException;
import com.example.pawpal.mappers.PetMapper;
import com.example.pawpal.repositories.PetRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PetsServiceJPA implements PetsService {

    private final PetRepository petRepository;
    private final PetMapper petMapper;

    public  PetUpdateDto convertEntityToDTO(PetEntity pet) { return petMapper.petToPetDto(pet);}

    @Override
    public Optional<PetUpdateDto> getPetById(Long id) {
        return Optional.ofNullable(
                petMapper.petToPetDto(
                        petRepository.findById(id)
                                .orElse(null)
                )
        );
    }

    @Override
    public PetUpdateDto savePet(PetUpdateDto pet) {
        PetEntity newPet = petMapper.petDtoToPet(pet);
        PetEntity savedPet = petRepository.save(newPet);
        return petMapper.petToPetDto(savedPet);
    }

    @Override
    public List<PetUpdateDto> getAllPets() {
        return petRepository.findAll()
                .stream()
                .map(petMapper::petToPetDto)
                .toList();
    }

    @Override
    public PetUpdateDto updatePet(Long id, PetUpdateDto pet) {
        PetEntity toUpdate = petRepository.findById(id).get();
        if (pet.getName() != null) {
            toUpdate.setName(pet.getName());
        }
        if (pet.getBreed() != null) {
            toUpdate.setBreed(pet.getBreed());
        }
        if (pet.getGender() != null) {
            toUpdate.setGender(pet.getGender());
        }
        if (pet.getAge() != null) {
            toUpdate.setAge(pet.getAge());
        }
        if (pet.getWeight() != null) {
            toUpdate.setWeight(pet.getWeight());
        }
        if (pet.getColor() != null) {
            toUpdate.setColor(pet.getColor());
        }
        if (pet.getSpecies() != null) {
            toUpdate.setSpecies(pet.getSpecies());
        }
        if (pet.getId() != null) {
            toUpdate.setId(pet.getId());
        }
        return petMapper.petToPetDto(petRepository.save(toUpdate));
    }

    @Override
    public SuccessDto deletePet(Long id) {
        PetEntity petToDelete = petRepository.findById(id).orElse(null);
        if (petToDelete != null) {
            petRepository.delete(petToDelete);
            return new SuccessDto();
        } else {
            throw new ApiException("Pet not found with id: " + id, HttpStatusCode.valueOf(404));
        }
    }
}
