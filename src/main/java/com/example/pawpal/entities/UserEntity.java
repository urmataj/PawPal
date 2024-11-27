package com.example.pawpal.entities;

import jakarta.persistence.*;
import lombok.*;
import lombok.experimental.FieldDefaults;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(
        name = "users",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = {"username"})
        }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long id;

    @Column(unique = true)
    String username;

    String name;

    String password;

    String email;

    String phone;

    LocalDateTime createdAt;

    @OneToMany(mappedBy = "user")
    private List<PetEntity> pets;

    @PrePersist
    void init() {this.createdAt = LocalDateTime.now();}

}
