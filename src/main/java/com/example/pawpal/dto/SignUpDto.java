package com.example.pawpal.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.*;
import lombok.experimental.FieldDefaults;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class SignUpDto {

    @NotEmpty
    @Size(min = 6, max = 100)
    String username;

    @NotEmpty
    @Size(min = 8)
    String password;

    @NotEmpty
    String name;
}
