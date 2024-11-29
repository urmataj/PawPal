package com.example.pawpal.entities;

import com.fasterxml.jackson.annotation.*;
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
@JsonIgnoreProperties({"pets"})
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserEntity {

    @JsonCreator
    public static UserEntity fromId(@JsonProperty("id") Long id) {
        UserEntity user = new UserEntity();
        user.setId(id);
        return user;
    }

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
    @JsonBackReference
    List<PetEntity> pets;

    @PrePersist
    void init() {this.createdAt = LocalDateTime.now();}

}
