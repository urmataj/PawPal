package com.example.pawpal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PetUpdateDto {

    private Long id;

    private String name;

    private String species;

    private String breed;

    private String gender;

    private Integer age;

    private String color;

    private Integer weight;

}
