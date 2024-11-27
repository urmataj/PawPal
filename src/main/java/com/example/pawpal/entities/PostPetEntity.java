package com.example.pawpal.entities;

import com.example.pawpal.Type;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PostPetEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private String content;

    @Enumerated(EnumType.STRING)
    private Type type; // Enum: SHELTER, LOST, CARE

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private UserEntity author; // Author of the post

    @OneToOne
    @JoinColumn(name = "pet_id")
    private PetEntity pet; // Pet associated with the post (nullable for CARE type)

    private LocalDateTime timestamp;
}

