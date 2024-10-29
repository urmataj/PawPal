package com.example.pawpal.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserDto {

    String username;

    String name;

    String password;

    String email;

    String phone;
}
