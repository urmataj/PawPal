package com.example.pawpal.controllers;

import com.example.pawpal.dto.PetUpdateDto;
import com.example.pawpal.dto.SuccessDto;
import com.example.pawpal.entities.PetEntity;
import com.example.pawpal.entities.PostPetEntity;
import com.example.pawpal.entities.UserEntity;
import com.example.pawpal.exceptions.ApiException;
import com.example.pawpal.repositories.PetRepository;
import com.example.pawpal.repositories.UserRepository;
import com.example.pawpal.services.PetsService;
import com.example.pawpal.services.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/pets/")
public class PetsController {

    @Autowired
    private PetsService petsService;

    @Autowired
    private UsersService usersService;

    @GetMapping("get/{id}")
    public PetUpdateDto getPetById(@PathVariable("id") Long id) {
        return petsService.getPetById(id)
                .orElseThrow(() -> new ApiException(
                        String.format("Pet with id:%d Not Found", id), HttpStatusCode.valueOf(404)
                ));
    }

    @GetMapping("get-all")
    public ResponseEntity<List<PetUpdateDto>> getAllPosts() {
        return ResponseEntity.ok(petsService.getAllPets());
    }

    @PostMapping("create")
    public ResponseEntity<PetUpdateDto> create (@Validated @RequestBody PetUpdateDto pet) {
        pet.setId(null);
        PetUpdateDto savedPet = petsService.savePet(pet);
        return ResponseEntity
                .created(URI.create("/pets/create/" + savedPet.getId()))
                .body(savedPet);
    }

    @PutMapping("update/{id}")
    public ResponseEntity<PetUpdateDto> update (@Validated @RequestBody PetUpdateDto pet, @PathVariable("id") Long id) {
        petsService.getPetById(id)
                .orElseThrow(() -> new ApiException(
                        String.format("Pet with id %d not found", id),
                        HttpStatusCode.valueOf(404)
                ));
        pet.setId(id);
        PetUpdateDto updatedPet = petsService.savePet(pet);
        return ResponseEntity.ok(updatedPet);
    }

    @DeleteMapping("delete/{id}")
    public SuccessDto delete(@PathVariable("id") Long id){
        petsService.getPetById(id)
                .orElseThrow(() -> new ApiException(
                        String.format("Pet with id %d not found", id), HttpStatusCode.valueOf(404)
                ));
        petsService.deletePet(id);
        return new SuccessDto();
    }

}
