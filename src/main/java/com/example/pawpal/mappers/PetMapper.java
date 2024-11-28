package com.example.pawpal.mappers;


import com.example.pawpal.dto.PetUpdateDto;
import com.example.pawpal.entities.PetEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface PetMapper {

    @Mapping(target = "user.pets", ignore = true)
    PetEntity petDtoToPet(PetUpdateDto petDto);

    @Mapping(target = "user.pets", ignore = true)
    PetUpdateDto petToPetDto(PetEntity pet);

}
