package com.franzlopez.apirestfull.services.impl;

import com.franzlopez.apirestfull.config.jwt.JwtService;
import com.franzlopez.apirestfull.models.Token;
import com.franzlopez.apirestfull.models.User;
import com.franzlopez.apirestfull.models.dtos.PhoneDto;
import com.franzlopez.apirestfull.models.dtos.request.UserRequestDto;
import com.franzlopez.apirestfull.models.dtos.response.UserResponseDto;
import com.franzlopez.apirestfull.repositories.TokenRepository;
import com.franzlopez.apirestfull.repositories.UserRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    private UserRepository userRepository;

    @Spy
    PasswordEncoder passwordEncoder;

    @Mock
    TokenRepository tokenRepository;

    @Spy
    JwtService jwtService;

    @Test
    @DisplayName("Return user when if exist id")
    void returnUserWhenIfExistId() {
        //Arrange
        when(userRepository.findById(anyString())).thenReturn(Optional.of(buildUser()));

        //Act
        Optional<UserResponseDto> userResponseDto = userService.findById("1");

        //Assert
        assertTrue(userResponseDto.isPresent());
    }

    @Test
    @DisplayName("Return all users")
    void returnAllUsers() {
        //Arrange
        when(userRepository.findAll()).thenReturn(List.of(buildUser()));

        //Act
        var users = userService.findAll();

        //Assert
        assertFalse(users.isEmpty());

    }



    private Token buildToken() {
        return Token.builder()
                .id("1")
                .token(UUID.randomUUID().toString())
                .build();
    }

    private UserRequestDto buildUserRequestDto() {
        return UserRequestDto.builder()
                .name("Franz Lopez")
                .email("franz@correo.com")
                .password(passwordEncoder.encode("misPassoword"))
                .phones(List.of(buildPhoneDto()))
                .build();
    }

    private PhoneDto buildPhoneDto() {
        return PhoneDto.builder()
                .number("1234567")
                .cityCode("1")
                .countryCode("57")
                .build();
    }

    private UserResponseDto buildUserResponseDto() {
        return UserResponseDto.builder()
                .id("1")
                .created("2021-09-01T00:00:00")
                .modified("2021-09-01T00:00:00")
                .lastLogin("2021-09-01T00:00:00")
                .token(UUID.randomUUID().toString())
                .isActive(true)
                .build();
    }

    private User buildUser() {
        return User.builder()
                .id("1")
                .name("name")
                .email("email")
                .password("password")
                .created(LocalDateTime.now())
                .modified(LocalDateTime.now())
                .lastLogin(LocalDateTime.now())
                .token("token")
                .isActive(true)
                .build();
    }
}