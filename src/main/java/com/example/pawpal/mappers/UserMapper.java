package com.example.pawpal.mappers;

import com.example.pawpal.dto.UserDto;
import com.example.pawpal.entities.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper
public interface UserMapper {

    @Mapping(target = "pets.user", ignore = true)
    UserEntity userDtoToUserEntity(UserDto userDto);

    @Mapping(target = "pets.user", ignore = true)
    UserDto userEntityToUserDto(UserEntity user);
}
