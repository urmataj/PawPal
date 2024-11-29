package com.example.pawpal.dto;

import com.example.pawpal.entities.UserEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PetUpdateDto {

    private Long id;

    private String name;

    private String species;

    private String breed;

    private String gender;

    private Integer age;

    private String color;

    private Integer weight;

    private Long userId;
}
