package com.example.pawpal.controllers;

import com.example.pawpal.dto.PetUpdateDto;
import com.example.pawpal.dto.SuccessDto;
import com.example.pawpal.entities.PetEntity;
import com.example.pawpal.entities.PostPetEntity;
import com.example.pawpal.entities.UserEntity;
import com.example.pawpal.repositories.PetRepository;
import com.example.pawpal.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pets/")
public class PetsController {

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private UserRepository userRepository;

    @GetMapping("get/{id}")
    public PetEntity getPetById(@PathVariable("id") Long id) {
        return petRepository.findById(id).orElseThrow();
    }

    @GetMapping("get-all")
    public ResponseEntity<List<PetEntity>> getAllPosts() {
        return ResponseEntity.ok(petRepository.findAll());
    }

    @PostMapping("create")
    public PetEntity create (@RequestBody PetEntity pet) {
        return petRepository.save(pet);
    }

    @PutMapping("update/{id}")
    public PetEntity update (@RequestBody PetUpdateDto pet, @PathVariable("id") Long id) {
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
        if (pet.getUserId() != null) {
            UserEntity user = userRepository.findById(pet.getUserId())
                    .orElseThrow(() -> new RuntimeException("User not found"));
            toUpdate.setUser(user);  // Set the user to the pet entity
        }
        return petRepository.save(toUpdate);
    }

    @DeleteMapping("delete/{id}")
    public SuccessDto delete(@PathVariable("id") Long id){
        petRepository.deleteById(id);
        return new SuccessDto();
    }

}
