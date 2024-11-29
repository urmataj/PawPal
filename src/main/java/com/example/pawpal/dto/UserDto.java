package com.example.pawpal.dto;

import com.example.pawpal.entities.PetEntity;
import com.example.pawpal.entities.UserEntity;
import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDto {

//    @JsonCreator
//    public static UserEntity fromId(@JsonProperty("id") Long id) {
//        UserEntity user = new UserEntity();
//        user.setId(id);
//        return user;
//    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    String username;

    String name;

    String password;

    String email;

    String phone;

    private List<PetEntity> pets;
}
