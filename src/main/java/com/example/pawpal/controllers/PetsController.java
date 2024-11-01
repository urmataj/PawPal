package com.example.pawpal.controllers;

import com.example.pawpal.dto.PetUpdateDto;
import com.example.pawpal.dto.SuccessDto;
import com.example.pawpal.entities.PetEntity;
import com.example.pawpal.repositories.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/pets/")
public class PetsController {

    @Autowired
    private PetRepository petRepository;

    @GetMapping("get-all")
    public PetEntity getPetById(@PathVariable("id") Long id) {
        return petRepository.findById(id).orElseThrow();
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
        return petRepository.save(toUpdate);
    }

    @DeleteMapping("delete/{id}")
    public SuccessDto delete(@PathVariable("id") Long id){
        petRepository.deleteById(id);
        return new SuccessDto();
    }

}
