package com.example.pawpal.entities;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;
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

    @JsonIgnore
    @OneToMany(mappedBy = "user")
    List<PetEntity> pets;

    @PrePersist
    void init() {this.createdAt = LocalDateTime.now();}

}
