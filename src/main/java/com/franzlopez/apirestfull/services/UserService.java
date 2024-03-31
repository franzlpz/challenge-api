package com.franzlopez.apirestfull.services;

import com.franzlopez.apirestfull.models.dtos.request.UserRequestDto;
import com.franzlopez.apirestfull.models.dtos.response.UserResponseDto;

import java.util.Optional;

public interface UserService extends GenericService<UserResponseDto, String> {
    Optional<UserResponseDto> save(UserRequestDto requestDto);

}
