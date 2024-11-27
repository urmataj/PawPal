package com.example.pawpal.dto;

import com.example.pawpal.Type;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PostUpdateDto {

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private Type type;

}
