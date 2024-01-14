package com.rodrigofnobrega.demoparkapi.web.dto.mapper;

import com.rodrigofnobrega.demoparkapi.entity.UserEntity;
import com.rodrigofnobrega.demoparkapi.web.dto.UserCreateDto;
import com.rodrigofnobrega.demoparkapi.web.dto.UserResponseDto;
import org.modelmapper.ModelMapper;
import org.modelmapper.PropertyMap;

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
}
