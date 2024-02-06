package com.rodrigofnobrega.demoparkapi.web.dto.mapper;

import com.rodrigofnobrega.demoparkapi.entity.UserEntity;
import com.rodrigofnobrega.demoparkapi.web.dto.user.UserCreateDto;
import com.rodrigofnobrega.demoparkapi.web.dto.user.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

import java.util.List;
import java.util.stream.Collectors;

public class UserMapper {
    public static UserEntity toUser(UserCreateDto userCreateDto) {
        return new ModelMapper().map(userCreateDto, UserEntity.class);
    }

    public static UserResponseDto toDto(UserEntity userEntity) {
        String role = userEntity.getRole().name().substring("ROLE_".length());
        PropertyMap<UserEntity, UserResponseDto> propertyMap = new PropertyMap<UserEntity, UserResponseDto>() {
            @Override
            protected void configure() {
                map().setRole(role);
            }
        };
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.addMappings(propertyMap);

        return modelMapper.map(userEntity, UserResponseDto.class);
    }

    public static List<UserResponseDto> toListDto(List<UserEntity> users) {
        return users.stream().map(user -> toDto(user)).collect(Collectors.toList());
    }
}
