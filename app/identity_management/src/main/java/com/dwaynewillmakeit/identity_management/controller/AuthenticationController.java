package com.dwaynewillmakeit.identity_management.controller;

import com.dwaynewillmakeit.identity_management.dto.AuthenticationRequest;
import com.dwaynewillmakeit.identity_management.dto.AuthenticationResponse;
import com.dwaynewillmakeit.identity_management.dto.RegisterRequest;
import com.dwaynewillmakeit.identity_management.model.User;
import com.dwaynewillmakeit.identity_management.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import lombok.val;
import org.springframework.context.annotation.Profile;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/register")
    public ResponseEntity<AuthenticationResponse> register(
            @RequestBody RegisterRequest request
    ) {
        return ResponseEntity.ok(authenticationService.register(request));
    }

    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(
            @RequestBody AuthenticationRequest request
    ) {
        return ResponseEntity.ok(authenticationService.authenticate(request));
    }

    @GetMapping("/hash")
    @Profile("development")
    public ResponseEntity<String> passwordHash(@RequestParam String word){

        val password = new BCryptPasswordEncoder().encode(word);

        return ResponseEntity.ok().body(password);

    }




}
