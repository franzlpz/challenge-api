package com.franzlopez.apirestfull.services.impl;

import com.franzlopez.apirestfull.config.jwt.JwtService;
import com.franzlopez.apirestfull.models.Phone;
import com.franzlopez.apirestfull.models.Token;
import com.franzlopez.apirestfull.models.User;
import com.franzlopez.apirestfull.models.dtos.PhoneDto;
import com.franzlopez.apirestfull.models.dtos.request.UserRequestDto;
import com.franzlopez.apirestfull.models.dtos.response.UserResponseDto;
import com.franzlopez.apirestfull.models.enums.TokenType;
import com.franzlopez.apirestfull.repositories.PhoneRepository;
import com.franzlopez.apirestfull.repositories.TokenRepository;
import com.franzlopez.apirestfull.repositories.UserRepository;
import com.franzlopez.apirestfull.services.UserService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.rmi.server.UID;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final PhoneRepository phoneRepository;
    private final TokenRepository tokenRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    @Autowired
    private ModelMapper mapper;

    @Override
    @Transactional(readOnly = true)
    public Optional<UserResponseDto> findById(String id) {
        return userRepository.findById(id)
                .map( user -> mapper.map(user, UserResponseDto.class));

    }

    @Override
    @Transactional(readOnly = true)
    public List<UserResponseDto> findAll() {
        List<User> users = (List<User>) userRepository.findAll();
        return users.stream()
                .map( user -> mapper.map(user, UserResponseDto.class))
                .collect(Collectors.toList());
    }

    @Override
    @Transactional
    public Optional<UserResponseDto> save(UserRequestDto requestDto) {

        User user = User.builder()
        .name(requestDto.getName())
        .email(requestDto.getEmail())
        .password(passwordEncoder.encode(requestDto.getPassword()))
        .build();

        user.setInitialValues();


        var jwtToken = jwtService.generateToken( user);
        if (!validateEmail(user.getEmail())){
            return Optional.empty();
        }
        user.setToken(jwtToken);
        User userResult = userRepository.save(user);

        registerPhones(userResult, requestDto.getPhones());
        saveUserToken(userResult, jwtToken);

        return Optional.of(mapper.map(userResult, UserResponseDto.class));

    }


    @Override
    public void deleteById(String id) {
        userRepository.deleteById(id);
    }

    private void registerPhones(User user, List<PhoneDto> phones) {
        phoneRepository.saveAll(phones.stream()
            .map(dto -> {
                Phone phone = mapper.map(dto, Phone.class);
                phone.setUser(user);
                return phone;
            })
            .collect(Collectors.toList()));
    }

    private void saveUserToken(User user, String jwtToken) {
        var token = Token.builder()
                .user(user)
                .token(jwtToken)
                .tokenType(TokenType.BEARER)
                .expired(false)
                .revoked(false)
                .build();
        tokenRepository.save(token);
    }
    private Boolean validateEmail(String email){
        return userRepository.findByEmail(email).isEmpty();
    }
}
