package com.dwaynewillmakeit.identity_management.service;

import com.dwaynewillmakeit.identity_management.configuration.JwtService;
import com.dwaynewillmakeit.identity_management.dto.AuthenticationRequest;
import com.dwaynewillmakeit.identity_management.dto.AuthenticationResponse;
import com.dwaynewillmakeit.identity_management.dto.RegisterRequest;
import com.dwaynewillmakeit.identity_management.model.User;
import com.dwaynewillmakeit.identity_management.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    private final AuthenticationManager authenticationManager;

    public AuthenticationResponse register(RegisterRequest request) {

        val user = User.builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .username(request.getUsername())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .createdOn(LocalDateTime.now())
                .build();

        userRepository.save(user);

        val jwtToken = jwtService.generateToken(user);
        return AuthenticationResponse.builder().token(jwtToken).build();
    }

    public AuthenticationResponse authenticate(AuthenticationRequest request) {

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                request.getUsername(), request.getPassword()
        ));

        val user = userRepository.findByUsername(request.getUsername()).orElseThrow();

        val jwtToken = jwtService.generateToken(user);

        return AuthenticationResponse.builder().token(jwtToken).build();
    }


}
